package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    private UserRepository userRepository;

    public QueryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Booking[] queryUserBookings(String userId) {
        return null;
    }
}
