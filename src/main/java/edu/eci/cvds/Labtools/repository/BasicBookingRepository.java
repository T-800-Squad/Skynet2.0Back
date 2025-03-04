package edu.eci.cvds.Labtools.repository;


import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class BasicBookingRepository implements BookingRepository {

    public String createBooking(User user, Lab lab, BookingDTO bookingDTO) {
        return null;
    }

    public String deleteBooking(String bookingId) {
        return null;
    }
}
