package com.example.referenceexcercise.module.apartment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

    Apartment findByNameIgnoreCase(String name);
}
