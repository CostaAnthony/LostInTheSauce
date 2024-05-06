package com.example.lostinthesauce;

import javafx.fxml.FXML;
import java.io.IOException;


public class HomeController {

    //public User currentUser1 = new User ("", "");

    public void initialize(){
        System.out.println("Hello!");
    }
    @FXML
    private void switchToSignIn() throws IOException {
        HelloApplication.setRoot("signin-view");
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }







}