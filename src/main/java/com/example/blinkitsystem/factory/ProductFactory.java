package com.example.blinkitsystem.factory;

import com.example.blinkitsystem.model.Product;

public class ProductFactory {

    public static Product createProduct(int id, String name, double price, String category, String image) {

        return new Product(id, name, price, category, image);
    }
}