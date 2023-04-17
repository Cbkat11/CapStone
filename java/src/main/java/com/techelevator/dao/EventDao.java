package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.User;

import java.util.List;

public interface EventDao {

    List<Event> findAll();

    Event getEventByID(int eventID);

    List<Event> findEventsByUserID(int userID);

    boolean createEvent(String eventName, long createDate, long expDate, int userID);

    boolean addRestaurantToEvent(int eventID, int restaurantID);
}
