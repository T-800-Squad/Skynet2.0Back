package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.dto.LabDTO;
import edu.eci.cvds.Labtools.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class BasicBookingService implements BookingService{

    public LabDTO[] checkAvailability(String date) {return null;}

    public boolean createBooking(LabDTO labDTO, UserDTO userDTO) {
        return false;
    }

    public boolean deleteBooking(BookingDTO bookingDTO){
        return false;
    }
}
