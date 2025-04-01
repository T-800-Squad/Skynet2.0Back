package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.dto.DeleteBookingDTO;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de reservas en la aplicación.
 * Este controlador proporciona endpoints para crear, eliminar y generar reservas.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */

@RestController
@RequestMapping("/booking")
public class BookingController {


    private BookingService bookingService;

    /**
     * Constructor para inyectar el servicio de reservas.
     *
     * @param bookingService Servicio de reservas a inyectar.
     */
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Endpoint para crear una nueva reserva.
     *
     * @param createBookingDTO DTO que contiene la información de la nueva reserva a crear.
     * @return ResponseEntity con la reserva creada y un código de estado 201 (CREATED) si la reserva es creada exitosamente.
     */
    @PostMapping()
    public ResponseEntity<Booking> createBooking(@RequestBody CreateBookingDTO createBookingDTO) {
        Booking booking = bookingService.createBooking(createBookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    /**
     * Endpoint para eliminar una reserva existente.
     *
     * @param deleteBookingDTO DTO que contiene la información de la reserva a eliminar.
     * @return ResponseEntity con un mensaje de éxito y un código de estado 200 (OK) si la reserva es eliminada exitosamente.
     */
    @DeleteMapping()
    public ResponseEntity<String> deleteBooking(@RequestBody DeleteBookingDTO deleteBookingDTO) {
        bookingService.deleteBooking(deleteBookingDTO);
        return ResponseEntity.ok("Booking deleted successfully.");
    }

    /**
     * Endpoint para generar reservas aleatorias.
     *
     * @return ResponseEntity con un mensaje de éxito y un código de estado 200 (OK) si las reservas son generadas exitosamente.
     */
    @PostMapping("/generate")
    public ResponseEntity<String> generateRandomBookings() {
        return ResponseEntity.ok(bookingService.generateRandomBookings());
    }
}
