package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.OrderItem;
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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class ProductController {

    @FXML
    private GridPane productGrid;

    @FXML
    private TextField searchField;

    @FXML
    private Button cartButton;

    @FXML
    private Label cartBadge;

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

        products.add(new Product(1,"Milk",60,"Dairy","/images/milk.jpg"));
        products.add(new Product(2,"Bread",40,"Bakery","/images/bread.jpg"));
        products.add(new Product(3,"Eggs",120,"Dairy","/images/eggs.jpg"));
        products.add(new Product(4,"Coke",50,"Drinks","/images/coke.jpg"));
        products.add(new Product(5,"Apple",120,"Fruits","/images/apple.jpg"));
        products.add(new Product(6,"Banana",30,"Fruits","/images/banana.jpg"));

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

    private void updateCartCount() {

        int count = 0;

        for (OrderItem item : CartService.getCart().getItems()) {
            count += item.getQuantity();
        }

        cartButton.setText("Cart");

        cartBadge.setText(String.valueOf(count));

        cartBadge.setVisible(count > 0);
    }

    // UPDATED LOAD METHOD
    private void loadProducts(List<Product> productList) {

        productGrid.getChildren().clear();

        int column = 0;
        int row = 0;

        try {

            for(Product product : productList){

                if(products.isEmpty()){

                    Label empty = new Label("No products found 😔");
                    empty.setStyle("-fx-font-size:16; -fx-text-fill:gray;");

                    productGrid.add(empty,0,0);
                    return;
                }

                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/product-card.fxml"));

                VBox card = loader.load();

                Label name = (Label) card.lookup("#productName");
                Label category = (Label) card.lookup("#productCategory");
                Label price = (Label) card.lookup("#productPrice");
                Button addButton = (Button) card.lookup("#addButton");

                ImageView image = (ImageView) card.lookup("#productImage");

                // ✅ SAFE IMAGE LOADING (NO CRASH)
                var stream = getClass().getResourceAsStream(product.getImage());

                if (stream != null) {
                    image.setImage(new Image(stream));
                } else {
                    System.out.println("❌ Image not found: " + product.getImage());
                }

                card.setOnMouseEntered(e -> {
                    card.setStyle("-fx-background-color:white; -fx-padding:12; -fx-background-radius:15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);");
                });

                card.setOnMouseExited(e -> {
                    card.setStyle("-fx-background-color:white; -fx-padding:12; -fx-background-radius:15; -fx-border-color:#E5E5E5;");
                });

                name.setText(product.getName());
                category.setText(product.getCategory());
                price.setText("₹ " + product.getPrice());


                addButton.setOnAction(e -> {

                    CartService.addProduct(product);

                    updateCartCount();

                    // CHANGE BUTTON UI
                    addButton.setText("ADDED ✓");
                    addButton.setStyle("-fx-background-color:#4CAF50; -fx-text-fill:white;");

                    // RESET AFTER 2 SECONDS
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));

                    addButton.setDisable(true);

                    pause.setOnFinished(event -> {
                        addButton.setText("ADD");
                        addButton.setStyle("-fx-background-color:#0C8F45; -fx-text-fill:white;");
                        addButton.setDisable(false);
                    });

                    pause.play();

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