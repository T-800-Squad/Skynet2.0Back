package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.dto.DeleteBookingDTO;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<Booking> createBooking(@RequestBody CreateBookingDTO createBookingDTO) {
        Booking booking = bookingService.createBooking(createBookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }


    @DeleteMapping()
    public ResponseEntity<String> deleteBooking(@RequestBody DeleteBookingDTO deleteBookingDTO) {
        bookingService.deleteBooking(deleteBookingDTO);
        return ResponseEntity.ok("Booking deleted successfully.");
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateRandomBookings() {
        return ResponseEntity.ok(bookingService.generateRandomBookings());
    }
}
