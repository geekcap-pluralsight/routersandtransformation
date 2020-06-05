package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {
    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);

    @ServiceActivator(inputChannel = "auditChannel")
    @Override
    public void handlePartyReservation(Message<PartyReservation> partyReservationMessage) {
        logger.info("AuditService: Message Headers: {}", partyReservationMessage.getHeaders());
    }
}
