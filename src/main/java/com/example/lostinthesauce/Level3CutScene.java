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

    /** Creates the cutscene before level1
     */
    public void initialize() {
        String videoFileName = "level3Video.mp4";
        String videoFilePath = "/com/example/lostinthesauce/" + videoFileName;
        Media media = new Media(getClass().getResource(videoFilePath).toString());
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

    /** Skips the cutscene
     */
    @FXML
    public void handleSkipButton() {
        mediaPlayerLevel3.stop();
        mediaPlayerLevel3.seek(Duration.ZERO);
        try {
            switchToLevel3();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Switches to the first level
     * @throws IOException
     */
    @FXML
    private void switchToLevel3() throws IOException {
        HelloApplication.setRoot("level3-view");
    }
}
