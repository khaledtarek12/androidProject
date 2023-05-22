package com.example.booking_airline_tickets;

public class Flight {
    private int id;
    private String takeOff;
    private String landing;
    private int startTime;
    private int endTime;
    private int price;

    public Flight() {

    }

    public Flight(int flightId, String takeOff, String landing, int startTime, int endTime, int price) {
    }

    public Flight(String takeOff, String landing, int startTime, int endTime, int price) {
        this.takeOff = takeOff;
        this.landing = landing;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Flight(String flightId, String takeOff, String landing, Integer integer, Object o, Object o1) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(String takeOff) {
        this.takeOff = takeOff;
    }

    public String getLanding() {
        return landing;
    }

    public void setLanding(String landing) {
        this.landing = landing;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

