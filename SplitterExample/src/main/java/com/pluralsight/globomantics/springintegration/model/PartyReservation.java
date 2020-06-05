package com.pluralsight.globomantics.springintegration.model;

public class PartyReservation {
    private Integer partyId;
    private String roomType;
    private String name;

    public PartyReservation(Integer partyId, String roomType, String name) {
        this.partyId = partyId;
        this.roomType = roomType;
        this.name = name;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
