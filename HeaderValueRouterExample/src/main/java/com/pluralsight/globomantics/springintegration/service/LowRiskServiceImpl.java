package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.SupportTicket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class LowRiskServiceImpl implements LowRiskService {

    private static final Logger logger = LogManager.getLogger(LowRiskServiceImpl.class);

    @ServiceActivator(inputChannel = "lowRiskChannel")
    @Override
    public void handleSupportTicket(Message<SupportTicket> supportTicketMessage) {
        SupportTicket supportTicket = supportTicketMessage.getPayload();
        logger.info("Handle low risk support ticket for customer '{}': {}",
                supportTicket.getCustomer(), supportTicket.getDescription());
    }
}
