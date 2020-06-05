package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;

public interface ReservationService {
    void bookGroupReservation(GroupReservation groupReservation, int totalMessages);
    void handleCompletedGroupReservation(GroupReservation groupReservation);
}
