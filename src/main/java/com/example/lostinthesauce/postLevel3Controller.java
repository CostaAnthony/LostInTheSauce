package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class postLevel3Controller extends level3Controller{
    @FXML
    public TextField scoreCount;

    public void initialize(){
        scoreFinal();
    }
    public void scoreFinal(){
        int totalScore = addScore();
        scoreCount.setText(String.valueOf(totalScore));
    }
    public int addScore(){
        return coin1Value+coin2Value+coin3Value+coin4Value+coin5Value+(time*10);
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }
    @FXML
    private void switchToLevel2() throws IOException {
        HelloApplication.setRoot("level2-view");
    }
}
