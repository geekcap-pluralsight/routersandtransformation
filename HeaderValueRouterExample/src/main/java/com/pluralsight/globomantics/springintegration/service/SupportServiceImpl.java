package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.RouterChannelGateway;
import com.pluralsight.globomantics.springintegration.model.SupportTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SupportServiceImpl implements SupportService {

    @Autowired
    private RouterChannelGateway routerChannelGateway;

    @Override
    public void openTicket(String customerId, String riskLevel, String description) {
        Message<SupportTicket> message = MessageBuilder.withPayload(new SupportTicket(customerId, description))
                .setHeader("riskLevel", riskLevel)
                .build();

        routerChannelGateway.routeMessage(message);

    }
}
