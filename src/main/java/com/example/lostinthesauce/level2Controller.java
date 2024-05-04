package com.example.lostinthesauce;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class level2Controller {
    @FXML
    private Pane scene;
    @FXML
    private Rectangle player;
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
    private Circle coin1;
    @FXML
    private Circle coin2;
    @FXML
    private Circle coin3;
    @FXML
    private Circle coin4;
    @FXML
    private Circle coin5;
    @FXML
    private Rectangle borderBottom;
    @FXML
    private Circle portal;
    @FXML
    public TextField timeCount;
    public int time = 30;
    private Timeline timeline;
    public int coin1Value;
    public int coin2Value;
    public int coin3Value;
    public int coin4Value;
    public int coin5Value;
    @FXML
    void start(ActionEvent event) {
        player.setLayoutY(650);
        player.setLayoutX(212);
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

    public void checkCollision(){

        if(player.getBoundsInParent().intersects(platform1.getBoundsInParent())&&player.getLayoutY() < platform1.getLayoutY()){
            isFalling = false;
            player.setLayoutY(platform1.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform2.getBoundsInParent())&&player.getLayoutY() < platform2.getLayoutY()){
            isFalling = false;
            player.setLayoutY(platform2.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform3.getBoundsInParent())&&player.getLayoutY() < platform3.getLayoutY()){
            isFalling = false;
            player.setLayoutY(platform3.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform4.getBoundsInParent())&&player.getLayoutY() < platform4.getLayoutY()){
            isFalling = false;
            player.setLayoutY(platform4.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform5.getBoundsInParent())&&player.getLayoutY() < platform5.getLayoutY()){
            isFalling = false;
            player.setLayoutY(platform5.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(borderBottom.getBoundsInParent())) {
            isFalling = false;
            player.setLayoutY(650);
            player.setLayoutX(212);
            System.out.println("Fell into the Void");
        }
        else{
            isFalling = true;
            System.out.println("No Collision");
        }
        if(isFalling && player.getBoundsInParent().intersects(coin1.getBoundsInParent()) && coin1.isVisible()){
            jumpSoundPlayer.seek(Duration.ZERO);
        }
        if (player.getBoundsInParent().intersects(portal.getBoundsInParent())) {
            isFalling = false;
            System.out.println("Level Beat!!!");
            try {
                switchToPostLevelSelect2();
            } catch (IOException e) {
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

    public void checkCollision2(){
        double playerLeft = player.getLayoutX();
        double playerRight = player.getLayoutX() + player.getWidth();
        double playerWidth = player.getWidth();
        double playerHeight = player.getHeight();


        if (player.getBoundsInParent().intersects(platform1.getBoundsInParent()) && player.getLayoutY() > platform1.getLayoutY() - 40) {
            double platform1Right = platform1.getLayoutX() + platform1.getWidth();
            double platform1Left = platform1.getLayoutX();

            if (playerLeft < platform1Right && playerLeft > platform1Left) {
                // Player is colliding with the right side of the platform, prevent further left movement
                player.setLayoutX(platform1Right);
                System.out.println("RIGHT WALL1!?!?!******************");
            }

            // Check collision with the left side of the platform
            if (player.getBoundsInParent().intersects(platform1.getBoundsInParent()) && player.getLayoutY() > platform1.getLayoutY() - 40) {
                if (playerRight > platform1Left && playerRight < platform1Right) {
                    // Player is colliding with the left side of the platform, prevent further right movement
                    player.setLayoutX(platform1Left - playerWidth);
                    System.out.println("LEFT WALL1!?!?????????????????????");
                }
            }
        }
        if (player.getBoundsInParent().intersects(platform2.getBoundsInParent()) && player.getLayoutY() > platform2.getLayoutY() - 40) {
            double platform2Right = platform2.getLayoutX() + platform2.getWidth();
            double platform2Left = platform2.getLayoutX();

            if (playerLeft < platform2Right && playerLeft > platform2Left) {
                // Player is colliding with the right side of the platform, prevent further left movement
                player.setLayoutX(platform2Right);
                System.out.println("RIGHT WALL2!?!?!******************");
            }

            // Check collision with the left side of the platform
            if (player.getBoundsInParent().intersects(platform2.getBoundsInParent()) && player.getLayoutY() > platform2.getLayoutY() - 40) {
                if (playerRight > platform2Left && playerRight < platform2Right) {
                    // Player is colliding with the left side of the platform, prevent further right movement
                    player.setLayoutX(platform2Left - playerWidth);
                    System.out.println("LEFT WALL2!?!?????????????????????");
                }
            }
        }
        if (player.getBoundsInParent().intersects(platform3.getBoundsInParent()) && player.getLayoutY() > platform3.getLayoutY() - 40) {
            double platform3Right = platform3.getLayoutX() + platform3.getWidth();
            double platform3Left = platform3.getLayoutX();

            if (playerLeft < platform3Right && playerLeft > platform3Left) {
                // Player is colliding with the right side of the platform, prevent further left movement
                player.setLayoutX(platform3Right);
                System.out.println("RIGHT WALL3!?!?!******************");
            }

            // Check collision with the left side of the platform
            if (player.getBoundsInParent().intersects(platform3.getBoundsInParent()) && player.getLayoutY() > platform3.getLayoutY() - 40) {
                if (playerRight > platform3Left && playerRight < platform3Right) {
                    // Player is colliding with the left side of the platform, prevent further right movement
                    player.setLayoutX(platform3Left - playerWidth);
                    System.out.println("LEFT WALL3!?!?????????????????????");
                }
            }
        }
        if (player.getBoundsInParent().intersects(platform4.getBoundsInParent()) && player.getLayoutY() > platform4.getLayoutY() - 40) {
            double platform4Right = platform4.getLayoutX() + platform4.getWidth();
            double platform4Left = platform4.getLayoutX();

            if (playerLeft < platform4Right && playerLeft > platform4Left) {
                // Player is colliding with the right side of the platform, prevent further left movement
                player.setLayoutX(platform4Right);
                System.out.println("RIGHT WALL4!?!?!******************");
            }

            // Check collision with the left side of the platform
            if (player.getBoundsInParent().intersects(platform4.getBoundsInParent()) && player.getLayoutY() > platform4.getLayoutY() - 40) {
                if (playerRight > platform4Left && playerRight < platform4Right) {
                    // Player is colliding with the left side of the platform, prevent further right movement
                    player.setLayoutX(platform4Left - playerWidth);
                    System.out.println("LEFT WALL4!?!?????????????????????");
                }
            }
        }
        if (player.getBoundsInParent().intersects(platform5.getBoundsInParent()) && player.getLayoutY() > platform5.getLayoutY() - 40) {
            double platform5Right = platform5.getLayoutX() + platform5.getWidth();
            double platform5Left = platform5.getLayoutX();

            if (playerLeft < platform5Right && playerLeft > platform5Left) {
                // Player is colliding with the right side of the platform, prevent further left movement
                player.setLayoutX(platform5Right);
                System.out.println("RIGHT WALL5!?!?!******************");
            }

            // Check collision with the left side of the platform
            if (player.getBoundsInParent().intersects(platform5.getBoundsInParent()) && player.getLayoutY() > platform5.getLayoutY() - 40) {
                if (playerRight > platform5Left && playerRight < platform5Right) {
                    // Player is colliding with the left side of the platform, prevent further right movement
                    player.setLayoutX(platform5Left - playerWidth);
                    System.out.println("LEFT WALL5!?!?????????????????????");
                }
            }
        }
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
    private void switchToPostLevelSelect2() throws IOException {
        HelloApplication.setRoot("postLevel2-view");
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
}
