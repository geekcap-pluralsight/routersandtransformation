package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.RouterChannelGateway;
import com.pluralsight.globomantics.springintegration.model.LocationRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger logger = LogManager.getLogger(LocationServiceImpl.class);

    @Autowired
    private RouterChannelGateway routerChannelGateway;

    @Override
    public void saveLocation(String gpsLocation, String customerId) {
        logger.info("Saving location for customer {}: {}", customerId, gpsLocation);
        Message<LocationRecord> message = MessageBuilder
                .withPayload(new LocationRecord(customerId, gpsLocation))
                .build();
        routerChannelGateway.routeMessage(message);
    }
}
