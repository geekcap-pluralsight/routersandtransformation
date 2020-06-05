package com.pluralsight.globomantics.springintegration.model;

public class ReservationConfirmation {
    private String reservationId;
    private String confirmationNumber;

    public ReservationConfirmation(String reservationId, String confirmationNumber) {
        this.reservationId = reservationId;
        this.confirmationNumber = confirmationNumber;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
