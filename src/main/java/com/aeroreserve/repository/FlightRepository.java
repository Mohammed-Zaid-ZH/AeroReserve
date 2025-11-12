package com.aeroreserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aeroreserve.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {}
