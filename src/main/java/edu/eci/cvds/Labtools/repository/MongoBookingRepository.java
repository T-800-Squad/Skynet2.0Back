package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoBookingRepository extends BookingRepository {

    default String createBooking(Booking booking) {
        return null;
    }

    default String deleteBooking(String bookingId) {
        return null;
    }
}
