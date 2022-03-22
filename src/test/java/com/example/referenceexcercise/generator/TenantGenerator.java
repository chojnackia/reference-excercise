package com.example.referenceexcercise.generator;

import com.example.referenceexcercise.module.tenant.Tenant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TenantGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();

    static List<Tenant> generateTenantList() {
        List<Tenant> tenants = new ArrayList<>();

        Tenant tenant1 = Tenant.builder()
                .id(id1)
                .name("Morty25")
                .build();

        Tenant tenant2 = Tenant.builder()
                .id(id2)
                .name("maven32")
                .build();

        tenants.add(tenant1);
        tenants.add(tenant2);

        return tenants;
    }
}
