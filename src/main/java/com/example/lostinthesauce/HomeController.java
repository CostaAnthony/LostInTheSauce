package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class HomeController {
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    public void initialize() {
        String userHome = System.getProperty("user.home");
        String videoFilePath = userHome + "/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/HomePageFinal.mp4";
        Media media = new Media(new File(videoFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(
                () -> {
                    mediaPlayer.seek(Duration.ZERO);
                    mediaPlayer.play();
                }
        );
    }
    @FXML
    private void switchToSignIn() throws IOException {
        HelloApplication.setRoot("signin-view");
        mediaPlayer.stop();
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
        mediaPlayer.stop();
    }

}