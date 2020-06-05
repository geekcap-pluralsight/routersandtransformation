package com.pluralsight.globomantics.springintegration.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class JsonResultsServiceImpl implements JsonResultsService {
    private static final Logger logger = LogManager.getLogger(JsonResultsServiceImpl.class);
    @Override
    @ServiceActivator(inputChannel = "jsonResultsChannel")
    public void jsonResults(String results) {
        logger.info("JSON Results: {}", results);
    }
}
