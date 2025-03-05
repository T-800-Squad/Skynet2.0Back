package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryController {

    private UserRepository userRepository;

    public QueryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Booking[] queryUserBookings(@RequestParam String userId) {
        return null;
    }
}
