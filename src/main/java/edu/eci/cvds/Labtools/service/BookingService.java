package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.dto.LabDTO;
import edu.eci.cvds.Labtools.dto.UserDTO;


public interface BookingService {
    LabDTO[] checkAvailability(String date);

    boolean createBooking(LabDTO labDTO, UserDTO userDTO);

    boolean deleteBooking(BookingDTO bookingDTO);
}
