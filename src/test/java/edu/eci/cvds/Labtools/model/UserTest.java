package edu.eci.cvds.Labtools.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {
    @Test
    public void testAddBooking() {
        Booking booking = new Booking();
        booking.setBookingId("123");
        User user = new BasicUser();
        user.addBooking(booking);
        assertEquals(user.getBookings().size(), 1);
        assertEquals(user.getBookings().get(0), booking);
    }
    @Test
    public void testAddBookingMaximumThree() {
        User user = new BasicUser();
        try {
            Booking booking = new Booking();
            Booking booking2 = new Booking();
            Booking booking3 = new Booking();
            Booking booking4 = new Booking();
            booking.setBookingId("123");
            booking2.setBookingId("123");
            booking3.setBookingId("123");
            booking4.setBookingId("123");
            user.addBooking(booking);
            user.addBooking(booking2);
            user.addBooking(booking3);
            user.addBooking(booking4);
        } catch (IllegalArgumentException e) {
            assertEquals("User already have three bookings", e.getMessage());
        }
        assertEquals(4, user.getBookings().size());

    }
    @Test
    public void testRemoveBooking() {
        Booking booking = new Booking();
        booking.setBookingId("123");
        User user = new BasicUser();
        user.addBooking(booking);
        assertEquals(1, user.getBookings().size());
        assertEquals(user.getBookings().get(0), booking);
        user.deleteBooking(booking);
        assertEquals(0, user.getBookings().size());
    }

    @Test
    public void testRemoveBookingFailsBecauseBookingIsEmpty() {
        try {
            Booking booking = new Booking();
            booking.setBookingId("123");
            User user = new BasicUser();
            user.deleteBooking(booking);
        } catch (IllegalArgumentException e) {
            assertEquals("User don't have bookings", e.getMessage());
        }

    }



}
