package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.ReservationConfirmation;
import org.springframework.messaging.Message;

public interface ReservationConfirmationService {
    Message<String> processString(Message<ReservationConfirmation> reservationConfirmationMessage);
}
