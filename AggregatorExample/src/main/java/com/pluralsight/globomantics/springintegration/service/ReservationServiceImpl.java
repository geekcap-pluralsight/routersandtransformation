package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.GroupReservationChannellGateway;
import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    @Autowired
    private GroupReservationChannellGateway groupReservationChannellGateway;

    @Override
    public void bookGroupReservation(GroupReservation groupReservation) {
        logger.info("Publishing group reservation: {}", groupReservation.getReservationId());
        groupReservationChannellGateway.publishGroupReservation(groupReservation);
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
