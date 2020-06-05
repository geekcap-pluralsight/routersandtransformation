package com.pluralsight.globomantics.springintegration.config;

import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.transformer.ContentEnricher;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class ContentEnricherConfig {
    private static final Logger logger = LogManager.getLogger(ContentEnricherConfig.class);

    @Bean
    public MessageChannel swagChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageChannel partyReservationEnricherChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel swagFulfillmentServiceChannel() {
        return new DirectChannel();
    }

    @Bean
    public SpelExpressionParser spelExpressionParser() {
        return new SpelExpressionParser();
    }

    @Bean
    @ServiceActivator(inputChannel = "swagChannel")
    public ContentEnricher contentEnricher(SpelExpressionParser spelExpressionParser) {
        ContentEnricher contentEnricher = new ContentEnricher();

        // Set the output channel to which we'll publish our enriched party reservation
        contentEnricher.setOutputChannelName("swagFulfillmentServiceChannel");

        // Define the request channel, which will provide our enrichment data
        contentEnricher.setRequestChannelName("partyReservationEnricherChannel");

        // Define the expression that extracts information to send to the partyReservationEnricherChannel
        contentEnricher.setRequestPayloadExpression(spelExpressionParser.parseExpression("payload.partyId"));

        // Define how to extract fields from the partyReservationEnricherChannel response and map those to fields
        // in our message payload
        contentEnricher.setPropertyExpressions(ImmutableMap.of(
                "address", spelExpressionParser.parseExpression("payload.address"),
                "city", spelExpressionParser.parseExpression("payload.city"),
                "state", spelExpressionParser.parseExpression("payload.state"),
                "zip", spelExpressionParser.parseExpression("payload.zip")
        ));
        return contentEnricher;
    }
}
