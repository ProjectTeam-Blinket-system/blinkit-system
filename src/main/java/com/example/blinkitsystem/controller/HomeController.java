package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.service.DeliveryService;
import com.example.blinkitsystem.service.LocationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private ComboBox<String> locationBox;

    @FXML
    private Label deliveryLabel;

    @FXML
    private TextField searchField;


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

        String query = searchField.getText();

        System.out.println("Searching for: " + query);
    }


    // ================= LOGIN =================

    @FXML
    private void openLogin() {

        System.out.println("Open login screen");
    }


    // ================= CART =================

    @FXML
    private void openCart() {

        System.out.println("Open cart screen");
    }


    // ================= PRODUCTS =================

    @FXML
    private void openProducts(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource("/fxml/products.fxml")
            );

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}