package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.GroupReservationChannelGateway;
import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    @Autowired private GroupReservationChannelGateway groupReservationChannellGateway;

    @Override
    public void bookGroupReservation(GroupReservation groupReservation, int totalMessages) {
        logger.info("Publishing group reservation: {}", groupReservation.getReservationId());
        groupReservationChannellGateway.publishGroupReservation(
                MessageBuilder.withPayload(groupReservation).setHeader("TOTAL_MESSAGES", 4).build());
    }

    @ServiceActivator(inputChannel = "reservationCompletedChannel")
    @Override
    public void handleCompletedGroupReservation(GroupReservation groupReservation) {
        logger.info("Group Reservation complete: {} - {} parties",
                groupReservation.getReservationId(), groupReservation.getParties().size());
        groupReservation.getParties().forEach(partyReservation ->
                logger.info("Confirmation number for party {}: {}",
                        partyReservation.getPartyId(), partyReservation.getConfirmationNumber())
        );
    }
}
