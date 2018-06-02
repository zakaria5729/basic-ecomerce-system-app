package com.example.zakaria.myproducts.models;

public class MobileProduct {

    private String userName;
    private String productName;
    private String location;
    private String condition;
    private String brand;
    private String model;
    private String category;
    private String description;
    private String posted;
    private String phoneNumber;
    private String price;
    private String imageUrl;
    private String uploadId;

    public MobileProduct() {

    }

    public MobileProduct(String userName, String productName, String location, String condition, String brand, String model, String category, String description, String posted, String phoneNumber, String price, String imageUrl, String uploadId) {
        this.userName = userName;
        this.productName = productName;
        this.location = location;
        this.condition = condition;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.description = description;
        this.posted = posted;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.imageUrl = imageUrl;
        this.uploadId = uploadId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}