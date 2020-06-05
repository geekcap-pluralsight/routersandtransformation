package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.PurchaseOrder;
import org.springframework.messaging.Message;

public interface StandardPurchaseOrderService {
    void handlePayment(Message<PurchaseOrder> purchaseOrderMessage);
}
