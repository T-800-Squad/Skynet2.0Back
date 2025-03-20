package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.dto.DeleteBookingDTO;
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
        labRepository.save(lab);
    }


    public void deleteBooking(DeleteBookingDTO deleteBookingDTO) {
        String bookingId = deleteBookingDTO.getBookingId();
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new IllegalArgumentException("Booking not found");
        } else {
            bookingRepository.deleteById(bookingId);
        }
        Booking booking = optionalBooking.get();
        updateDateInLab(booking.getLab(),booking.getDate());
        updateListOfBookingsBeforeDelete(deleteBookingDTO.getUserName(), booking);
    }

    private void updateDateInLab(Lab lab, String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        lab.deleteIsAvailable(dateTime);
        labRepository.save(lab);
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
