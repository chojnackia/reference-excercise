package com.example.referenceexcercise.generator;

import com.example.referenceexcercise.module.apartment.Apartment;
import com.example.referenceexcercise.module.booking.Booking;
import com.example.referenceexcercise.module.booking.BookingDTO;
import com.example.referenceexcercise.module.owner.Owner;
import com.example.referenceexcercise.module.tenant.Tenant;
import lombok.Getter;

import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
public class ObjectGenerator {

    private final List<Apartment> apartmentList = ApartmentGenerator.generateApartmentList();
    private final List<Booking> bookingList = BookingGenerator.generateBookingList();
    private final List<BookingDTO> bookingDtoList = BookingGenerator.generateBookingDtoList();
    private final List<Owner> ownerList = OwnerGenerator.generateOwnerList();
    private final List<Tenant> tenantList = TenantGenerator.generateTenantList();

    public void generateDependencies(List<Apartment> apartments, List<Booking> bookings, List<Owner> owners, List<Tenant> tenants) {

        bookings.get(0).setApartment(apartments.get(0));
        bookings.get(1).setApartment(apartments.get(1));
        bookings.get(2).setApartment(apartments.get(0));

        bookings.get(0).setOwner(owners.get(0));
        bookings.get(1).setOwner(owners.get(1));
        bookings.get(2).setOwner(owners.get(0));

        bookings.get(0).setTenant(tenants.get(0));
        bookings.get(1).setTenant(tenants.get(1));
        bookings.get(2).setTenant(tenants.get(1));

        apartments.get(0).setOwner(owners.get(0));
        apartments.get(1).setOwner(owners.get(1));

    }

    public void generateDtoDependencies(List<Apartment> apartments, List<BookingDTO> bookingDtos) {

        bookingDtos.get(0).setPrice(apartments.get(0).getPriceForDay() * DAYS.between(bookingDtos.get(0).getCheckInDate(), bookingDtos.get(0).getCheckOutDate()));
        bookingDtos.get(1).setPrice(apartments.get(1).getPriceForDay() * DAYS.between(bookingDtos.get(1).getCheckInDate(), bookingDtos.get(1).getCheckOutDate()));
    }
}