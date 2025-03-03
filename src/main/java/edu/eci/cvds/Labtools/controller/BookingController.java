package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.repository.BookingRepository;
import edu.eci.cvds.Labtools.repository.LabRepository;
import edu.eci.cvds.Labtools.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private LabRepository labRepository;
    private BookingRepository bookingRepository;
    private UserRepository userRepository;

    public BookingController(LabRepository labRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        this.labRepository = labRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public boolean[][] checkAvailability() {
        return new boolean[6][8];
    }

    @PostMapping
    public String createBooking(@RequestParam String userId,@RequestParam String labId, @RequestParam int day,@RequestParam int timeLine){
        return userId+labId+day+timeLine;
    }

    public String deleteBooking(String userId, String bookingId){
        return null;
    }

    public String[] checkAvailabilityLabExactMoment(int day, int timeLine){
        return null;
    }

}
