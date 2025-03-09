package edu.eci.cvds.Labtools.service;


import edu.eci.cvds.Labtools.dto.CreateBookingDTO;

public interface BookingService {
    String[] checkAvailability(String date);

    void createBooking(CreateBookingDTO createBookingDTO);

    boolean deleteBooking(String bookingId);
}
