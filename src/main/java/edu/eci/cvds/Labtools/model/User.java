package edu.eci.cvds.Labtools.model;

import java.util.List;

public abstract class User {

    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected List<Booking> bookings;

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
