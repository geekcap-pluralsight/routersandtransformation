package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.ReservationRecord;
import org.springframework.messaging.Message;

public interface ReservationRecordService {
    Message<String> processReservationRecord(Message<ReservationRecord> reservationRecordMessage);
}
