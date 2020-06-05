package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class PartyRetrievalServiceImpl implements PartyRetrievalService {
    private static final Logger logger = LogManager.getLogger(PartyRetrievalServiceImpl.class);

    @ServiceActivator(inputChannel = "partyReservationEnricherChannel")
    @Override
    public Address getPartyAddress(String partyId) {
        logger.info("PartyRetrievalService - Retrieve address for partyId: {}", partyId);
        return new Address("123 Some Street", "Some City", "ST", "12345");
    }
}
