package com.example.lostinthesauce;

import javafx.fxml.FXML;
import java.io.IOException;

public class levelSelectController {
    @FXML
    private void switchToLevel1() throws IOException {
        HelloApplication.setRoot("level1-view");
    }
    @FXML
    private void switchToLevel2() throws IOException {
        HelloApplication.setRoot("level2CutScene-view");
    }

    @FXML
    private void switchToLevel3() throws IOException {
        HelloApplication.setRoot("level3CutScene-view");
    }
    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
    }
}
