package com.techelevator.model;

public class Event {

    private int eventID;
    private String expDate;
    private int userID;
    private String link;

    public Event() { }

    public Event(int eventID, String expDate, int userID, String link) {
        this. eventID = eventID;
        this.expDate = expDate;
        this.userID = userID;
        this.link = link;
    }
}
