package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.BookingDTO;

public interface BookingService {
    boolean[][] checkAvailability();
    String createBooking(BookingDTO bookingDTO);
    String deleteBooking(BookingDTO bookingDTO);
    String[]checkAvailableLabExactMoment(int day, int timeLine);
}
