package com.example.referenceexcercise.module.booking;

import com.example.referenceexcercise.exception.ApartmentIsOccupiedException;
import com.example.referenceexcercise.exception.ApartmentNotFoundException;
import com.example.referenceexcercise.exception.TenantNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/")
    public BookingDTO addBooking(@RequestBody BookingDTO bookingDTO) throws ApartmentIsOccupiedException {
        return bookingService.addBooking(bookingDTO);
    }

    @GetMapping("/{name}")
    public List<BookingDTO> findAllBookingsForApartment(@PathVariable String name) throws ApartmentNotFoundException {
        return bookingService.findAllBookingsByApartment(name);
    }

    @GetMapping("/tenants/{name}")
    public List<BookingDTO> findAllBookingsForTenant(@PathVariable String name) throws TenantNotFoundException {
        return bookingService.findAllBookingsByTenant(name);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@RequestBody BookingDTO bookingDTO, @PathVariable UUID id) {
        return bookingService.updateBooking(bookingDTO, id);
    }
}
