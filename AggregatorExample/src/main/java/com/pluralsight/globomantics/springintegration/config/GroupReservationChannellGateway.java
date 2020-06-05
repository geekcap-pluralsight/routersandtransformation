package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "groupReservationChannelGateway", defaultRequestChannel = "groupReservationChannel")
public interface GroupReservationChannellGateway {
    @Gateway
    void publishGroupReservation(GroupReservation message);
}
