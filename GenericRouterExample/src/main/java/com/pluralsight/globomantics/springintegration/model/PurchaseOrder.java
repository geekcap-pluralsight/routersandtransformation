package com.pluralsight.globomantics.springintegration.model;

public class PurchaseOrder {
    private String customerId;
    private Float amount;

    public PurchaseOrder(String customerId, Float amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
