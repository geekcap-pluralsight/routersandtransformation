package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "headerEnricherAddAuthChannelGateway", defaultRequestChannel = "headerEnricherAddAuthChannel")
public interface HeaderEnricherChannelGateway {
    @Gateway
    void publishPartyReservation(PartyReservation partyReservation);
}
