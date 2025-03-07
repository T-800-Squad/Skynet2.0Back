package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.LabDTO;
import edu.eci.cvds.Labtools.model.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
