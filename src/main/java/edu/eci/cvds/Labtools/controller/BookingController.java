package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.BookingDTO;
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
    public boolean[][] checkAvailability() {
        return new boolean[6][8];
    }

    @PostMapping
    public String createBooking(@RequestBody BookingDTO bookingDTO) {
        return null;
    }

    public String deleteBooking(BookingDTO bookingDTO) {
        return null;
    }

    public String[] checkAvailabilityLabExactMoment(int day, int timeLine){
        return null;
    }

}
