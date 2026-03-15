package com.example.blinkitsystem.decorator;

public class BaseOrder implements OrderComponent {

    private double baseCost;

    public BaseOrder(double baseCost) {
        this.baseCost = baseCost;
    }

    @Override
    public double getCost() {
        return baseCost;
    }

    @Override
    public String getDescription() {
        return "Base Order";
    }
}