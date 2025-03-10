package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.repository.MongoBookingRepository;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BasicBookingService implements BookingService{

    @Autowired
    private MongoBookingRepository bookingRepository;

    @Autowired
    private MongoUserRepository userRepository;

    @Autowired
    private MongoLabRepository labRepository;

    public String[] checkAvailability(String date) {return null;}

    public Booking createBooking(CreateBookingDTO createBookingDTO) {
        Booking booking = new Booking();

        var lab = labRepository.findByName(createBookingDTO.labName);
        if (lab == null) {
            throw new RuntimeException("Lab not found: " + createBookingDTO.labName);
        }
        booking.setLab(lab);

        var user = userRepository.findByName(createBookingDTO.userName);
        if (user == null) {
            throw new RuntimeException("User not found: " + createBookingDTO.userName);
        }
        booking.setUser(user);



        booking.setDate(createBookingDTO.date);
        booking.setBookingId(UUID.randomUUID().toString());

        System.out.println("Booking created: " + booking.getBookingId());
        return bookingRepository.save(booking);
    }


    public boolean deleteBooking(String bookingId) {
        return false;
    }
}
