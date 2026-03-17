package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.model.User;
import com.example.blinkitsystem.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddressController {

    @FXML
    private VBox addressBox;

    @FXML
    private TextField newAddressField;

    private List<String> addresses = new ArrayList<>();


    @FXML
    public void initialize() {

        // default addresses
        addresses.add("Home - Sonipat");
        addresses.add("Work - Delhi");

        loadAddresses();
    }


    private void loadAddresses() {

        addressBox.getChildren().clear();

        for(String addr : addresses){

            Button btn = new Button(addr);

            btn.setMaxWidth(Double.MAX_VALUE);

            btn.setOnAction(e -> selectAddress(addr));

            addressBox.getChildren().add(btn);
        }
    }


    private void selectAddress(String address){

        if(UserService.getCurrentUser() == null){

            // create guest user
            UserService.login(new User(0, "Guest", address));

        } else {

            UserService.getCurrentUser().setAddress(address);
        }

        goBack(null);
    }

    @FXML
    private void addAddress(){

        String newAddr = newAddressField.getText();

        if(!newAddr.isEmpty()){
            addresses.add(newAddr);
            loadAddresses();
            newAddressField.clear();
        }
    }


    @FXML
    private void goBack(javafx.event.ActionEvent event){

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

            Stage stage = (Stage) addressBox.getScene().getWindow();

            stage.setScene(new Scene(root));

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}