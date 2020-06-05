package com.pluralsight.globomantics.springintegration.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "familyReservationJsonChannelGateway", defaultRequestChannel = "familyReservationJsonChannel")
public interface FamilyReservationJsonChannelGateway {
    @Gateway
    void publishFamilyReservation(String familyReservationJson);
}
