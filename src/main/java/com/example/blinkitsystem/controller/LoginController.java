package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.User;
import com.example.blinkitsystem.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private void login() {

        String name = nameField.getText();
        String address = addressField.getText();

        // simple demo user (id = 1)
        User user = new User(1, name, address);

        UserService.login(user);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

            Stage stage = (Stage) nameField.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}