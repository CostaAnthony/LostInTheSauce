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
    private MediaPlayer musicPlayer;
    public void initialize() {
        String userHome = System.getProperty("user.home");

        String videoFile = userHome + "/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/UpdatedHome.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        String homeMusic = userHome + "/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/menuMusic.mp3";
        Media homeMusicSound = new Media(new File(homeMusic).toURI().toString());
        musicPlayer = new MediaPlayer(homeMusicSound);

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        musicPlayer.play();
        mediaPlayer.setOnEndOfMedia(
                () -> {
                    mediaPlayer.seek(Duration.ZERO);
                    mediaPlayer.play();
                }
        );
        musicPlayer.setOnEndOfMedia(
                () -> {
                    musicPlayer.seek(Duration.ZERO);
                    musicPlayer.play();
                }
        );
    }
    @FXML
    private void switchToSignIn() throws IOException {
        HelloApplication.setRoot("signin-view");
        mediaPlayer.stop();
        musicPlayer.stop();
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
        mediaPlayer.stop();
        musicPlayer.stop();
    }

}