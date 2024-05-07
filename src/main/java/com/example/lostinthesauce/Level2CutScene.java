package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Level2CutScene {
    @FXML
    private MediaView mediaViewLevel2;
    private MediaPlayer mediaPlayerLevel2;
    /** Creates the cutscene before level1
     */
    public void initialize() {
        String videoFileName = "level2Video.mp4";
        String videoFilePath = "/com/example/lostinthesauce/" + videoFileName;
        Media media = new Media(getClass().getResource(videoFilePath).toString());
        mediaPlayerLevel2 = new MediaPlayer(media);

        mediaViewLevel2.setMediaPlayer(mediaPlayerLevel2);
        mediaPlayerLevel2.play();
        mediaPlayerLevel2.setOnEndOfMedia(
                () -> {
                    try {
                        switchToLevel2();
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
        mediaPlayerLevel2.stop();
        mediaPlayerLevel2.seek(Duration.ZERO);
        try {
            switchToLevel2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /** Switches to the second level
     * @throws IOException
     */
    @FXML
    private void switchToLevel2() throws IOException {
        HelloApplication.setRoot("level2-view");
    }
}
