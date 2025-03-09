package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import org.springframework.stereotype.Service;

@Service
public class BasicQueryService implements QueryService {
    public BookingDTO[] findBookingsByName(String name){
        return null;
    }
}