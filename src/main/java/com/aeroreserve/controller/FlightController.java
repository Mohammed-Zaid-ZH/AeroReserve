package com.aeroreserve.controller;

import com.aeroreserve.model.Flight;
import com.aeroreserve.service.FlightService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")  
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return service.getAllFlights();
    }

    @PostMapping("/add")
    public Flight addFlight(@RequestBody Flight f) {
        return service.addFlight(f);
    }
}

