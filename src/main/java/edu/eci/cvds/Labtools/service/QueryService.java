package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.Booking;

import java.util.List;

public interface QueryService {
    List<Booking> getAllBookings();
    Booking[] queryUserBookings(String userId);
}
