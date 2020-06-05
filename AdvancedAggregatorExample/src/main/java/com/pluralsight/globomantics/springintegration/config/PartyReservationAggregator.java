package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PartyReservationAggregator {
    private static final Logger logger = LogManager.getLogger(PartyReservationAggregator.class);

    @CorrelationStrategy
    public Object correlateBy(Message<PartyReservation> partyReservation) {
        String reservationId = (String)partyReservation.getHeaders().get("RESERVATION_ID");
        logger.info("Correlating by reservation ID: {}", reservationId);
        return reservationId;
    }

    @ReleaseStrategy
    public boolean releaseChecker(List<Message<PartyReservation>> messages) {
        if (messages.size() > 0) {
            int totalMessages = (Integer)(messages.get(0).getHeaders().get("TOTAL_MESSAGES"));
            logger.info("ReleaseChecker: Total Messages: {}, current number of messages: {}", totalMessages, messages.size());
            if (messages.size() >= totalMessages) {
                logger.info("Release Checker: Releasing messages");
                return true;
            }
        }
        logger.info("Release Checker: Not releasing messages");
        return false;
    }

    @Aggregator(inputChannel = "reservationAggregatorChannel", outputChannel = "reservationCompletedChannel")
    public GroupReservation aggregator(List<Message<PartyReservation>> partyReservations) {
        GroupReservation groupReservation = new GroupReservation(
                (String) partyReservations.get(0).getHeaders().get("RESERVATION_ID"));
        groupReservation.getParties().addAll(partyReservations.stream()
                .map(Message::getPayload)
                .collect(Collectors.toList()));
        logger.info("Aggregating group: {} - {} parties",
                groupReservation.getReservationId(), groupReservation.getParties().size());
        return groupReservation;
    }
}
