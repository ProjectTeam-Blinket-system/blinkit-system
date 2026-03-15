package factory;

import model.Product;

public class ProductFactory {

    public static Product createProduct(int id, String name, double price, String category) {

        return new Product(id, name, price, category);
    }
}