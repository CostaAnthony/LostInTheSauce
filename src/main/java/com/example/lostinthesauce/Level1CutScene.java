package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Level1CutScene {
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;

    /** Creates the cutscene before level1
     */
    public void initialize() {

        String videoFile = "src/main/resources/com/example/lostinthesauce/level1Video.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(
                () -> {
                    try {
                        switchToLevel1();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    /** Skips the cutscene
     */
    @FXML
    public void handleSkipButton() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        try {
            switchToLevel1();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Switches to the first level
     * @throws IOException
     */
    @FXML
    private void switchToLevel1() throws IOException {
        HelloApplication.setRoot("level1-view");
    }
}
