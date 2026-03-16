package com.example.blinkitsystem.service;

import com.example.blinkitsystem.model.Cart;
import com.example.blinkitsystem.model.OrderItem;
import com.example.blinkitsystem.model.Product;

public class CartService {

    private static Cart cart = new Cart();

    public static void addProduct(Product product) {

        Cart cart = getCart();

        for (OrderItem item : cart.getItems()) {

            if (item.getProduct().getName().equals(product.getName())) {

                item.setQuantity(item.getQuantity() + 1);

                return;
            }
        }

        cart.addItem(product, 1);
    }

    public static Cart getCart() {
        return cart;
    }

}