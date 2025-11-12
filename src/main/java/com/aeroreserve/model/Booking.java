package com.aeroreserve.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;

    @Positive(message = "Number of seats must be positive")
    private int seatsBooked;

    public Booking() {}

    public Booking(User user, Flight flight, int seatsBooked) {
        this.user = user;
        this.flight = flight;
        this.seatsBooked = seatsBooked;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }
}
