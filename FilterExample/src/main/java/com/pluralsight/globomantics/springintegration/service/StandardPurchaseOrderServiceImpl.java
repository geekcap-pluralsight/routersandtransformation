package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.model.PurchaseOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class StandardPurchaseOrderServiceImpl implements StandardPurchaseOrderService {
    private static final Logger logger = LogManager.getLogger(StandardPurchaseOrderServiceImpl.class);

    @ServiceActivator(inputChannel = "standardPaymentChannel")
    @Override
    public void handlePayment(Message<PurchaseOrder> purchaseOrderMessage) {
        PurchaseOrder purchaseOrder = purchaseOrderMessage.getPayload();
        logger.info("Standard Purchase Order: Customer: {}, Amount: {}",
                purchaseOrder.getCustomerId(), purchaseOrder.getAmount());
    }
}
