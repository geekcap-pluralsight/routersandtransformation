package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.SupportTicket;
import org.springframework.messaging.Message;

public interface LowRiskService {
    void handleSupportTicket(Message<SupportTicket> supportTicketMessage);
}
