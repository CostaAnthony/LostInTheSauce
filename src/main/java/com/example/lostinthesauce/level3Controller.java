package com.example.lostinthesauce;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.File;
import javafx.stage.Stage;
import java.io.IOException;

public class level3Controller {

    @FXML
    private Pane scene;
    @FXML
    private ImageView player;
    @FXML
    private Rectangle platform1;
    @FXML
    private Rectangle platform2;
    @FXML
    private Rectangle platform3;
    @FXML
    private Rectangle platform4;
    @FXML
    private Rectangle platform5;
    @FXML
    private Rectangle platform6;
    @FXML
    private ImageView coin1;

    private coin coinInstance;

    @FXML
    private ImageView coin2;
    @FXML
    private ImageView coin3;
    @FXML
    private ImageView coin4;
    @FXML
    private ImageView coin5;
    @FXML
    private Rectangle borderBottom;
    @FXML
    private Circle portal;
    @FXML
    public TextField scoreCount;
    @FXML
    public TextField timeCount;
    @FXML
    public TextField levelFailed;
    public int time = 30;
    private Timeline timeline;
    public int coin1Value;
    public int coin2Value;
    public int coin3Value;
    public int coin4Value;
    public int coin5Value;

    @FXML
    void start(ActionEvent event) {
        player.setLayoutY(660);
        player.setLayoutX(128);
    }

    private boolean wPressed;
    private boolean aPressed;
    private boolean dPressed;
    private double movementVar = 8;
    private double velY = 0;
    private final double GRAVITY = -0.7;
    private final double MAX_SPEED = 30;
    private boolean isFalling = false;

    String jumpSoundPath = new File("/Users/jay/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/Jump.mp3").toURI().toString();
    Media jumpSoundMedia = new Media(jumpSoundPath); //Media object for the jump sound
    MediaPlayer jumpSoundPlayer = new MediaPlayer(jumpSoundMedia); //MediaPlayer to play the sound
    String bottleSoundMP3 = new File("/Users/jay/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/bottle2.mp3").toURI().toString();
    Media bottleSound = new Media(bottleSoundMP3);
    MediaPlayer bottleSoundPlayer = new MediaPlayer(bottleSound);

    AnimationTimer movementTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            player.setLayoutY(player.getLayoutY() - velY);
            if (isFalling) {
                velY += GRAVITY;
                if (velY >= MAX_SPEED) {
                    velY = MAX_SPEED;
                }
            } else if(!isFalling) {
                velY = 0;

            }

            if (aPressed) {
                player.setLayoutX(player.getLayoutX() - movementVar);
            }

            if (dPressed) {
                player.setLayoutX(player.getLayoutX() + movementVar);
            }
            if (wPressed && !isFalling) {
                //Plays the jump sound effect
                jumpSoundPlayer.play();
                //Resets the jump sound back to the beginning of the track
                jumpSoundPlayer.seek(Duration.ZERO);
                velY += 10.5;
            }

        }
    };
    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            checkCollision();
        }
    };
    AnimationTimer collisionTimer2 = new AnimationTimer() {
        @Override
        public void handle(long l) {
            checkCollision2();
        }
    };

    public void initialize() {
        movementSetup();
        movementTimer.start();
        collisionTimer.start();
        collisionTimer2.start();
        timer();
        scoreInitial();
    }

    public void movementSetup() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.SPACE) {
                wPressed = true;
            }

            if (e.getCode() == KeyCode.A) {
                aPressed = true;
            }

            if (e.getCode() == KeyCode.D) {
                dPressed = true;
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.SPACE) {
                wPressed = false;
            }

            if (e.getCode() == KeyCode.A) {
                aPressed = false;
            }
             if (e.getCode() == KeyCode.D) {
                 dPressed = false;
             }
        });
    }

    public void checkCollision() {
            if(player.getBoundsInParent().intersects(platform1.getBoundsInParent())&&player.getLayoutY() < platform1.getLayoutY()){
                fixPlayerDipping(platform1);
            }
            else if(player.getBoundsInParent().intersects(platform2.getBoundsInParent())&&player.getLayoutY() < platform2.getLayoutY()){
                fixPlayerDipping(platform2);
            }
            else if(player.getBoundsInParent().intersects(platform3.getBoundsInParent())&&player.getLayoutY() < platform3.getLayoutY()){
                fixPlayerDipping(platform3);
            }
            else if(player.getBoundsInParent().intersects(platform4.getBoundsInParent())&&player.getLayoutY() < platform4.getLayoutY()){
                fixPlayerDipping(platform4);
            }
            else if(player.getBoundsInParent().intersects(platform5.getBoundsInParent())&&player.getLayoutY() < platform5.getLayoutY()){
                fixPlayerDipping(platform5);
            }
            else if(player.getBoundsInParent().intersects(platform6.getBoundsInParent())&&player.getLayoutY() < platform6.getLayoutY()) {
                fixPlayerDipping(platform6);
            }
            else if(player.getBoundsInParent().intersects(borderBottom.getBoundsInParent())) {
                isFalling = false;
                player.setLayoutY(650);
                player.setLayoutX(128);
                System.out.println("Fell into the Void");
            }

            else{
                isFalling = true;
                System.out.println("No Collision");
            }
        if(isFalling && player.getBoundsInParent().intersects(coin1.getBoundsInParent()) && coin1.isVisible()){
            jumpSoundPlayer.seek(Duration.ZERO);
        }
        if(player.getBoundsInParent().intersects(portal.getBoundsInParent())) {
            isFalling = false;
            System.out.println("Level Beat!!!");
            scoreFinal();
            try {
                switchToLevelSelect();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (player.getBoundsInParent().intersects(coin1.getBoundsInParent()) && coin1.isVisible()) {
            bottleSoundPlayer.seek(Duration.ZERO);
            bottleSoundPlayer.play();
            coin1.setVisible(false);
            System.out.println("Collected coin 1");
            coin1Value = 100;
        }
        if (player.getBoundsInParent().intersects(coin2.getBoundsInParent()) && coin2.isVisible()) {
            bottleSoundPlayer.seek(Duration.ZERO);
            bottleSoundPlayer.play();
            coin2.setVisible(false);
            System.out.println("Collected coin 2");
            coin2Value = 100;
        }
        if (player.getBoundsInParent().intersects(coin3.getBoundsInParent()) && coin3.isVisible()) {
            bottleSoundPlayer.seek(Duration.ZERO);
            bottleSoundPlayer.play();
            coin3.setVisible(false);
            System.out.println("Collected coin 3");
            coin3Value = 100;
        }
        if (player.getBoundsInParent().intersects(coin4.getBoundsInParent()) && coin4.isVisible()) {
            bottleSoundPlayer.seek(Duration.ZERO);
            bottleSoundPlayer.play();
            coin4.setVisible(false);
            System.out.println("Collected coin 4");
            coin4Value = 100;
        }
        if (player.getBoundsInParent().intersects(coin5.getBoundsInParent()) && coin5.isVisible()) {
            bottleSoundPlayer.seek(Duration.ZERO);
            bottleSoundPlayer.play();
            coin5.setVisible(false);
            System.out.println("Collected coin 5");
            coin5Value = 100;
        }
    }

    public void checkCollision2() {
        double playerLeft = player.getLayoutX();
        double playerRight = player.getLayoutX() + player.getFitWidth();

        Rectangle[] platforms = {platform1, platform2, platform3, platform4, platform5, platform6};

        for (Rectangle platform : platforms) {
            if (player.getBoundsInParent().intersects(platform.getBoundsInParent()) && player.getLayoutY() > platform.getLayoutY() - 40) {
                double platformRight = platform.getLayoutX() + platform.getWidth();
                double platformLeft = platform.getLayoutX();

                if (playerRight > platformLeft && playerLeft < platformLeft) {
                    // Player is colliding with the left side of the platform
                    player.setLayoutX(platformLeft - player.getFitWidth());
                    System.out.println("LEFT WALL!?!?????????????????????");
                } else if (playerLeft < platformRight && playerRight > platformRight) {
                    // Player is colliding with the right side of the platform
                    player.setLayoutX(platformRight);
                    System.out.println("RIGHT WALL!?!?*******************");
                }

            }
        }
    }



    public void fixPlayerDipping(Rectangle platform){
        isFalling = false;
        player.setLayoutY(platform.getLayoutY()-player.getFitHeight());
        System.out.println("Collision");
    }
        @FXML
        private void switchToHome() throws IOException {
            HelloApplication.setRoot("home-view");
            movementTimer.stop();
            collisionTimer.stop();
            timeline.stop();
        }
        @FXML
        private void switchToLevelSelect() throws IOException {
            HelloApplication.setRoot("levelSelect-view");
            movementTimer.stop();
            collisionTimer.stop();
            timeline.stop();
        }
    private void switchToPostLevelSelect3() throws IOException {
        HelloApplication.setRoot("postLevel3-view");
        movementTimer.stop();
        collisionTimer.stop();
        timeline.stop();
    }
    public void timer() {
        timeCount.setText(String.valueOf(time));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time--;
            timeCount.setText(String.valueOf(time));
            if (time == 0) {
                timeline.stop();
                levelFailed.setText("Level Failed!");
                hideLevelFailedAfterDelay();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void scoreInitial() {
        coin1Value = 0;
        coin2Value = 0;
        coin3Value = 0;
        coin4Value = 0;
        coin5Value = 0;
    }
    public void scoreFinal(){
        int totalScore = addScore();
        scoreCount.setText(String.valueOf(totalScore));
    }
    public int addScore(){
        return coin1Value+coin2Value+coin3Value+coin4Value+coin5Value+(time*10);
    }
    private void hideLevelFailedAfterDelay() {
        Timeline hideTimeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            levelFailed.setText("");
            try {
                resetLevel();
                System.out.println("Level reset");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        hideTimeline.play();
    }
    private void resetLevel() throws IOException {
        player.setLayoutY(660);
        player.setLayoutX(128);
        coin1.setVisible(true);
        coin2.setVisible(true);
        coin3.setVisible(true);
        coin4.setVisible(true);
        coin5.setVisible(true);
        time = 30;
        timer();
        levelFailed.setText("");
    }
}