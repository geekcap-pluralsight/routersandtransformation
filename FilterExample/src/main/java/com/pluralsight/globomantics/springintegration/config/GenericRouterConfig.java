package com.pluralsight.globomantics.springintegration.config;

import com.pluralsight.globomantics.springintegration.model.PurchaseOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSelector;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.MessageRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableIntegration
public class GenericRouterConfig {
    @Bean public MessageChannel filterChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel routerChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel standardPaymentChannel() {
        return new DirectChannel();
    }
    @Bean public MessageChannel highCostPaymentChannel() {
        return new DirectChannel();
    }
    @Bean
    @Router(inputChannel = "routerChannel")
    public AbstractMessageRouter myCustomRouter() {
        return new AbstractMessageRouter() {
            @Override
            protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
                PurchaseOrder purchaseOrder = (PurchaseOrder)message.getPayload();
                if (purchaseOrder.getAmount() > 10000.0) {
                    return Arrays.asList(highCostPaymentChannel());
                }
                return Arrays.asList(standardPaymentChannel());
            }
        };
    }
    @Bean
    @Filter(inputChannel = "filterChannel", outputChannel = "routerChannel")
    public MessageSelector noCostFilter() {
        return message -> ((PurchaseOrder)message.getPayload()).getAmount() > 0.0;
    }
}
