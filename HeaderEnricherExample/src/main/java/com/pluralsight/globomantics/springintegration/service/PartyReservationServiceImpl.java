package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.AuditChannelGateway;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PartyReservationServiceImpl implements PartyReservationService {
    private static final Logger logger = LogManager.getLogger(PartyReservationServiceImpl.class);

    @Autowired
    private AuditChannelGateway auditChannelGateway;

    @ServiceActivator(inputChannel = "partyReservationChannel")
    @Override
    public void handlePartyReservation(Message<PartyReservation> partyReservationMessage) {
        logger.info("Message Headers: {}", partyReservationMessage.getHeaders());

        String authToken = (String)partyReservationMessage.getHeaders().get("AUTH_TOKEN");
        if (authToken.equals("12345")) {
            PartyReservation partyReservation = partyReservationMessage.getPayload();
            logger.info("Book party reservation for {}: {}",
                    partyReservation.getPartyId(), partyReservation.getName());

            // Publish the party reservation to the audit channel
            auditChannelGateway.publishPartyReservation(partyReservationMessage);
        } else {
            logger.error("Invalid authorization token");
        }
    }
}
