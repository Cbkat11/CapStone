package com.techelevator.model;

public class Restaurant {

//    INSERT INTO restaurant (name, type, address, hours, phone_number, stars, img_url, takeout, delivery)
//    VALUES ('Il Rione', 'pizza', '1303 W 65th St, Cleveland, OH 44102', '4:00PM-10:00PM', '(216) 282-1451', 4.8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZWNLWv5D1yRmFIqCmZVuLrcT2CgKH7_5pMQ&usqp=CAU', true, false),
    private int id;
    private String name;
    private String type;
    private String address;
    private String hours;
    private String phoneNumber;
    private double stars;
    private String imgUrl;
    private boolean takeout;
    private boolean delivery;
    private String website;
    private String reviews;
    private boolean popupTrigger;
    private int totalRank;
    private String menu;
    private String priceRange;

    public Restaurant() { }

    public Restaurant(int id, String name, String type, String address,String hours, String phoneNumber, double stars,String imgUrl,boolean takeout,boolean delivery, String website, String reviews, boolean popupTrigger, String menu, String priceRange){
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.hours = hours;
        this.phoneNumber = phoneNumber;
        this.stars = stars;
        this.imgUrl = imgUrl;
        this.takeout = takeout;
        this.delivery = delivery;
        this.website = website;
        this.reviews = reviews;
        this.popupTrigger = popupTrigger;
        this.menu = menu;
        this.priceRange = priceRange;

    }
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type= type;
    }

    public String getAddress() {
        return address ;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getHours() {
        return hours ;
    }

    public void setHours(String hours) {
        this.hours= hours;
    }

    public String getPhoneNumber() {
        return phoneNumber ;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars=stars;
    }

    public String getImgUrl() {
        return imgUrl ;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl=imgUrl;
    }

    public boolean getTakeout() {
        return takeout ;
    }

    public void setTakeout(boolean takeout) {
        this.takeout=takeout;
    }

    public boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery=delivery;
    }

    public String getWebsite() { return website; }

    public void setWebsite(String website) { this.website=website; }

    public String getReviews() { return reviews; }

    public void setReviews(String reviews) { this.reviews=reviews; }

    public boolean getPopupTrigger() { return popupTrigger; }

    public void setPopupTrigger(boolean popupTrigger) { this.popupTrigger=popupTrigger; }

    public void setTotalRank(int totalRank) { this.totalRank=totalRank; }

    public int setTotalRank() { return this.totalRank}

    public String getMenu() { return menu; }

    public void setMenu(String menu) { this.menu=menu; }

    public String getPriceRange() { return priceRange; }

    public void setPriceRange(String priceRange) { this.priceRange=priceRange; }
}
