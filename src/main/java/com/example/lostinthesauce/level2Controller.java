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

public class level2Controller {

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
    private ImageView coin1;
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
    public static int totalScore;
    private MediaPlayer musicPlayerLevel2;

    public int getCoin1Value() {
        return coin1Value;
    }

    public void setCoin1Value(int coin1Value) {
        this.coin1Value = coin1Value;
    }
/** Sets the player at the default location
 * @param ActionEvent event
 */
    @FXML
    void start(ActionEvent event) {
        player.setLayoutY(623);
        player.setLayoutX(220);
    }

    private boolean wPressed;
    private boolean aPressed;
    private boolean dPressed;
    private double movementVar = 8;
    private double velY = 0;
    private final double GRAVITY = -0.7;
    private final double MAX_SPEED = 30;
    private boolean isFalling = false;
    String userHome = System.getProperty("user.home");
    String jumpSoundPath = userHome + "/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/Jump.mp3";
    Media jumpSound = new Media(new File(jumpSoundPath).toURI().toString());
    MediaPlayer jumpSoundPlayer = new MediaPlayer(jumpSound); //MediaPlayer to play the sound
    String bottleSoundMP3 = userHome + "/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/bottle2.mp3";
    Media bottleSound = new Media(new File(bottleSoundMP3).toURI().toString());
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

                jumpSoundPlayer.seek(Duration.ZERO);
                jumpSoundPlayer.play();
                //Resets the jump sound back to the beginning of the track
                velY += 10.5;
                System.out.println("JUMP SOUND");
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

    /** Each AnimationTimer plays a specific role in the program
     * movementTimer checks for inputs to move the Player, as well as implements gravity
     * collisionTimer checks for collision from the top of each platform,
     * as well as the collision for the player and coins
     * collisionTimer2 checks for collision from the sides of each platform
     *
     * timer() starts a timer for the level.
     *
     * scoreInitial() programs the score setup for the level
     *
     * The initialize method also sets up sound effects and background music
     *
     */
    public void initialize() {
        movementSetup();
        movementTimer.start();
        collisionTimer.start();
        collisionTimer2.start();
        timer();
        scoreInitial();
        String level2Music = userHome + "/IdeaProjects/LostInTheSauce/src/main/resources/com/example/lostinthesauce/level2Music.mp3";
        Media level2Sound = new Media(new File(level2Music).toURI().toString());
        musicPlayerLevel2 = new MediaPlayer(level2Sound);
        musicPlayerLevel2.play();

        musicPlayerLevel2.setOnEndOfMedia(
                () -> {
                    musicPlayerLevel2.seek(Duration.ZERO);
                    musicPlayerLevel2.play();
                }
        );
    }

    /** Checks for inputs, and causes the player object to move once inputs are detected
     */
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

    /** Checks for collision between the player and the top of platforms and coins
     */
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
        else if(player.getBoundsInParent().intersects(borderBottom.getBoundsInParent())) {
            isFalling = false;
            player.setLayoutY(623);
            player.setLayoutX(220);
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
            scoreFinal();
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

    /** Checks for collision between the Player and the sides of Platforms
     *
     */
    public void checkCollision2() {
        double playerLeft = player.getLayoutX();
        double playerRight = player.getLayoutX() + player.getFitWidth();

        Rectangle[] platforms = {platform1, platform2, platform3, platform4, platform5};

        for (Rectangle platform : platforms) {
            if (player.getBoundsInParent().intersects(platform.getBoundsInParent()) && player.getLayoutY() > platform.getLayoutY() - player.getFitHeight()) {
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


    /**
     * Prevents the player from dipping into platforms and snaps them back up if they do
     * @param Rectangle platform
     */
    public void fixPlayerDipping(Rectangle platform){
        isFalling = false;
        player.setLayoutY(platform.getLayoutY()-player.getFitHeight());
        System.out.println("Collision");
    }
    /** Switches to home
     * @throws IOException
     */
    @FXML
    private void switchToHome() throws IOException {
        HelloApplication.setRoot("home-view");
        movementTimer.stop();
        musicPlayerLevel2.stop();
        collisionTimer.stop();
        timeline.stop();
    }
    /** Switches to level select
     * @throws IOException
     */
    @FXML
    private void switchToLevelSelect() throws IOException {
        HelloApplication.setRoot("levelSelect-view");
        movementTimer.stop();
        collisionTimer.stop();
        musicPlayerLevel2.stop();
        timeline.stop();
    }
    /**Switches to post level select
     * @throws IOException
     */
    private void switchToPostLevelSelect2() throws IOException {
        HelloApplication.setRoot("postLevel2-view");
        movementTimer.stop();
        collisionTimer.stop();
        musicPlayerLevel2.stop();
        timeline.stop();
    }
    /** Creates a timer
     */
    public void timer() {
        timeCount.setText(String.valueOf(time));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time--;
            timeCount.setText(String.valueOf(time));
            if (time == 0) {
                timeline.stop();
                try {
                    resetLevel();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                levelFailed.setText("Level Failed!");
                hideLevelFailedAfterDelay();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /** Sets the score value for each coin
     */
    public void scoreInitial() {
        coin1Value = 0;
        coin2Value = 0;
        coin3Value = 0;
        coin4Value = 0;
        coin5Value = 0;
    }
    /** Calculates the final score using the addScore() method
     */
    public void scoreFinal(){
        totalScore = addScore();
        scoreCount.setText(String.valueOf(totalScore));
        System.out.println("Total Score is: " + totalScore);
        HelloApplication.fstore.collection("Users").document(SignInController.currentUser.getUsername()+SignInController.currentUser.getPassword()).update("Level 2 HiScore",scoreCount.getText());
    }
    /** Adds the score accumulated by each coin as well as the timer
     * @return the int value for the total score
     */
    public int addScore(){
        return coin1Value+coin2Value+coin3Value+coin4Value+coin5Value+(time*10);
    }
    /** Hides the level after the user fails the level
     */
    private void hideLevelFailedAfterDelay() {
        Timeline hideTimeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            levelFailed.setText("");
            System.out.println("Level reset");
        }));
        hideTimeline.play();
    }
    /** Resets the level to default
     * @throws IOException
     */
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
