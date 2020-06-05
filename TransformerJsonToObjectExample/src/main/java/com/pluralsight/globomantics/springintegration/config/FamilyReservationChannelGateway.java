package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.FamilyReservation;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "familyReservationChannelGateway", defaultRequestChannel = "familyReservationChannel")
public interface FamilyReservationChannelGateway {
    @Gateway
    void publishFamilyReservation(FamilyReservation familyReservation);
}
