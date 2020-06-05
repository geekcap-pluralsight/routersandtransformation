package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.*;
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
    public List<Message<PartyReservation>> splitter(Message<GroupReservation> groupReservationMessage) {
        GroupReservation groupReservation = groupReservationMessage.getPayload();

        int totalMessages = (Integer)(groupReservationMessage.getHeaders().get("TOTAL_MESSAGES"));
        logger.info("Splitting group: {}, total messages in group: {}",
                groupReservation.getReservationId(), totalMessages);
        return groupReservation.getParties().stream()
                .map(partyReservation -> MessageBuilder.withPayload(partyReservation)
                        .setHeader("RESERVATION_ID", groupReservation.getReservationId())
                        .setHeader("TOTAL_MESSAGES", totalMessages)
                        .build()).collect(Collectors.toList());
    }
}
