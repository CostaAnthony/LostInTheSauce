package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class postLevel2Controller{
    public void initialize(){

    }

    /** Switches to level select
     * @throws IOException
     */
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }
    /** Switches to level 3
     * @throws IOException
     */
    @FXML
    private void switchToLevel3() throws IOException {
        HelloApplication.setRoot("level3CutScene-view");
    }
}
