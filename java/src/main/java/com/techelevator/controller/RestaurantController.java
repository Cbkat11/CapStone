package com.techelevator.controller;

import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.EventRestaurant;
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

    @PostMapping("/restaurants/{eventId}")
    public void setRestaurantsToEvent(@PathVariable int eventId, @RequestBody List<Integer> restaurantIds) {
        for(int i = 0; i < restaurantIds.size(); i++) {
            restaurantDao.addRestaurantToEvent(eventId, restaurantIds.get(i));
        }
    }

    @RequestMapping("/restaurants/{eventId}")
    public List<Restaurant> getRestaurantsByEvent(@PathVariable int eventId) {
        return restaurantDao.getRestaurantsByEventId(eventId);
    }

}
