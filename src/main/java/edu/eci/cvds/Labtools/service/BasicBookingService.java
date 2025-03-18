package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoBookingRepository;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasicBookingService implements BookingService{

    @Autowired
    private MongoBookingRepository bookingRepository;

    @Autowired
    private MongoUserRepository userRepository;

    @Autowired
    private MongoLabRepository labRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public String[] checkAvailability(String date) {
        return new String[0];
    }

    public Booking createBooking(CreateBookingDTO createBookingDTO) {
        Booking booking = new Booking();
        Lab lab = labRepository.findByName(createBookingDTO.getLabName());

        validateDateAndLab(createBookingDTO.getDate(),lab);
        booking.setLab(lab);
        booking.setDate(createBookingDTO.getDate());
        booking.setBookingId(UUID.randomUUID().toString());

        System.out.println("booking created");
        bookingRepository.save(booking);

        updateListOfBookingsInUser(createBookingDTO.getUserName(), booking);

        return booking;
    }

    private void updateListOfBookingsInUser(String userName, Booking booking) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.addBooking(booking);
        userRepository.save(user);
    }

    private void validateDateAndLab(String date, Lab lab) {
        if (lab == null) {
            throw new IllegalArgumentException("Lab not found");
        }
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        if(dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date is after now");
        }
        lab.setIsAvailable(dateTime);
    }


    public void deleteBooking(String bookingId, String userId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isEmpty()) {
            throw new IllegalArgumentException("Booking not found with id: " + bookingId);
        } else {
            bookingRepository.deleteById(bookingId);
        }
    }

    private void updateListOfBookingsBeforeDelete(String userName, Booking booking) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.deleteBooking(booking);
        userRepository.save(user);
    }
}
