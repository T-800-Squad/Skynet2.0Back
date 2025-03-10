package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import org.springframework.stereotype.Service;

@Service
public class BasicBookingService implements BookingService{

    public String[] checkAvailability(String date) {return null;}

    public boolean createBooking(CreateBookingDTO createBookingDTO) {
        return false;
    }

    public boolean deleteBooking(String bookingId) {
        return false;
    }
}