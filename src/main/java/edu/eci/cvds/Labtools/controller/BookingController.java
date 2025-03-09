package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String[] checkAvailability(@RequestBody String date) {
        return null;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBooking(@RequestBody CreateBookingDTO createBookingDTO) {
        bookingService.createBooking(createBookingDTO);
    }

    @DeleteMapping
    public boolean deleteBooking(@RequestBody String bookingId) {
        return false;
    }

}
