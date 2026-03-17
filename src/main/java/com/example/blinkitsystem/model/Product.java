package com.example.blinkitsystem.model;

public class Product {

    private int productId;
    private String name;
    private double price;
    private String category;
    private String image;

    public Product(int productId, String name, double price, String category, String image) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}