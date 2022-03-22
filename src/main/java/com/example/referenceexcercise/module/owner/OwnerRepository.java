package com.example.referenceexcercise.module.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {

    Owner findByName(String name);
}
