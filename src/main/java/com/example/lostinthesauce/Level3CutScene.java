package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Level3CutScene {
    @FXML
    private MediaView mediaViewLevel3;
    private MediaPlayer mediaPlayerLevel3;

    public void initialize() {
        String videoFile = "/Users/jay/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/level3Video.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());
        mediaPlayerLevel3 = new MediaPlayer(media);

        mediaViewLevel3.setMediaPlayer(mediaPlayerLevel3);
        mediaPlayerLevel3.play();
        mediaPlayerLevel3.setOnEndOfMedia(
                () -> {
                    try {
                        switchToLevel3();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @FXML
    public void handleSkipButton() {
        Duration totalDuration = mediaPlayerLevel3.getTotalDuration();
        mediaPlayerLevel3.seek(totalDuration);
    }

    @FXML
    private void switchToLevel3() throws IOException {
        HelloApplication.setRoot("level3-view");
    }
}
