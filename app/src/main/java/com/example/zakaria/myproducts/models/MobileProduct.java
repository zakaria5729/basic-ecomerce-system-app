package com.example.zakaria.myproducts.models;

public class MobileProduct {

    private String name;
    private String location;
    private String condition;
    private String brand;
    private String model;
    private String features;
    private String edition;
    private String category;
    private String description;
    private String posted;
    private String phoneNumber;
    private String price;

    public MobileProduct() {

    }

    public MobileProduct(String name, String location, String condition, String brand, String model, String features, String edition, String category, String description, String posted, String phoneNumber, String price) {
        this.name = name;
        this.location = location;
        this.condition = condition;
        this.brand = brand;
        this.model = model;
        this.features = features;
        this.edition = edition;
        this.category = category;
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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
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
