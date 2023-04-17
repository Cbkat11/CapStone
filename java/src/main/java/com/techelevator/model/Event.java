package com.techelevator.model;

public class Event {

    private int eventID;
    private String eventName;
    private String link;
    private String expDate;
    private int userID;

    public Event() { }

    public Event(int eventID, String eventName, String link, String expDate, int userID) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.link = link;
        this.expDate = expDate;
        this.userID = userID;
    }

    public int getEventID(){
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
