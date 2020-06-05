package com.pluralsight.globomantics.springintegration.service;

import com.pluralsight.globomantics.springintegration.config.RouterChannelGateway;
import com.pluralsight.globomantics.springintegration.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private RouterChannelGateway routerChannelGateway;

    @Override
    public void submitPurchaseOrder(PurchaseOrder purchaseOrder) {
        routerChannelGateway.routeMessage(MessageBuilder.withPayload(purchaseOrder).build());
    }
}
