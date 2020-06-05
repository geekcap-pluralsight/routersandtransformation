package com.pluralsight.globomantics.springintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class RecipientListRouterConfig {

    @Bean
    public MessageChannel routerChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel auditChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel machineLearningChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "routerChannel")
    @Bean
    public RecipientListRouter router() {
        RecipientListRouter router = new RecipientListRouter();
        router.addRecipient("auditChannel");
        router.addRecipient("machineLearningChannel");
        return router;
    }
}
