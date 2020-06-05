package com.pluralsight.globomantics.springintegration.model;

public class SupportTicket {
    private String customer;
    private String description;

    public SupportTicket(String customer, String description) {
        this.customer = customer;
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
