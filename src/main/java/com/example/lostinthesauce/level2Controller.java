package com.example.lostinthesauce;

import javafx.fxml.FXML;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

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
    void start(ActionEvent event) {
        player.setLayoutY(240);
        player.setLayoutX(212);
    }

    private boolean wPressed;
    private boolean aPressed;
    private boolean dPressed;
    private double movementVar = 3.5;
    private double velY = 0;
    private final double GRAVITY = -0.5;
    private final double MAX_SPEED = 15;
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

    public void initialize() {
        movementSetup();
        movementTimer.start();
        collisionTimer.start();
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

        if(player.getBoundsInParent().intersects(platform1.getBoundsInParent())){
            isFalling = false;
            player.setLayoutY(platform1.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform2.getBoundsInParent())){
            isFalling = false;
            player.setLayoutY(platform2.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform3.getBoundsInParent())){
            isFalling = false;
            player.setLayoutY(platform3.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform4.getBoundsInParent())){
            isFalling = false;
            player.setLayoutY(platform4.getLayoutY()-40);
            System.out.println("Collision");
        }
        else if(player.getBoundsInParent().intersects(platform5.getBoundsInParent())){
            isFalling = false;
            player.setLayoutY(platform5.getLayoutY()-40);
            System.out.println("Collision");
        }
        else{
            isFalling = true;
            System.out.println("No Collision");
        }
    }
    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
    }
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
    }
}
