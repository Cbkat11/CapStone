package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.User;

import java.util.List;

public interface EventDao {

    List<Event> findAll();

    Event getEventByID(int eventID);

    List<Event> findEventsByUserID(int userID);

    public boolean createEvent(String eventName, String link, String expDate, int userID);
}
