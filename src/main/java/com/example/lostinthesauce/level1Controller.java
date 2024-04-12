package com.example.lostinthesauce;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Umer's class to showcase collision
 * Make sure to have a button that opens a menu (Maybe a file bar?)
 * that will allow to choose to go back to level select or the home page,
 * code to do so is in a comment below
 */
public class level1Controller {
    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
     }
}
