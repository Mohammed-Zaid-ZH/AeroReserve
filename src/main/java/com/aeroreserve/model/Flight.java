package com.aeroreserve.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flight name is required")
    private String name;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @Positive(message = "Seats must be positive")
    private int seats;

    public Flight() {}

    public Flight(String name, String source, String destination, int seats) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
}
