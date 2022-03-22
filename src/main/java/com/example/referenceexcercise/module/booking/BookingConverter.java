package com.example.referenceexcercise.module.booking;

import com.example.referenceexcercise.module.apartment.Apartment;
import com.example.referenceexcercise.module.owner.Owner;
import com.example.referenceexcercise.module.tenant.Tenant;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookingConverter {

    public static BookingDTO convertToBookingDto(Booking booking) {
        if (booking == null) return null;
        return BookingDTO.builder()
                .id(booking.getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .apartmentName(booking.getApartment().getName())
                .tenantName(booking.getTenant().getName())
                .price(booking.getPrice())
                .build();
    }

    public static Booking convertToBooking(BookingDTO bookingDto, Apartment apartment, Tenant tenant, Owner owner) {
        if (bookingDto == null) return null;
        return Booking.builder()
                .id(bookingDto.getId())
                .checkInDate(bookingDto.getCheckInDate())
                .checkOutDate(bookingDto.getCheckOutDate())
                .owner(owner)
                .tenant(tenant)
                .price(bookingDto.getPrice())
                .apartment(apartment)
                .build();
    }
}
