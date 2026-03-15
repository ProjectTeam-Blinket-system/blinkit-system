package com.example.blinkitsystem.observer;

public class CustomerObserver implements Observer {

    @Override
    public void update(String status) {

        System.out.println("Customer Notification: Order status changed to " + status);
    }
}