package edu.eci.cvds.Labtools.repository;


import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;

public interface BookingRepository {
    String createBooking(User user, Lab lab, int day, int timeLine);
    String deleteBooking(String bookingId);
}
