package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.Product;
import com.example.blinkitsystem.service.CartService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

    @FXML
    private GridPane productGrid;

    @FXML
    private TextField searchField;

    @FXML
    private Button cartButton;

    private List<Product> products = new ArrayList<>();


    @FXML
    private void openCart(ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/cart.fxml"));

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void initialize() {

        products.add(new Product(1,"Milk",60,"Dairy"));
        products.add(new Product(2,"Bread",40,"Bakery"));
        products.add(new Product(3,"Eggs",120,"Dairy"));
        products.add(new Product(4,"Coke",50,"Drinks"));
        products.add(new Product(5,"Apple",120,"Fruits"));
        products.add(new Product(6,"Banana",30,"Fruits"));

        loadProducts(products);
        updateCartCount();
    }


    // SEARCH FUNCTION
    @FXML
    private void searchProducts() {

        String keyword = searchField.getText().toLowerCase();

        List<Product> filtered = new ArrayList<>();

        for(Product product : products){

            if(product.getName().toLowerCase().contains(keyword)){
                filtered.add(product);
            }

        }

        loadProducts(filtered);
    }

    @FXML
    private void goBack(ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

            Stage stage =
                    (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateCartCount(){

        int count = CartService.getCart().getItems().size();

        cartButton.setText("Cart (" + count + ")");
    }

    // UPDATED LOAD METHOD
    private void loadProducts(List<Product> productList) {

        productGrid.getChildren().clear();

        int column = 0;
        int row = 0;

        try {

            for(Product product : productList){

                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/product-card.fxml"));

                VBox card = loader.load();

                Label name = (Label) card.lookup("#productName");
                Label category = (Label) card.lookup("#productCategory");
                Label price = (Label) card.lookup("#productPrice");
                Button addButton = (Button) card.lookup("#addButton");


                name.setText(product.getName());
                category.setText(product.getCategory());
                price.setText("₹ " + product.getPrice());


                addButton.setOnAction(e -> {

                    CartService.addProduct(product);
                    updateCartCount();

                    System.out.println("Added to cart: " + product.getName());
                });


                productGrid.add(card,column,row);

                column++;

                if(column == 3){
                    column = 0;
                    row++;
                }

            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}