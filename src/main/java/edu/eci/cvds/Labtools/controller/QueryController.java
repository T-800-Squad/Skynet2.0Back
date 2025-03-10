package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.service.QueryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/query")
public class QueryController {
    private QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public BookingDTO[] findBookingsById(@RequestParam String userName) {
        return null;
    }
}
