package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class levelSelectController {
    private MediaPlayer musicPlayerMenu;
    public void initialize() {
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
    @FXML
    private void switchToLevel1() throws IOException {
        musicPlayerMenu.stop();
        HelloApplication.setRoot("level1CutScene-view");
    }
    @FXML
    private void switchToLevel2() throws IOException {
        musicPlayerMenu.stop();
        HelloApplication.setRoot("level2CutScene-view");
    }

    @FXML
    private void switchToLevel3() throws IOException {
        musicPlayerMenu.stop();
        HelloApplication.setRoot("level3CutScene-view");
    }
    @FXML
    private void switchToHome() throws IOException {
        musicPlayerMenu.stop();
        HelloApplication.setRoot("home-view");
    }
}
