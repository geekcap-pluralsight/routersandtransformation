package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.ReservationAggregatorChannelGateway;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PartyReservationServiceImpl implements PartyReservationService {
    private static final Logger logger = LogManager.getLogger(PartyReservationServiceImpl.class);

    @Autowired
    private ReservationAggregatorChannelGateway reservationAggregatorChannelGateway;

    @ServiceActivator(inputChannel = "partyReservationChannel")
    @Override
    public void handlePartyReservation(Message<PartyReservation> partyReservationMessage) {
        PartyReservation partyReservation = partyReservationMessage.getPayload();
        logger.info("Book party reservation for {}: {}",
                partyReservation.getPartyId(), partyReservation.getName());

        // Generate a random confirmation number
        partyReservation.setConfirmationNumber(Integer.toString((int)(Math.random()*1000)));
        reservationAggregatorChannelGateway.publishPartyReservation(
                MessageBuilder
                        .withPayload(partyReservation)
                        .copyHeaders(partyReservationMessage.getHeaders())
                        .build());
    }
}
