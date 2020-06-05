package com.pluralsight.globomantics.springintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class HeaderValueRouterConfig {

    @Bean
    public MessageChannel routerChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel lowRiskChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel highRiskChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "routerChannel")
    public HeaderValueRouter headerValueRouter() {
        HeaderValueRouter router = new HeaderValueRouter("riskLevel");
        router.setChannelMapping("low", "lowRiskChannel");
        router.setChannelMapping("high", "highRiskChannel");
        return router;
    }
}
