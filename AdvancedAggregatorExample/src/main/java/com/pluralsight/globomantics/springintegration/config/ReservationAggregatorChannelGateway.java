package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "reservationAggregatorChannelGateway", defaultRequestChannel = "reservationAggregatorChannel")
public interface ReservationAggregatorChannelGateway {
    @Gateway
    void publishPartyReservation(Message<PartyReservation> partyReservationMessage);
}
