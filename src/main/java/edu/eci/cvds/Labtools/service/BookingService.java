package edu.eci.cvds.Labtools.service;


import edu.eci.cvds.Labtools.dto.CreateBookingDTO;

public interface BookingService {
    String[] checkAvailability(String date);

    boolean createBooking(CreateBookingDTO createBookingDTO);

    boolean deleteBooking(String bookingId);
}
