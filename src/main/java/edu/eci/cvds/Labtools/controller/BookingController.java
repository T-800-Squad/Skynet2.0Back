package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String[] checkAvailability(@RequestParam String date) {
        return null;
    }

    @PostMapping
    public boolean createBooking(@RequestBody CreateBookingDTO createBookingDTO) {
        return false;
    }

    @DeleteMapping
    public boolean deleteBooking(@RequestParam String bookingId) {
        return false;
    }

}