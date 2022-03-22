package com.example.referenceexcercise.module.booking;

import com.example.referenceexcercise.exception.ApartmentIsOccupiedException;
import com.example.referenceexcercise.exception.ApartmentNotFoundException;
import com.example.referenceexcercise.exception.TenantNotFoundException;
import com.example.referenceexcercise.module.apartment.Apartment;
import com.example.referenceexcercise.module.apartment.ApartmentRepository;
import com.example.referenceexcercise.module.owner.Owner;
import com.example.referenceexcercise.module.owner.OwnerRepository;
import com.example.referenceexcercise.module.tenant.Tenant;
import com.example.referenceexcercise.module.tenant.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@AllArgsConstructor
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ApartmentRepository apartmentRepository;
    private final OwnerRepository ownerRepository;
    private final TenantRepository tenantRepository;

    public BookingDTO addBooking(BookingDTO bookingDTO) throws ApartmentIsOccupiedException {

        Apartment apartment = apartmentRepository.findByNameIgnoreCase(bookingDTO.getApartmentName());
        Owner owner = ownerRepository.findByName(apartment.getOwner().getName());
        Tenant tenant = tenantRepository.findByNameIgnoreCase(bookingDTO.getTenantName());

        long lengthOfStay = DAYS.between(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());

        Booking booking = BookingConverter.convertToBooking(bookingDTO, apartment, tenant, owner);

        if (Objects.nonNull(tenant)) {
            booking.setTenant(tenant);
        } else {
            Tenant addTenant = Tenant.builder()
                    .id(UUID.randomUUID())
                    .name(bookingDTO.getTenantName())
                    .build();
            booking.setTenant(addTenant);
        }

        booking.setPrice(countPriceForBooking(apartment.getPriceForDay(), lengthOfStay));
        booking.setId(UUID.randomUUID());

        if (isApartmentOccupied(bookingDTO)) {
            bookingRepository.save(booking);
            return BookingConverter.convertToBookingDto(booking);
        } else
            throw new ApartmentIsOccupiedException("Apartment is occupied between " + bookingDTO.getCheckInDate() + " - " + bookingDTO.getCheckOutDate());
    }

    private Boolean isApartmentOccupied(BookingDTO bookingDto) {
        Apartment apartment = apartmentRepository.findByNameIgnoreCase(bookingDto.getApartmentName());

        List<Booking> findDatesBetween = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
                apartment,
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        List<Booking> findDatesIncluded = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBeforeAndCheckOutDateIsAfter(
                apartment,
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        return (findDatesBetween.isEmpty() && findDatesIncluded.isEmpty());
    }

    public List<BookingDTO> findAllBookingsByApartment(String name) throws ApartmentNotFoundException {
        Apartment apartment = apartmentRepository.findByNameIgnoreCase(name);

        if (Objects.isNull(apartment)) {
            throw new ApartmentNotFoundException("Apartment " + name + " is not booked");
        }

        return bookingRepository.findAllByApartmentEquals(apartment)
                .stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

    private long countPriceForBooking(int priceForOneDay, long lengthOfStay) {
        return priceForOneDay * lengthOfStay;
    }

    public List<BookingDTO> findAllBookingsByTenant(String name) throws TenantNotFoundException {
        Tenant tenant = tenantRepository.findByNameIgnoreCase(name);

        if (Objects.isNull(tenant)) {
            throw new TenantNotFoundException("Tenant " + name + " does not have any apartment booked");
        }
        return bookingRepository.findAllByTenantEquals(tenant)
                .stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

    public Booking updateBooking(BookingDTO bookingDTO) {
        Apartment apartment = apartmentRepository.findByNameIgnoreCase(bookingDTO.getApartmentName());
        Owner owner = ownerRepository.findByName(apartment.getOwner().getName());
        Tenant tenant = tenantRepository.findByNameIgnoreCase(bookingDTO.getTenantName());

        long lengthOfStay = DAYS.between(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());

        Booking booking = BookingConverter.convertToBooking(bookingDTO, apartment, tenant, owner);
        booking.setPrice(countPriceForBooking(apartment.getPriceForDay(), lengthOfStay));
        booking.setOwner(owner);

        return bookingRepository.save(booking);
    }

}
