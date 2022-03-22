package com.example.referenceexcercise.module.booking;

import com.example.referenceexcercise.module.apartment.Apartment;
import com.example.referenceexcercise.module.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("SELECT b FROM Booking b WHERE b.apartment = ?1 AND (b.checkInDate BETWEEN ?2 AND ?3 OR b.checkOutDate BETWEEN ?2 AND ?3)")
    List<Booking> findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(Apartment apartment, LocalDate checkInDate, LocalDate checkOutDate);

    List<Booking> findAllByApartmentEqualsAndCheckInDateIsBeforeAndCheckOutDateIsAfter(Apartment apartment, LocalDate checkInDate, LocalDate checkOutDate);

    List<Booking> findAllByApartmentEquals(Apartment apartment);

    List<Booking> findAllByTenantEquals(Tenant tenant);
}
