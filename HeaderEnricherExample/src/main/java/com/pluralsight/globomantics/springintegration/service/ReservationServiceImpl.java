package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.HeaderEnricherChannelGateway;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    @Autowired
    private HeaderEnricherChannelGateway headerEnricherChannelGateway;

    @Override
    public void bookPartyReservation(PartyReservation partyReservation) {
        logger.info("Publishing party reservation {} to the header enricher channel", partyReservation.getPartyId());
        headerEnricherChannelGateway.publishPartyReservation(partyReservation);
    }
}
