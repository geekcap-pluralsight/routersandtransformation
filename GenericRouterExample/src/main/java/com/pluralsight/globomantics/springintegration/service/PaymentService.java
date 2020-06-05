package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.PurchaseOrder;

public interface PaymentService {
    void submitPurchaseOrder(PurchaseOrder purchaseOrder);
}
