package com.example.referenceexcercise.generator;

import com.example.referenceexcercise.module.apartment.Apartment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApartmentGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();
    private static final UUID id3 = UUID.randomUUID();

    static List<Apartment> generateApartmentList() {
        List<Apartment> apartments = new ArrayList<>();
        Apartment apartments1 = new Apartment(id1, "Krysztalowy", 30000, 65, "Random opis 1", null);
        Apartment apartments2 = new Apartment(id2, "Szmaragdowy", 12000, 30, "Random opis 2", null);
        Apartment apartments3 = new Apartment(id3, "Ametystowy", 21000, 43, "Random opis 3", null);

        apartments.add(apartments1);
        apartments.add(apartments2);
        apartments.add(apartments3);
        return apartments;
    }
}
