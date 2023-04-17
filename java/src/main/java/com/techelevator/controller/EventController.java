package com.techelevator.controller;

import com.techelevator.model.*;
import com.techelevator.dao.EventDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    private EventDao eventDao;

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @RequestMapping(path = "/event", method = RequestMethod.POST)
    public boolean get(@Valid @RequestBody Event event) {
        return eventDao.createEvent(event.getEventName(), event.getLink(), event.getExpDate(), event.getUserID());

    }

    @RequestMapping(path = "/event/{eventID}", method = RequestMethod.GET)
    public Event get(@PathVariable int eventID) {
        Event event = eventDao.getEventByID(eventID);
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found.");
        } else {
            return event;
        }
    }

    @RequestMapping(path = "/{userID}/events", method = RequestMethod.GET)
    public List<Event> getEventsByUserID(@PathVariable int userID) {
        return eventDao.findEventsByUserID(userID);
    }

}
