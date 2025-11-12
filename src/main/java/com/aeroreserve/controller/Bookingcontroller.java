package com.aeroreserve.controller;

import com.aeroreserve.model.Booking;
import com.aeroreserve.service.BookingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*")
public class Bookingcontroller {
    private final BookingService service;
    public Bookingcontroller(BookingService service) { this.service = service; }

    @PostMapping("/book")
    public Booking book(@RequestBody Booking booking) { return service.bookFlight(booking); }

    @GetMapping("/user/{id}")
    public List<Booking> getUserBookings(@PathVariable Long id) {
        return service.getBookingsByUser(id);
    }
    
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

}
