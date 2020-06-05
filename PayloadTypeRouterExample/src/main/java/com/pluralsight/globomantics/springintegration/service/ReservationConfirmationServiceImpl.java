package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.ReservationConfirmation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class ReservationConfirmationServiceImpl implements ReservationConfirmationService {
    private static final Logger logger = LogManager.getLogger(ReservationConfirmationServiceImpl.class);

    @ServiceActivator(inputChannel = "reservationConfirmationChannel")
    @Override
    public Message<String> processString(Message<ReservationConfirmation> reservationConfirmationMessage) {
        ReservationConfirmation reservationConfirmation = reservationConfirmationMessage.getPayload();
        logger.info("ReservationConfirmation: reservationId={}, confirmationNumber={}",
                reservationConfirmation.getReservationId(), reservationConfirmation.getConfirmationNumber());
        return MessageBuilder.withPayload("SUCCESS").build();
    }
}
