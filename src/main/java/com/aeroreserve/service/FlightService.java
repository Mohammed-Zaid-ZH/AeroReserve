package com.aeroreserve.service;

import com.aeroreserve.model.Flight;
import com.aeroreserve.repository.FlightRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository repo;
    public FlightService(FlightRepository repo) { this.repo = repo; }

    public Flight addFlight(Flight f) { return repo.save(f); }
    public List<Flight> getAllFlights() { return repo.findAll(); }
}
