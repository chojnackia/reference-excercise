package com.example.referenceexcercise.module.booking;

import com.example.referenceexcercise.exception.ApartmentIsOccupiedException;
import com.example.referenceexcercise.generator.ObjectGenerator;
import com.example.referenceexcercise.module.apartment.Apartment;
import com.example.referenceexcercise.module.apartment.ApartmentRepository;
import com.example.referenceexcercise.module.owner.Owner;
import com.example.referenceexcercise.module.owner.OwnerRepository;
import com.example.referenceexcercise.module.tenant.Tenant;
import com.example.referenceexcercise.module.tenant.TenantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

class BookingServiceTest {

    private final ObjectGenerator generator = new ObjectGenerator();
    List<Apartment> apartments = generator.getApartmentList();
    List<Booking> bookings = generator.getBookingList();
    List<Owner> owners = generator.getOwnerList();
    List<Tenant> tenants = generator.getTenantList();
    List<BookingDTO> bookingDTOS = generator.getBookingDtoList();
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private BookingService bookingService;
    @Mock
    private ApartmentRepository apartmentRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private TenantRepository tenantRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(bookingRepository, apartmentRepository, ownerRepository, tenantRepository);
    }

    @Test
    void shouldAddBooking() throws ApartmentIsOccupiedException {

        BookingDTO bookingDTO = generator.getBookingDtoList().get(0);

        generator.generateDependencies(apartments, bookings, owners, tenants);
        generator.generateDtoDependencies(apartments, bookingDTOS);

        Apartment expectedApartment = apartments.get(0);
        Owner expectedOwner = owners.get(0);
        Tenant expectedTenant = tenants.get(0);

        Mockito.when(apartmentRepository.findByNameIgnoreCase(apartments.get(0).getName())).thenReturn(expectedApartment);
        Mockito.when(ownerRepository.findByName(owners.get(0).getName())).thenReturn(expectedOwner);
        Mockito.when(tenantRepository.findByNameIgnoreCase(tenants.get(0).getName())).thenReturn(expectedTenant);

        BookingDTO addedBooking = bookingService.addBooking(bookingDTO);
        bookingDTO.setId(addedBooking.getId());

        assertThat(addedBooking).isEqualTo(bookingDTO);
        Mockito.verify(bookingRepository, times(1)).save(Mockito.any(Booking.class));
    }

    @Test
    void shouldThrowAppartmentIsOccupiedException() {

        BookingDTO bookingDto = generator.getBookingDtoList().get(0);
        Booking bookingInDatabase = bookings.get(0);

        generator.generateDependencies(apartments, bookings, owners, tenants);
        generator.generateDtoDependencies(apartments, bookingDTOS);

        Apartment expectedApartment = apartments.get(0);
        Owner expectedOwner = owners.get(0);
        Tenant expectedTenant = tenants.get(0);

        Mockito.when(bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(apartments.get(0), bookingDTOS.get(0).getCheckInDate(), bookingDTOS.get(0).getCheckOutDate())).thenReturn(Collections.singletonList(bookingInDatabase));
        Mockito.when(apartmentRepository.findByNameIgnoreCase(apartments.get(0).getName())).thenReturn(expectedApartment);
        Mockito.when(ownerRepository.findByName(owners.get(0).getName())).thenReturn(expectedOwner);
        Mockito.when(tenantRepository.findByNameIgnoreCase(tenants.get(0).getName())).thenReturn(expectedTenant);

        ApartmentIsOccupiedException e = Assertions.assertThrows(ApartmentIsOccupiedException.class, () ->
                bookingService.addBooking(bookingDto));

        assertThat(e.getMessage()).isEqualTo("Apartment is occupied between " + bookingDTOS.get(0).getCheckInDate() + " - " + bookingDTOS.get(0).getCheckOutDate());
    }
}