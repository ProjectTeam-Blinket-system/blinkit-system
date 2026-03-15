package model;

public class Coupon {

    private int couponCount;
    private double discountValue;

    public Coupon(int couponCount, double discountValue) {
        this.couponCount = couponCount;
        this.discountValue = discountValue;
    }

    public boolean hasCoupon() {
        return couponCount > 0;
    }

    public void useCoupon() {
        if (couponCount > 0) {
            couponCount--;
        }
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public int getCouponCount() {
        return couponCount;
    }
}