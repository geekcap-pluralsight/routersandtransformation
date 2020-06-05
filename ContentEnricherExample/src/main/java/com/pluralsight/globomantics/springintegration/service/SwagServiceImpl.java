package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.SwagChannelGateway;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwagServiceImpl implements SwagService {
    private static final Logger logger = LogManager.getLogger(SwagServiceImpl.class);

    @Autowired
    private SwagChannelGateway swagChannelGateway;

    @Override
    public void sendSwag(PartyReservation partyReservation) {
        logger.info("Publishing message to send swag for partyId: {}", partyReservation.getPartyId());
        swagChannelGateway.sendSwag(partyReservation);
    }
}
