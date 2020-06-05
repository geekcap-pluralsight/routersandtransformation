package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.ReservationRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ReservationRecordServiceImpl implements ReservationRecordService {
    private static final Logger logger = LogManager.getLogger(ReservationRecordServiceImpl.class);

    @ServiceActivator(inputChannel = "reservationRecordChannel")
    @Override
    public Message<String> processReservationRecord(Message<ReservationRecord> reservationRecordMessage) {
        ReservationRecord reservationRecord = reservationRecordMessage.getPayload();
        logger.info("Reservation Record: id={}, name={}", reservationRecord.getId(), reservationRecord.getName());
        return MessageBuilder.withPayload("SUCCESS").build();
    }
}
