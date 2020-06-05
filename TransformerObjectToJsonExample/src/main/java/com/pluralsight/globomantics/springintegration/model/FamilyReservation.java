package com.pluralsight.globomantics.springintegration.model;

public class FamilyReservation {
    private Integer familyId;
    private String name;
    private String roomType;
    private int numberOfChildren;

    public FamilyReservation() {
    }

    public FamilyReservation(Integer familyId, String name, String roomType, int numberOfChildren) {
        this.familyId = familyId;
        this.name = name;
        this.roomType = roomType;
        this.numberOfChildren = numberOfChildren;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
}
