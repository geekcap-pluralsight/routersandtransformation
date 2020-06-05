package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.FamilyReservation;

public interface FamilyReservationService {
    void bookFamilyReservation(FamilyReservation familyReservation);
    void bookFamilyReservationAsJson(String familyReservationJson);
}
