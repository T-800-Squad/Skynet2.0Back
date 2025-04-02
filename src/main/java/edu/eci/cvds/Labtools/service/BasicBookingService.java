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
import java.util.*;
import java.util.stream.IntStream;

/**
 * Clase BasicBookingService que implementa la interfaz BookingService.
 * Esta clase se encarga de gestionar las reservas en el sistema.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Service
public class BasicBookingService implements BookingService{

    @Autowired
    private MongoBookingRepository bookingRepository;

    @Autowired
    private MongoUserRepository userRepository;

    @Autowired
    private MongoLabRepository labRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Crea una nueva reserva a partir de un objeto CreateBookingDTO.
     *
     * @param createBookingDTO Objeto que contiene la información de la reserva a crear.
     * @return La reserva creada.
     */
    public Booking createBooking(CreateBookingDTO createBookingDTO) {
        Booking booking = new Booking();
        Lab lab = labRepository.findByName(createBookingDTO.getLabName());

        validateDateAndLab(createBookingDTO.getDate(),lab);
        booking.setLab(lab);
        booking.setDate(createBookingDTO.getDate());
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setPriority(createBookingDTO.getPriority());

        System.out.println("booking created");


        updateListOfBookingsInUser(createBookingDTO.getUserName(), booking);
        bookingRepository.save(booking);

        return booking;
    }

    /**
     * Actualiza la lista de reservas de un usuario después de crear una nueva reserva.
     *
     * @param userName Nombre del usuario.
     * @param booking Reserva a agregar a la lista del usuario.
     */
    private void updateListOfBookingsInUser(String userName, Booking booking) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.addBooking(booking);
        userRepository.save(user);
    }

    /**
     * Valida la fecha y el laboratorio para la reserva.
     *
     * @param date Fecha de la reserva.
     * @param lab Laboratorio asociado a la reserva.
     */
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

    /**
     * Elimina una reserva a partir de un objeto DeleteBookingDTO.
     *
     * @param deleteBookingDTO Objeto que contiene la información de la reserva a eliminar.
     */
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

    /**
     * Actualiza la disponibilidad del laboratorio después de eliminar una reserva.
     *
     * @param lab Laboratorio asociado a la reserva.
     * @param date Fecha de la reserva.
     */
    private void updateDateInLab(Lab lab, String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        lab.deleteIsAvailable(dateTime);
        labRepository.save(lab);
    }

    /**
     * Actualiza la lista de reservas de un usuario antes de eliminar una reserva.
     *
     * @param userName Nombre del usuario.
     * @param booking Reserva a eliminar de la lista del usuario.
     */
    private void updateListOfBookingsBeforeDelete(String userName, Booking booking) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.deleteBooking(booking);
        userRepository.save(user);
    }

}
