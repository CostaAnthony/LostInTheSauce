package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class postLevel1Controller extends level1Controller {
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
}
