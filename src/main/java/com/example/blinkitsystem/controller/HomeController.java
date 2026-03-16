package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.Product;
import com.example.blinkitsystem.service.CartService;
import com.example.blinkitsystem.service.DeliveryService;
import com.example.blinkitsystem.service.LocationService;
import com.example.blinkitsystem.service.ProductService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class HomeController {

    @FXML
    private ComboBox<String> locationBox;

    @FXML
    private Label deliveryLabel;

    @FXML
    private TextField searchField;

    @FXML
    private GridPane productGrid;

    @FXML
    private Button cartButton;


    // ================= INITIALIZE =================

    @FXML
    public void initialize() {

        locationBox.getItems().addAll(
                "Sonipat",
                "Delhi",
                "Gurgaon",
                "Noida"
        );

        String detectedCity = LocationService.getCurrentCity();

        locationBox.setValue(detectedCity);

        updateDeliveryTime();

        locationBox.setOnAction(e -> updateDeliveryTime());

        loadProducts(ProductService.getAllProducts());

        updateCartCount();
    }


    // ================= DELIVERY TIME =================

    private void updateDeliveryTime() {

        String location = locationBox.getValue();

        String time = DeliveryService.getDeliveryTime(location);

        deliveryLabel.setText("Delivery in " + time);
    }


    // ================= SEARCH =================

    @FXML
    private void searchProduct() {

        String keyword = searchField.getText();

        List<Product> results =
                ProductService.searchProducts(keyword);

        loadProducts(results);
    }


    // ================= LOAD PRODUCTS =================

    private void loadProducts(List<Product> products) {

        productGrid.getChildren().clear();

        int column = 0;
        int row = 0;

        try {

            for(Product product : products){

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


                // ADD TO CART
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


    // ================= CART COUNT =================

    private void updateCartCount() {

        int count = CartService.getCart().getItems().size();

        cartButton.setText("Cart (" + count + ")");
    }


    // ================= LOGIN =================

    @FXML
    private void openLogin() {

        System.out.println("Open login screen");
    }


    // ================= CART =================

    @FXML
    private void openCart(ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/cart.fxml"));

            Stage stage =
                    (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // ================= PRODUCTS PAGE =================

    @FXML
    private void openProducts(ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/products.fxml"));

            Stage stage =
                    (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}