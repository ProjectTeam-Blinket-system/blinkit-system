package model;

import java.util.ArrayList;
import java.util.List;

import model.OrderItem;
import observer.Observer;

public class Order {

    private int orderId;
    private String status;
    private List<OrderItem> items;

    private List<Observer> observers = new ArrayList<>();

    public Order(int orderId, List<OrderItem> items) {
        this.orderId = orderId;
        this.items = items;
        this.status = "Created";
    }

    public void updateStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }
}