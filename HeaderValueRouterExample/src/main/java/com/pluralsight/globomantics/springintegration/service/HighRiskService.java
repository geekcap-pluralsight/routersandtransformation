package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.SupportTicket;
import org.springframework.messaging.Message;

public interface HighRiskService {
    void handleSupportTicket(Message<SupportTicket> supportTicketMessage);
}
