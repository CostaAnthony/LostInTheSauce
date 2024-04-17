package com.example.lostinthesauce;

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
import java.sql.*;

public class SignInController {
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

    public void printDatabase(){
        dbClass.printAccountInfo(connect);
    }


    public void onLoginPress(ActionEvent event) throws IOException{
        String usernameInput = usernameTextfield.getText();
        String passwordInput = passwordTextField.getText();

        try {
            String tableName = "AccountInfo";
            Statement stmt = connect.createStatement();
            ResultSet result = stmt.executeQuery("select * from " + tableName);
            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                if(username.equals(usernameInput) && password.equals(passwordInput)){
                    Parent root = FXMLLoader.load(getClass().getResource("sceneChange.fxml"));
                }
            }
            System.out.println("Login called");
        } catch (SQLException except) {
            except.printStackTrace();
        }

    }

    public void onCreateAccountPress(){
        String username;
        String password;
        username = usernameTextfield.getText();
        password = passwordTextField.getText();
        dbClass.createAccount(connect, username, password);

    }

    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
    }

}

