package com.example.blinkitsystem.strategy;

public class CODPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Cash on Delivery selected for " + amount);
    }
}