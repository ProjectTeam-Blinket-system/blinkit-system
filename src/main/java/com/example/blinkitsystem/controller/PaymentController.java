package com.example.blinkitsystem.controller;

import com.example.blinkitsystem.service.CartService;
import com.example.blinkitsystem.strategy.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PaymentController {

    @FXML
    private TextField upiField;

    @FXML
    private TextField cardField;

    @FXML
    private TextField walletField;

    @FXML
    private Label amountLabel;

    @FXML
    private VBox upiBox;

    @FXML
    private VBox cardBox;

    @FXML
    private VBox walletBox;

    private double amount;


    @FXML
    public void initialize() {

        amount = CartService.getCart().getTotal();

        amountLabel.setText("Total Amount: ₹ " + amount);

    }


    // SHOW INPUT SECTIONS

    @FXML
    private void showUPI() {

        upiBox.setVisible(true);
        cardBox.setVisible(false);
        walletBox.setVisible(false);

    }

    @FXML
    private void showCard() {

        upiBox.setVisible(false);
        cardBox.setVisible(true);
        walletBox.setVisible(false);

    }

    @FXML
    private void showWallet() {

        upiBox.setVisible(false);
        cardBox.setVisible(false);
        walletBox.setVisible(true);

    }


    // PAYMENT METHODS

    @FXML
    private void payWithUPI(ActionEvent event) {

        String upiId = upiField.getText();

        PaymentStrategy strategy = new UpiPayment(upiId);

        strategy.pay(amount);

        System.out.println("UPI Payment Successful");

        openOrderTracking(event);
    }


    @FXML
    private void payWithCard(ActionEvent event) {

        String cardNumber = cardField.getText();

        PaymentStrategy strategy = new CardPayment(cardNumber);

        strategy.pay(amount);

        System.out.println("Card Payment Successful");

        openOrderTracking(event);
    }


    @FXML
    private void payWithWallet(ActionEvent event) {

        String walletId = walletField.getText();

        PaymentStrategy strategy = new WalletPayment(walletId);

        strategy.pay(amount);

        System.out.println("Wallet Payment Successful");

        openOrderTracking(event);
    }


    @FXML
    private void payWithCOD(ActionEvent event) {

        PaymentStrategy strategy = new CODPayment();

        strategy.pay(amount);

        System.out.println("Cash on Delivery Selected");

        openOrderTracking(event);
    }


    // NAVIGATE TO ORDER TRACKING

    private void openOrderTracking(ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/order-tracking.fxml"));

            Stage stage =
                    (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}