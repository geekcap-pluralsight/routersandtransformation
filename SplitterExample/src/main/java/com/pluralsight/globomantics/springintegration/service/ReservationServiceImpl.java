package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.GroupReservationChannelGateway;
import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    @Autowired
    private GroupReservationChannelGateway groupReservationChannelGateway;

    @Override
    public void bookGroupReservation(GroupReservation groupReservation) {
        logger.info("Publishing group reservation {} to the reservation channel", groupReservation.getReservationId());
        groupReservationChannelGateway.publishGroupReservation(MessageBuilder.withPayload(groupReservation).build());
    }
}
