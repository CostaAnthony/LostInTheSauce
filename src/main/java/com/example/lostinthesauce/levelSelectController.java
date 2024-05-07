package com.example.lostinthesauce;

import javafx.fxml.FXML;
import java.io.IOException;

public class levelSelectController {
    /** Switches to level 1
     * @throws IOException
     */
    @FXML
    private void switchToLevel1() throws IOException {
        HelloApplication.setRoot("level1-view");
    }
    /** Switches to level 2
     * @throws IOException
     */
    @FXML
    private void switchToLevel2() throws IOException {
        HelloApplication.setRoot("level2CutScene-view");
    }

    /** Switches to level 3
     * @throws IOException
     */
    @FXML
    private void switchToLevel3() throws IOException {
        HelloApplication.setRoot("level3CutScene-view");
    }

    /** Switches to level 4
     * @throws IOException
     */
    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
    }
}
