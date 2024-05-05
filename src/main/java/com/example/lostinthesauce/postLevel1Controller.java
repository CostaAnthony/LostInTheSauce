package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class postLevel1Controller {
    public void initialize(){

    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }
    @FXML
    private void switchToLevel2() throws IOException {
        HelloApplication.setRoot("level2CutScene-view");
    }
}
