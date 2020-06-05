package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.LocationRecord;
import org.springframework.messaging.Message;

public interface MachineLearningService {
    void handleLocationRecord(Message<LocationRecord> locationMessage);
}
