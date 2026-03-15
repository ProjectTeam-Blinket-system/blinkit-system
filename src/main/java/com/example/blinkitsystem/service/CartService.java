package com.example.blinkitsystem.service;

import com.example.blinkitsystem.model.Cart;
import com.example.blinkitsystem.model.Product;

public class CartService {

    private static Cart cart = new Cart();

    public static void addProduct(Product product) {
        cart.addItem(product, 1);
    }

    public static Cart getCart() {
        return cart;
    }

}