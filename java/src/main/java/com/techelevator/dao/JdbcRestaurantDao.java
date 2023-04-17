package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Restaurant;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcRestaurantDao implements RestaurantDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRestaurantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Restaurant getRestaurantByID(int eventID) {
        String sql = "SELECT * FROM event WHERE event_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventID);
        if (results.next()) {
            return mapRowToRestaurant(results);
        } else {
            return null;
        }
    }

    @Override
    public List<Restaurant> findAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "select * from restaurant";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    @Override
    public List<Restaurant> getRestaurantsByEventID(int eventID) {
        List<Restaurant> restaurants = new ArrayList<>();

        String sql = "SELECT restaurant_id FROM restaurant WHERE event_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, eventID);

        while (rowSet.next()) {
            Restaurant restaurant = getRestaurantByID(rowSet.getInt("event_id"));
            restaurants.add(restaurant);
        }
        return restaurants;
    }

    private Restaurant mapRowToRestaurant(SqlRowSet row) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(row.getInt("restaurant_id"));
        restaurant.setName(row.getString("name"));
        restaurant.setType(row.getString("type"));
        restaurant.setAddress(row.getString("address"));
        restaurant.setHours(row.getString("hours"));
        restaurant.setPhoneNumber(row.getString("phone_number"));
        restaurant.setStars(row.getDouble("stars"));
        restaurant.setImgUrl(row.getString("img_url"));
        restaurant.setTakeout(row.getBoolean("takeout"));
        restaurant.setDelivery(row.getBoolean("delivery"));
        return restaurant;
    }
}
