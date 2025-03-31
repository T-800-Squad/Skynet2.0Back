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
import java.util.Random;
import java.util.UUID;
import java.util.List;
import java.util.stream.IntStream;

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
        booking.setPriority(createBookingDTO.getPriority());

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
        updateDateInLab(labRepository.findByName(booking.getLab().getName()),booking.getDate());
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

    public void generateRandomBookings() {
        Random random = new Random();
        int numBookings = random.nextInt(901) + 100;

        List<User> users = userRepository.findAll();
        List<Lab> labs = labRepository.findAll();

        if (users.isEmpty() || labs.isEmpty()) {
            throw new IllegalStateException("No users or labs available for bookings.");
        }

        IntStream.range(0, numBookings).forEach(i -> {
            User user = users.get(random.nextInt(users.size()));
            Lab lab = labs.get(random.nextInt(labs.size()));

            Booking booking = new Booking();
            booking.setLab(lab);
            booking.setDate(generateRandomDate());
            booking.setBookingId(UUID.randomUUID().toString());
            booking.setPriority(random.nextInt(5) + 1);

            bookingRepository.save(booking);
            updateListOfBookingsInUser(user.getName(), booking);
        });

        System.out.println(numBookings + " bookings generated successfully.");
    }

    private String generateRandomDate() {
        LocalDateTime randomDate = LocalDateTime.now()
                .plusDays(new Random().nextInt(30));
        return randomDate.format(formatter);
    }
}
