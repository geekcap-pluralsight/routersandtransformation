package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.FamilyReservationChannelGateway;
import com.pluralsight.globomantics.springintegration.model.FamilyReservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyReservationServiceImpl implements FamilyReservationService {
    private static final Logger logger = LogManager.getLogger(FamilyReservationServiceImpl.class);

    @Autowired
    private FamilyReservationChannelGateway familyReservationChannelGateway;

    @Override
    public void bookFamilyReservation(FamilyReservation familyReservation) {
        logger.info("Publishing family reservation {} to the family reservation channel",
                familyReservation.getFamilyId());
        familyReservationChannelGateway.publishFamilyReservation(familyReservation);
    }
}
