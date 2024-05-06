package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class PostLevel3CutScene {
    @FXML
    private MediaView mediaViewPostLevel3;
    private MediaPlayer mediaPlayerPostLevel3;
    public void initialize() {
        String videoFile = "/Users/jay/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/postLevel3.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());
        mediaPlayerPostLevel3 = new MediaPlayer(media);

        mediaViewPostLevel3.setMediaPlayer(mediaPlayerPostLevel3);
        mediaPlayerPostLevel3.play();
        mediaPlayerPostLevel3.setOnEndOfMedia(
                () -> {
                    try {
                        switchToPostLevel3();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
    @FXML
    public void handleSkipButton() {
        mediaPlayerPostLevel3.stop();
        mediaPlayerPostLevel3.seek(Duration.ZERO);
        try {
            switchToPostLevel3();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void switchToPostLevel3() throws IOException {
        HelloApplication.setRoot("postLevel3-view");
    }
}
