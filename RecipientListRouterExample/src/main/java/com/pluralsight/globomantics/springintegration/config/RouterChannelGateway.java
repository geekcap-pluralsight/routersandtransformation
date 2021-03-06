package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.LocationRecord;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "routerChannelGateway", defaultRequestChannel = "routerChannel")
public interface RouterChannelGateway {
    @Gateway
    void routeMessage(Message<LocationRecord> message);
}
