package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class BasicQueryService implements QueryService {
    @Autowired
    private MongoUserRepository userRepository;

    @Autowired
    private MongoLabRepository labRepository;

    public List<String> checkAvailability(String date) {
        List<Lab> labs = labRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date,formatter);
        List<String> labList = new ArrayList<>();
        for (Lab lab : labs) {
            if(!lab.getIsAvailable().containsKey(dateTime)) {
                labList.add(lab.getName());
            }
        }
        return labList;
    }

    public List<BookingDTO> findBookingsByName(String name){
        User user = userRepository.findByName(name);
        validateUser(user);
        List<Booking> bookings = user.getBookings();
        List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
        for(Booking booking : bookings){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setDate(booking.getDate());
            bookingDTO.setBookingId(booking.getBookingId());
            bookingDTO.setLabName(booking.getLab().getName());
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }

    private void validateUser(User user) {

        if(user==null){
            throw new IllegalArgumentException("No user found");
        }
        if(!user.getRol() || user.getBookings().isEmpty()){
            throw new IllegalArgumentException("User don't have bookings");
        }

    }
}