package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.LocationRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class MachineLearningServiceImpl implements MachineLearningService {
    private static final Logger logger = LogManager.getLogger(MachineLearningServiceImpl.class);

    @ServiceActivator(inputChannel = "machineLearningChannel")
    @Override
    public void handleLocationRecord(Message<LocationRecord> locationMessage) {
        LocationRecord locationRecord = locationMessage.getPayload();
        logger.info("MachineLearningService - Location record: customerId='{}', gpsLocation={}",
                locationRecord.getCustomerId(), locationRecord.getGpsLocation());
    }
}
