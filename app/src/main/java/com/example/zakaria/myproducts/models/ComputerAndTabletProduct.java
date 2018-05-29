package com.example.zakaria.myproducts.models;

public class ComputerAndTabletProduct {

    private String name;
    private String location;
    private String condition;
    private String deviceType;
    private String brand;
    private String model;
    private String category;
    private String description;
    private String phoneNumber;
    private String price;
    private String posted;

    public ComputerAndTabletProduct() {

    }

    public ComputerAndTabletProduct(String name, String location, String condition, String deviceType, String brand, String model, String category, String description, String phoneNumber, String price, String posted) {
        this.name = name;
        this.location = location;
        this.condition = condition;
        this.deviceType = deviceType;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.posted = posted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }
}
