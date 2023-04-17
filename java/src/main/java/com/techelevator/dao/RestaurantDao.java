package com.techelevator.dao;

import com.techelevator.model.Restaurant;

import java.util.List;

public interface RestaurantDao {

    List<Restaurant> findAllRestaurants();

    Restaurant getRestaurantByID(int eventID);

    List<Restaurant> getRestaurantsByEventID(int eventID);
}
