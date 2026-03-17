package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.Order;
import com.example.blinkitsystem.model.OrderItem;
import com.example.blinkitsystem.observer.Observer;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.util.List;

public class OrderTrackingController implements Observer {

    @FXML
    private Label statusLabel;

    @FXML
    private ProgressBar progressBar;

    private Order order;

    private int step = 0;

    private String[] statuses = {
            "Order Placed",
            "Packed",
            "Out for Delivery",
            "Delivered"
    };

    @FXML
    public void initialize() {

        // Dummy order (you can later connect real cart)
        order = new Order(1, List.of());

        order.attach(this);

        startTracking();
    }

    private void startTracking() {

        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds(3), e -> {

                    if (step < statuses.length) {

                        order.updateStatus(statuses[step]);

                        progressBar.setProgress((step + 1) * 0.25);

                        step++;
                    }

                })
        );

        timeline.setCycleCount(statuses.length);
        timeline.play();
    }

    @Override
    public void update(String status) {

        statusLabel.setText(status);
    }

    @FXML
    private void goHome() {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

            Stage stage =
                    (Stage) statusLabel.getScene().getWindow();

            stage.setScene(new Scene(root));

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}