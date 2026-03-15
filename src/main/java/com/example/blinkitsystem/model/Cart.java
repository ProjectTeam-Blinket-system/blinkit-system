package model;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.OrderItem;

public class Cart {

    private List<OrderItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public double getTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getItemTotal();
        }

        return total;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}