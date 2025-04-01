package edu.eci.cvds.Labtools.service;


import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.dto.DeleteBookingDTO;
import edu.eci.cvds.Labtools.model.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(CreateBookingDTO createBookingDTO);

    void deleteBooking(DeleteBookingDTO deleteBookingDTO);

    String  generateRandomBookings();

    List<Integer> getPriorities();

}
