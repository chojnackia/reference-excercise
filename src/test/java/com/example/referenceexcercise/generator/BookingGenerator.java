package com.example.referenceexcercise.generator;

import com.example.referenceexcercise.module.booking.Booking;
import com.example.referenceexcercise.module.booking.BookingDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();
    private static final UUID id3 = UUID.randomUUID();

    static List<Booking> generateBookingList() {
        List<Booking> bookings = new ArrayList<>();

        Booking booking1 = Booking.builder()
                .id(id1)
                .owner(null)
                .tenant(null)
                .price(0)
                .apartment(null)
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .build();

        Booking booking2 = Booking.builder()
                .id(id2)
                .owner(null)
                .tenant(null)
                .price(0)
                .apartment(null)
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .build();

        Booking booking3 = Booking.builder()
                .id(id3)
                .owner(null)
                .tenant(null)
                .price(0)
                .apartment(null)
                .checkInDate(LocalDate.of(2021, 11, 1))
                .checkOutDate(LocalDate.of(2021, 11, 3))
                .build();

        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        return bookings;
    }

    static List<BookingDTO> generateBookingDtoList() {
        List<BookingDTO> bookingDtos = new ArrayList<>();

        BookingDTO bookingDto1 = BookingDTO.builder()
                .id(id1)
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .price(0)
                .tenantName("Morty25")
                .apartmentName("Krysztalowy")
                .build();

        BookingDTO bookingDto2 = BookingDTO.builder()
                .id(id2)
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .price(0)
                .tenantName("maven32")
                .apartmentName("Szmaragdowy")
                .build();

        BookingDTO bookingDto3 = BookingDTO.builder()
                .id(id3)
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .price(0)
                .tenantName("random99")
                .apartmentName("Ametystowy")
                .build();

        bookingDtos.add(bookingDto1);
        bookingDtos.add(bookingDto2);
        bookingDtos.add(bookingDto3);

        return bookingDtos;
    }
}
