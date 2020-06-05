package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.PurchaseOrder;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

//@MessagingGateway(name = "routerChannelGateway", defaultRequestChannel = "routerChannel")
@MessagingGateway(name = "routerChannelGateway", defaultRequestChannel = "filterChannel")
public interface RouterChannelGateway {
    @Gateway
    void routeMessage(Message<PurchaseOrder> message);
}
