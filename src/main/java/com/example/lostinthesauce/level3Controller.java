package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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

            if(player.getBoundsInParent().intersects(platform1.getBoundsInParent())){
                fixPlayerDipping(platform1);
            }
            else if(player.getBoundsInParent().intersects(platform2.getBoundsInParent())){
                fixPlayerDipping(platform2);
            }
            else if(player.getBoundsInParent().intersects(platform3.getBoundsInParent())){
                fixPlayerDipping(platform3);
            }
            else if(player.getBoundsInParent().intersects(platform4.getBoundsInParent())){
                fixPlayerDipping(platform4);
            }
            else if(player.getBoundsInParent().intersects(platform5.getBoundsInParent())){
                fixPlayerDipping(platform5);
            }
            else if(player.getBoundsInParent().intersects(platform6.getBoundsInParent())) {
                fixPlayerDipping(platform6);
            }
            else if(player.getBoundsInParent().intersects(borderBottom.getBoundsInParent())) {
                isFalling = false;
                player.setLayoutY(650);
                player.setLayoutX(128);
                System.out.println("Fell into the Void");
            }
            else if(player.getBoundsInParent().intersects(portal.getBoundsInParent())) {
                isFalling = false;
                System.out.println("Level Beat!!!");
                try {
                    switchToLevelSelect();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else{
                isFalling = true;
                System.out.println("No Collision");
            }

        if (player.getBoundsInParent().intersects(coin1.getBoundsInParent()) && coin1.isVisible()) {
            coin1.setVisible(false);
            System.out.println("Collected coin 1");
        }
        if (player.getBoundsInParent().intersects(coin2.getBoundsInParent()) && coin2.isVisible()) {
            coin2.setVisible(false);
            System.out.println("Collected coin 2");
        }
        if (player.getBoundsInParent().intersects(coin3.getBoundsInParent()) && coin3.isVisible()) {
            coin3.setVisible(false);
            System.out.println("Collected coin 3");
        }
        if (player.getBoundsInParent().intersects(coin4.getBoundsInParent()) && coin4.isVisible()) {
            coin4.setVisible(false);
            System.out.println("Collected coin 4");
        }
        if (player.getBoundsInParent().intersects(coin5.getBoundsInParent()) && coin5.isVisible()) {
            coin5.setVisible(false);
            System.out.println("Collected coin 5");
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
        }
        @FXML
        private void switchToLevelSelect() throws IOException {
            HelloApplication.setRoot("levelSelect-view");
            movementTimer.stop();
            collisionTimer.stop();
        }
    }