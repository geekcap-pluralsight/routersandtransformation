package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.ReservationConfirmation;
import com.pluralsight.globomantics.springintegration.model.ReservationRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.MessageRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class PayloadTypeRouterConfig {
    @Bean
    public MessageChannel routerChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel reservationRecordChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel reservationConfirmationChannel() {
        return new DirectChannel();
    }
    @Bean
    @ServiceActivator(inputChannel = "routerChannel")
    public PayloadTypeRouter payloadTypeRouter() {
        PayloadTypeRouter router = new PayloadTypeRouter();
        router.setChannelMapping(ReservationRecord.class.getName(), "reservationRecordChannel");
        router.setChannelMapping(ReservationConfirmation.class.getName(), "reservationConfirmationChannel");
        return router;
    }
}
