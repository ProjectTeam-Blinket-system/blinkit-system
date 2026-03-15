package com.example.blinkitsystem.service;

public class DeliveryService {

    public static String getDeliveryTime(String location) {

        switch (location) {

            case "Delhi":
                return "10 minutes";

            case "Gurgaon":
                return "12 minutes";

            case "Noida":
                return "14 minutes";

            default:
                return "15 minutes";
        }
    }
}