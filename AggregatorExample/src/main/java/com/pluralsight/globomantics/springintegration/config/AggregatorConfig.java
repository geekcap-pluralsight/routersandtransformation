package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableIntegration
public class AggregatorConfig {
    private static final Logger logger = LogManager.getLogger(AggregatorConfig.class);
    @Bean public MessageChannel groupReservationChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel partyReservationChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel reservationAggregatorChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel reservationCompletedChannel() {
        return new DirectChannel();
    }
    @Splitter(inputChannel = "groupReservationChannel", outputChannel = "partyReservationChannel")
    public List<Message<PartyReservation>> splitter(GroupReservation groupReservation) {
        logger.info("Splitting group: {}", groupReservation.getReservationId());
        return groupReservation.getParties().stream()
                .map(partyReservation -> MessageBuilder.withPayload(partyReservation)
                        .setHeader("RESERVATION_ID", groupReservation.getReservationId())
                        .build()).collect(Collectors.toList());
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
