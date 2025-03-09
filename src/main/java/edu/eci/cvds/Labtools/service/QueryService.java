package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.BookingDTO;

public interface QueryService {
    BookingDTO[] findBookingsByName(String name);
}
