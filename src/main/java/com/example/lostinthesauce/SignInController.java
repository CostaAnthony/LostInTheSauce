package com.example.lostinthesauce;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SignInController {
    @FXML
    public TextField usernameTextfield;

    @FXML
    public TextField passwordTextField;

    public User currentUser;

    @FXML
    private void switchToHomeView() throws IOException {
        HelloApplication.setRoot("home-view");
    }

    public void addData() {

        DocumentReference docRef = HelloApplication.fstore.collection("Users").document(UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("Username", usernameTextfield.getText());
        data.put("Password", passwordTextField.getText());
        data.put("Customization", 0);
        data.put("Level 1 HiScore", 0);
        data.put("Level 2 HiScore", 0);
        data.put("Level 3 HiScore", 0);
        data.put("Total HiScore", 0);

        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }


    public void deleteData(){

    }

}

