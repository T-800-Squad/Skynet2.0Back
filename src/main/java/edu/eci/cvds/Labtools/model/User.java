package edu.eci.cvds.Labtools.model;

import lombok.Data;

import java.util.List;

@Data
public abstract class User {

    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected boolean logged;
    protected List<Booking> bookings;

    public void addBooking(Booking booking) {

    }

    public void deleteBooking(String bookingId) {

    }

}
