package com.example.lostinthesauce;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class SignInController {
    @FXML
    public TextField usernameTextfield;

    @FXML
    public TextField passwordTextField;

    public User currentUser = new User("", "");

    private boolean key;

    public String userUsername = "";



    @FXML
    private void switchToHomeView() throws IOException {
        HelloApplication.setRoot("home-view");
    }

    public void addData() {

        DocumentReference docRef = HelloApplication.fstore.collection("Users").document(usernameTextfield.getText()+passwordTextField.getText());

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

        usernameTextfield.clear();
        passwordTextField.clear();
    }


    public void initUser(){
        key = false;

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future =  HelloApplication.fstore.collection("Users").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (documents.size() > 0) {
                //System.out.println("Getting (reading) data from firabase database....");
                for (QueryDocumentSnapshot document : documents) {
                    if (usernameTextfield.getText().equals(document.getData().get("Username")) && passwordTextField.getText().equals(document.getData().get("Password"))) {
                        //currentUser = new User(String.valueOf(document.getData().get("Username")),String.valueOf(document.getData().get("Password")));

                        //userUsername = String.valueOf(document.getData().get("Username"));
                                currentUser.setUsername(String.valueOf(document.getData().get("Username")));
                                currentUser.setPassword(String.valueOf(document.getData().get("Password")));
                    }
                }
            }
            key = true;
        }catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }

        //System.out.println(userUsername);
        //String currUserName = currentUser.getUsername();
        //System.out.println(currUserName);

        usernameTextfield.clear();
        passwordTextField.clear();
    }


    public void deleteData(){
        HelloApplication.fstore.collection("Users").document("85ac8fb7-6b6f-4524-a373-2150dfb0534b")
                .delete();
    }




    public void updateData(){
        HelloApplication.fstore.collection("Users").document(currentUser.getUsername()+currentUser.getPassword())
                .update("Customization", 3);
    }



    public void readData(){

    }

}

