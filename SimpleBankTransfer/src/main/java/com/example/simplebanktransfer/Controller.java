package com.example.simplebanktransfer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    Database db = new Database();

    private Stage stage;
    private Scene scene;
    private Parent root;

    //Variables using the fx:id's from newuser-view.fxml and other views.
    public TextField nameTextField, addressTextField, emailTextField, mobileNrTextField, cardNrTextField, checkDigitsTextField, expirationDayMonthTextField, expirationYearTextField, passwordTextField, wealthTextField, emailLoginTextField, passwordLoginTextField;
    public Label balanceLabel;

    public void switchToLogInView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToNewUserView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("newuser-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTransferPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("transferMoney-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToBalancePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("balance-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Switches scene from Login to Transfer page if the login was succesful.
    public void switchSceneIfLoginSuccesful(ActionEvent event) throws IOException {
        String loginEmail = emailLoginTextField.getText();
        String loginPassword = passwordLoginTextField.getText();

        db.login(event, loginEmail, loginPassword);
    }
    //Uses addUser from Database class.
    public void createUser(ActionEvent event) {
        db.addUser(event, nameTextField.getText(), addressTextField.getText(), emailTextField.getText(), Integer.parseInt(mobileNrTextField.getText()), cardNrTextField.getText(), checkDigitsTextField.getText(), expirationDayMonthTextField.getText(), expirationYearTextField.getText(), passwordTextField.getText(), Integer.parseInt(wealthTextField.getText()));
    }
    //Makes you able to check your balance.
    public void displayBalance(ActionEvent event) {
        db.checkBalance(event);

    }

    public Label getBalanceLabel() {
        return balanceLabel;
    }

    public void setBalanceLabel(Label balanceLabel) {
        this.balanceLabel = balanceLabel;
    }
}