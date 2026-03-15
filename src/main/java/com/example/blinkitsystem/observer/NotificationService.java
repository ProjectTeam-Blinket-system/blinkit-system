package com.example.blinkitsystem.observer;

public class NotificationService implements Observer {

    @Override
    public void update(String status) {

        sendNotification("Order status updated to: " + status);
    }

    public void sendNotification(String message) {

        System.out.println("Notification Service: " + message);
    }
}