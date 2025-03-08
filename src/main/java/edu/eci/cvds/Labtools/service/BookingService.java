package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.LabDTO;
import edu.eci.cvds.Labtools.model.UserDTO;


public interface BookingService {
    LabDTO[] checkAvailability(String date);

    boolean createBooking(LabDTO labDTO, UserDTO userDTO);

    //boolean deleteBooking(BookingDTO bookingDTO);
}
