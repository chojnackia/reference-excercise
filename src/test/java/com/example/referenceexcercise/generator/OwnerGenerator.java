package com.example.referenceexcercise.generator;

import com.example.referenceexcercise.module.owner.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OwnerGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();

    static List<Owner> generateOwnerList() {
        List<Owner> owners = new ArrayList<>();

        Owner owner1 = Owner.builder()
                .id(id1)
                .name("qwerty132")
                .build();

        Owner owner2 = Owner.builder()
                .id(id2)
                .name("gradle321")
                .build();

        owners.add(owner1);
        owners.add(owner2);

        return owners;
    }
}
