package com.example.referenceexcercise.module.tenant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    Tenant findByNameIgnoreCase(String name);
}
