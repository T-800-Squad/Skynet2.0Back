package edu.eci.cvds.Labtools.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public abstract class User {

    @Id
    protected String userId;
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected boolean logged;
    protected ArrayList<Booking> bookings;
    protected boolean rol;
    protected int attempsTimes;

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void deleteBooking(String bookingId) {
        for  (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookings.remove(booking);
            }
        }
    }

}
