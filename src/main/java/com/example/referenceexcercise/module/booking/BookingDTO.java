package com.example.referenceexcercise.module.booking;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class BookingDTO {

    private UUID id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String tenantName;
    private long price;
    private String apartmentName;
}
