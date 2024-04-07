package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloController {
    @FXML
    public TextField usernameTextfield;

    @FXML
    public TextField passwordTextField;
    String dbFilePath = ".//AccountInfo.accdb";
    String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
    Connection connect;

    {
        try {
            connect = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void onLoginPress(){
        dbClass.printAccountInfo(connect);
    }

    public void onCreateAccountPress(){
        String username;
        String password;
        username = usernameTextfield.getText();
        password = passwordTextField.getText();
        dbClass.createAccount(connect, username, password);

    }


}