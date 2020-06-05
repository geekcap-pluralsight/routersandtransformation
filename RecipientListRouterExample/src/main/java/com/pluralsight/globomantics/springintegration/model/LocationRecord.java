package com.pluralsight.globomantics.springintegration.model;

public class LocationRecord {
    private String customerId;
    private String gpsLocation;

    public LocationRecord(String customerId, String gpsLocation) {
        this.customerId = customerId;
        this.gpsLocation = gpsLocation;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }
}
