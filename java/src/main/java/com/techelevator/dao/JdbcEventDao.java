package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Restaurant;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class JdbcEventDao implements EventDao{

    private List<Event> events = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        String sql = "select * from event";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Event event = mapRowToEvent(results);
            events.add(event);
        }

        return events;
    }

    @Override
    public Event getEventByID(int eventID) {
        String sql = "SELECT * FROM event WHERE event_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventID);
        if (results.next()) {
            return mapRowToEvent(results);
        } else {
            return null;
        }
    }

    @Override
    public List<Event> findEventsByUserID(int userID) {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT event_id FROM event WHERE user_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userID);

        while (rowSet.next()) {
            Event event = getEventByID(rowSet.getInt("event_id"));
            events.add(event);
        }
        return events;
    }

    @Override
    public boolean createEvent(String eventName, String link, String expDate, int userID) {
        String insertEventSql = "insert into event (username, password_hash,role) values (?,?,?,?)";
        return jdbcTemplate.update(insertEventSql, eventName, link, expDate, userID) == 1;
    }


    private Event mapRowToEvent(SqlRowSet row) {
        Event event = new Event();
        event.setEventID(row.getInt("event_id"));
        event.setEventName(row.getString("event_name"));
        event.setLink(row.getString("link"));
        event.setExpDate(row.getString("expiration_date"));
        event.setUserID(row.getInt("user_id"));
        return event;
    }
}
