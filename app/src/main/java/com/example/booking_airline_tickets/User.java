package com.example.booking_airline_tickets;

public class User {
    private int id;
    private String userName;
    private String password;
    private int flightId;

    public User() {
    }

    public User(String userName, String password, int flightId) {
        this.userName = userName;
        this.password = password;
        this.flightId = flightId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}

