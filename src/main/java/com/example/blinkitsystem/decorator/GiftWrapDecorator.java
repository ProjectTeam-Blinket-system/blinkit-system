package com.example.blinkitsystem.decorator;

public class GiftWrapDecorator extends OrderDecorator {

    private double wrapCost = 20;

    public GiftWrapDecorator(OrderComponent order) {
        super(order);
    }

    @Override
    public double getCost() {
        return order.getCost() + wrapCost;
    }

    @Override
    public String getDescription() {
        return order.getDescription() + " + Gift Wrap";
    }
}