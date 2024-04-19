package com.example.lostinthesauce;

import javafx.fxml.FXML;
import java.io.IOException;

public class HomeController {
    @FXML
    private void switchToSignIn() throws IOException {
        HelloApplication.setRoot("signin-view");
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }

}