package com.example.zakaria.myproducts.models;

public class UpdateProfile {

    private String userName;
    private String phoneNumber;
    private String location;

    public UpdateProfile() {

    }

    public UpdateProfile(String userName, String phoneNumber, String location) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
