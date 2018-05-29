package com.example.zakaria.myproducts.models;

public class Product {
    String companyName;
    String productName;
    String price;

    public Product() {
    }

    public Product(String companyName, String productName, String price) {
        this.companyName = companyName;
        this.productName = productName;
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
