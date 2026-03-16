package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.Cart;
import com.example.blinkitsystem.model.OrderItem;
import com.example.blinkitsystem.service.CartService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CartController {

    @FXML
    private VBox cartItemsBox;

    @FXML
    private Label totalLabel;


    @FXML
    public void initialize() {

        loadCart();
    }


    // LOAD CART ITEMS
    private void loadCart() {

        cartItemsBox.getChildren().clear();

        Cart cart = CartService.getCart();

        for (OrderItem item : cart.getItems()) {

            HBox row = new HBox(20);

            Label name = new Label(item.getProduct().getName());

            Button minus = new Button("-");
            Label qty = new Label(String.valueOf(item.getQuantity()));
            Button plus = new Button("+");

            Label price = new Label("₹ " + item.getItemTotal());


            // INCREASE QTY
            plus.setOnAction(e -> {

                item.setQuantity(item.getQuantity() + 1);

                loadCart();
            });


            // DECREASE QTY
            minus.setOnAction(e -> {

                if(item.getQuantity() > 1){

                    item.setQuantity(item.getQuantity() - 1);

                } else {

                    CartService.getCart().removeItem(item);
                }

                loadCart();
            });


            row.getChildren().addAll(name, minus, qty, plus, price);

            cartItemsBox.getChildren().add(row);
        }

        totalLabel.setText("₹ " + cart.getTotal());
    }



    @FXML
    private void goBack(ActionEvent event) {

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


    @FXML
    private void checkout(ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/payment.fxml"));

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}