package com.example.blinkitsystem.observer;

public class DeliveryAgentObserver implements Observer {

    @Override
    public void update(String status) {

        System.out.println("Delivery Agent Notification: Order status changed to " + status);
    }
}