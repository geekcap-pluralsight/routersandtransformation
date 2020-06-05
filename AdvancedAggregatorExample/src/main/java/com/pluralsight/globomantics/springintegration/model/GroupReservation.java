package com.pluralsight.globomantics.springintegration.model;

import java.util.ArrayList;
import java.util.List;

public class GroupReservation {
    private String reservationId;
    private List<PartyReservation> parties = new ArrayList<>();

    public GroupReservation(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public List<PartyReservation> getParties() {
        return parties;
    }
}
