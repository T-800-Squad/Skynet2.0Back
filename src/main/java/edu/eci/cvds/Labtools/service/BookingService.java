package edu.eci.cvds.Labtools.service;


import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.model.Booking;

public interface BookingService {
    String[] checkAvailability(String date);

    Booking createBooking(CreateBookingDTO createBookingDTO);

    boolean deleteBooking(String bookingId);
}
