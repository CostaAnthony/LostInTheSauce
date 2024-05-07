package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class postLevel3Controller extends level3Controller{
    @FXML
    private TextField scoreCount;
    private MediaPlayer musicPlayerMenu;
    public void initialize() {
        scoreCount.setText(String.valueOf(level3Controller.totalScore));
        String userHome = System.getProperty("user.home");

        String homeMusic = userHome + "/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/menuMusicOther.mp3";
        Media homeMusicSound = new Media(new File(homeMusic).toURI().toString());
        musicPlayerMenu = new MediaPlayer(homeMusicSound);

        musicPlayerMenu.play();
        musicPlayerMenu.setOnEndOfMedia(
                () -> {
                    musicPlayerMenu.seek(Duration.ZERO);
                    musicPlayerMenu.play();
                }
        );
    }
    /** Switches to level select
     * @throws IOException
     */
    @FXML
    private void switchToLevelSelect() throws IOException {
        musicPlayerMenu.stop();
        HelloApplication.setRoot("levelSelect-view");
    }
}
