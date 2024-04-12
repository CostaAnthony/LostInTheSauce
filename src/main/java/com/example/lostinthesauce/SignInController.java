package com.example.lostinthesauce;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Ian's sign-in
 * Make sure to add a back button to go back to the Main Screen
 */
public class SignInController {
    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
    }
}

