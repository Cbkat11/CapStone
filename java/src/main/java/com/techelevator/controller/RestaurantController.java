package com.techelevator.controller;

import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {

    private RestaurantDao restaurantDao;

    public RestaurantController(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @RequestMapping(path = "/restaurants", method = RequestMethod.GET)
    public List<Restaurant> listRestaurants() {
        return restaurantDao.findAllRestaurants();
    }

    @RequestMapping(path = "/restaurants/{eventID}", method = RequestMethod.GET)
    public List<Restaurant> getRestaurantsByEventID(@PathVariable int eventID) {
        return restaurantDao.getRestaurantsByEventID(eventID);
    }


}
