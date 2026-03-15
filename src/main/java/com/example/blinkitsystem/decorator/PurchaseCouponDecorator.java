package com.example.blinkitsystem.decorator;

import model.Coupon;
public class PurchaseCouponDecorator extends OrderDecorator {

    private Coupon coupon;

    public PurchaseCouponDecorator(OrderComponent order, Coupon coupon) {
        super(order);
        this.coupon = coupon;
    }

    @Override
    public double getCost() {

        if (coupon.hasCoupon()) {
            coupon.useCoupon();
            return order.getCost() - coupon.getDiscountValue();
        }

        return order.getCost();
    }

    @Override
    public String getDescription() {

        if (coupon.hasCoupon()) {
            return order.getDescription() + " + Coupon Applied";
        }

        return order.getDescription() + " (No Coupon Available)";
    }
}