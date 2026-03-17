package com.example.blinkitsystem.service;

import com.example.blinkitsystem.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private static List<Product> products = new ArrayList<>();

    static {

        products.add(new Product(1,"Milk",60,"Dairy","/images/milk.jpg"));
        products.add(new Product(2,"Bread",40,"Bakery","/images/bread.jpg"));
        products.add(new Product(3,"Eggs",120,"Dairy","/images/eggs.jpg"));
        products.add(new Product(4,"Coke",50,"Drinks","/images/coke.jpg"));
        products.add(new Product(5,"Apple",120,"Fruits","/images/apple.jpg"));
        products.add(new Product(6,"Banana",30,"Fruits","/images/banana.jpg"));

    }

    public static List<Product> getAllProducts() {
        return products;
    }

    public static List<Product> searchProducts(String keyword) {

        return products.stream()
                .filter(p -> p.getName().toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

    }
}