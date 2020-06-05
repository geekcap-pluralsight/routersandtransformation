package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.Address;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import org.springframework.messaging.Message;

public interface PartyRetrievalService {
    Address getPartyAddress(String partyId);
}
