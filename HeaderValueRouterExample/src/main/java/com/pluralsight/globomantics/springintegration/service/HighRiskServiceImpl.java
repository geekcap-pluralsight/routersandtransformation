package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.SupportTicket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class HighRiskServiceImpl implements HighRiskService {
    private static final Logger logger = LogManager.getLogger(HighRiskServiceImpl.class);

    @ServiceActivator(inputChannel = "highRiskChannel")
    @Override
    public void handleSupportTicket(Message<SupportTicket> supportTicketMessage) {
        SupportTicket supportTicket = supportTicketMessage.getPayload();
        logger.info("Handle high risk support ticket for customer '{}': {}",
                supportTicket.getCustomer(), supportTicket.getDescription());
    }
}
