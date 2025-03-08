package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.repository.MongoBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BasicQueryService implements QueryService {
    @Autowired
    private MongoBookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    @Override
    public Booking[] queryUserBookings(String userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);
        Booking[] bookingsForUser = new Booking[userBookings.size()];
        for (int i = 0; i < userBookings.size(); i++) {
            bookingsForUser[i] = userBookings.get(i);
        }
        return bookingsForUser;
    }
}
