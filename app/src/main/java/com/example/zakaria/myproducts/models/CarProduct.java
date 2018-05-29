package com.example.zakaria.myproducts.models;

public class CarProduct {

    private String name;
    private String location;
    private String condition;
    private String brand;
    private String model;
    private String transmission;
    private String bodyType;
    private String fuelType;
    private String registerYear;
    private String engineCapacity;
    private String category;
    private String kilometersRun;
    private String description;
    private String posted;
    private String phoneNumber;
    private String price;

    public CarProduct() {

    }

    public CarProduct(String name, String location, String condition, String brand, String model, String transmission, String bodyType, String fuelType, String registerYear, String engineCapacity, String category, String kilometersRun, String description, String posted, String phoneNumber, String price) {
        this.name = name;
        this.location = location;
        this.condition = condition;
        this.brand = brand;
        this.model = model;
        this.transmission = transmission;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.registerYear = registerYear;
        this.engineCapacity = engineCapacity;
        this.category = category;
        this.kilometersRun = kilometersRun;
        this.description = description;
        this.posted = posted;
        this.phoneNumber = phoneNumber;
        this.price = price;
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

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKilometersRun() {
        return kilometersRun;
    }

    public void setKilometersRun(String kilometersRun) {
        this.kilometersRun = kilometersRun;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
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
}
