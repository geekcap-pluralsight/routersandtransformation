package com.pluralsight.globomantics.springintegration.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.HeaderFilter;
import org.springframework.integration.transformer.support.ExpressionEvaluatingHeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;

import java.util.Collections;
import java.util.Map;

@Configuration
@EnableIntegration
public class HeaderEnricherConfig {
    private static final Logger logger = LogManager.getLogger(HeaderEnricherConfig.class);

    @Bean public MessageChannel headerEnricherAddAuthChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel headerEnricherPartyOwnerChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel partyReservationChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel headerEnricherRemoveAuthChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel auditChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "headerEnricherAddAuthChannel", outputChannel = "headerEnricherPartyOwnerChannel")
    public HeaderEnricher headerEnricherAuthToken() {
        return new HeaderEnricher(Collections.singletonMap(
                "AUTH_TOKEN", new StaticHeaderValueMessageProcessor<>("12345")));
    }

    @Bean
    @Transformer(inputChannel = "headerEnricherPartyOwnerChannel", outputChannel = "partyReservationChannel")
    public HeaderEnricher headerEnricherPartyOwner() {
        // Create a SpEL expression that finds the "partyId" property in the message payload
        Expression expression = new SpelExpressionParser().parseExpression("payload.partyId");

        // Return a new HeaderEnricher with the SpEL header
        return new HeaderEnricher(Collections.singletonMap(
                "PARTY_OWNER", new ExpressionEvaluatingHeaderValueMessageProcessor<>(expression, String.class)));
    }

    @Bean
    @Transformer(inputChannel = "headerEnricherRemoveAuthChannel", outputChannel = "auditChannel")
    public HeaderFilter filterAuthToken() {
        return new HeaderFilter("AUTH_TOKEN");
    }
}
