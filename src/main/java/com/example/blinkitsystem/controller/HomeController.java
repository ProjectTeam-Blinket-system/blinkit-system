package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.OrderItem;
import com.example.blinkitsystem.model.Product;
import com.example.blinkitsystem.service.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.List;

public class HomeController {

    @FXML
    private Label deliveryLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private TextField searchField;

    @FXML
    private GridPane productGrid;

    @FXML
    private Button cartButton;

    @FXML
    private Label cartBadge;

    @FXML
    private Label userLabel;

    // ================= INITIALIZE =================

    @FXML
    public void initialize() {

        // USER + ADDRESS HANDLING (FIXED)
        if (UserService.isLoggedIn()) {

            String name = UserService.getCurrentUser().getName();
            String address = UserService.getCurrentUser().getAddress();

            userLabel.setText("Hello, " + name + " 👋");

            if (address != null && !address.trim().isEmpty()) {

                locationLabel.setText(address);
                updateDeliveryTime(address);

            } else {

                locationLabel.setText("Select Location");
                deliveryLabel.setText("Delivery in 15 minutes");
            }

        } else {

            userLabel.setText("Login");
            locationLabel.setText("Select Location");
            deliveryLabel.setText("Delivery in 15 minutes");
        }

        // LOAD PRODUCTS
        loadProducts(ProductService.getAllProducts());

        updateCartCount();
    }


    // ================= DELIVERY TIME =================

    private void updateDeliveryTime(String location) {

        String time = DeliveryService.getDeliveryTime(location);

        deliveryLabel.setText("Delivery in " + time);
    }


    // ================= SEARCH =================

    @FXML
    private void searchProduct() {

        String keyword = searchField.getText();

        List<Product> results = ProductService.searchProducts(keyword);

        loadProducts(results);
    }


    // ================= LOAD PRODUCTS =================

    private void loadProducts(List<Product> products) {

        productGrid.getChildren().clear();

        // EMPTY STATE FIX
        if (products == null || products.isEmpty()) {

            Label empty = new Label("No products found 😔");
            empty.setStyle("-fx-font-size:16; -fx-text-fill:gray;");

            productGrid.add(empty, 0, 0);
            return;
        }

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
                ImageView image = (ImageView) card.lookup("#productImage");

                // IMAGE SAFE LOAD
                var stream = getClass().getResourceAsStream(product.getImage());

                if (stream != null) {
                    image.setImage(new Image(stream));
                }

                // HOVER EFFECT
                card.setOnMouseEntered(e ->
                        card.setStyle("-fx-background-color:white; -fx-padding:12; -fx-background-radius:15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);")
                );

                card.setOnMouseExited(e ->
                        card.setStyle("-fx-background-color:white; -fx-padding:12; -fx-background-radius:15; -fx-border-color:#E5E5E5;")
                );

                name.setText(product.getName());
                category.setText(product.getCategory());
                price.setText("₹ " + product.getPrice());

                // ADD TO CART
                addButton.setOnAction(e -> {

                    CartService.addProduct(product);

                    updateCartCount();

                    addButton.setText("ADDED ✓");
                    addButton.setStyle("-fx-background-color:#4CAF50; -fx-text-fill:white;");
                    addButton.setDisable(true);

                    PauseTransition pause = new PauseTransition(Duration.seconds(2));

                    pause.setOnFinished(event -> {
                        addButton.setText("ADD");
                        addButton.setStyle("-fx-background-color:#0C8F45; -fx-text-fill:white;");
                        addButton.setDisable(false);
                    });

                    pause.play();
                });

                productGrid.add(card, column, row);

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

        int count = 0;

        for (OrderItem item : CartService.getCart().getItems()) {
            count += item.getQuantity();
        }

        cartBadge.setText(String.valueOf(count));
        cartBadge.setVisible(count > 0);
    }


    // ================= NAVIGATION =================

    @FXML
    private void openCart(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/cart.fxml"));

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openAddressPage() {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/address.fxml"));

            Stage stage =
                    (Stage) locationLabel.getScene().getWindow();

            stage.setScene(new Scene(root));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void openProducts(ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/products.fxml"));

            Stage stage =
                    (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ================= LOGIN / LOGOUT =================

    @FXML
    private void handleUserClick(MouseEvent event) {

        try {

            if (UserService.isLoggedIn()) {

                UserService.logout();

                userLabel.setText("Login");
                locationLabel.setText("Select Location");
                deliveryLabel.setText("Delivery in 15 minutes");

            } else {

                Parent root =
                        FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

                Stage stage =
                        (Stage)((Node)event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}