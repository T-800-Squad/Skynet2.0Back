package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.dto.LabDTO;
import edu.eci.cvds.Labtools.dto.UserDTO;
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
    public LabDTO[] checkAvailability(@RequestParam String date) {
        return null;
    }

    @PostMapping
    public boolean createBooking(@RequestBody LabDTO labDTO, UserDTO userDTO) {
        return false;
    }

    @DeleteMapping
    public boolean deleteBooking(@RequestBody BookingDTO bookingDTO) {
        return false;
    }

}
