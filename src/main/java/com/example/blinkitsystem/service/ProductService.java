package com.example.blinkitsystem.service;

import com.example.blinkitsystem.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private static List<Product> products = new ArrayList<>();

    static {

        products.add(new Product(1,"Milk",60,"Dairy"));
        products.add(new Product(2,"Bread",40,"Bakery"));
        products.add(new Product(3,"Eggs",120,"Dairy"));
        products.add(new Product(4,"Coke",50,"Drinks"));
        products.add(new Product(5,"Apple",120,"Fruits"));
        products.add(new Product(6,"Banana",30,"Fruits"));
        products.add(new Product(7,"Butter",80,"Dairy"));
        products.add(new Product(8,"Orange Juice",90,"Drinks"));

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