package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.Booking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryController {


    @GetMapping
    public Booking[] queryUserBookings(@RequestParam String userId) {
        return null;
    }
}
