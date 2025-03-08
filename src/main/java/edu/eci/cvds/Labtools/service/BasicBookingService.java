package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.LabDTO;
import edu.eci.cvds.Labtools.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class BasicBookingService implements BookingService{

    public LabDTO[] checkAvailability(String date) {return null;}

    public boolean createBooking(LabDTO labDTO, UserDTO userDTO) {
        return false;
    }

    /*public boolean deleteBooking(BookingDTO bookingDTO){
        return false;
    }*/
}
