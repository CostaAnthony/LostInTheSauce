package com.example.lostinthesauce;

import javafx.fxml.FXML;

import java.io.IOException;

public class level2Controller {
    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }
}
