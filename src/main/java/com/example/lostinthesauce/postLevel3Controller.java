package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class postLevel3Controller extends level3Controller{
    public void initialize(){

    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }
}