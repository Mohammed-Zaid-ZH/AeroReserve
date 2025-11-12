package com.aeroreserve.service;

import com.aeroreserve.model.*;
import com.aeroreserve.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final FlightRepository flightRepo;

    public BookingService(BookingRepository bookingRepo, FlightRepository flightRepo) {
        this.bookingRepo = bookingRepo;
        this.flightRepo = flightRepo;
    }

    public Booking bookFlight(Booking booking) {
        // Fetch actual flight from DB using ID
        Flight f = flightRepo.findById(booking.getFlight().getId())
                             .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        if (f.getSeats() < booking.getSeatsBooked())
            throw new IllegalArgumentException("Not enough seats available");

        // Deduct seats and save
        f.setSeats(f.getSeats() - booking.getSeatsBooked());
        flightRepo.save(f);

        booking.setFlight(f); // attach real flight object
        return bookingRepo.save(booking);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

}
