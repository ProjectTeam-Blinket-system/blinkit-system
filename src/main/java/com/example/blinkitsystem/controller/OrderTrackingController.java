package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.Order;
import com.example.blinkitsystem.model.OrderItem;
import com.example.blinkitsystem.observer.Observer;
import com.example.blinkitsystem.service.CartService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class OrderTrackingController implements Observer {

    @FXML
    private Label statusLabel;

    private Order order;

    private int step = 0;

    private String[] statuses = {
            "Order Created",
            "Preparing Order",
            "Packed",
            "Out for Delivery",
            "Delivered"
    };


    @FXML
    public void initialize() {

        List<OrderItem> items = CartService.getCart().getItems();

        order = new Order(1, items);

        order.attach(this);

        order.updateStatus(statuses[0]);
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

    @FXML
    private void nextStatus() {

        step++;

        if(step < statuses.length) {

            order.updateStatus(statuses[step]);

        }

    }


    @Override
    public void update(String status) {

        statusLabel.setText(status);

    }

}