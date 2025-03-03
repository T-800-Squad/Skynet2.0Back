package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class BasicBookingRepository implements BookingRepository {

    public String createBooking(User user, Lab lab, int day, int timeLine) {
        return null;
    }

    public String deleteBooking(String bookingId) {
        return null;
    }
}
