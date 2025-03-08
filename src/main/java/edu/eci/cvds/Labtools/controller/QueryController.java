package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private BasicQueryService basicQueryService;

    @GetMapping
    public Booking[] queryUserBookings(@RequestParam String userId) {
        return basicQueryService.queryUserBookings(userId);
    }
}