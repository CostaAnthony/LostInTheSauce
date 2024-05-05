package com.example.lostinthesauce;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class coin {
    private ImageView circle;

    private Image sauce = new Image("@Assets/hot sauce.png");
    private boolean collected;
    private int touchCount;

    public coin(double centerX, double centerY) {
        circle = new ImageView();
        circle.setImage(sauce);
        circle.setLayoutX(centerX);
        circle.setLayoutX(centerY);
        this.collected = false;
        this.touchCount = 0;
    }

    public ImageView getCircle() {
        return circle;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
        circle.setVisible(!collected);
    }

    public int getTouchCount() {
        return touchCount;
    }

    public void incrementTouchCount() {
        touchCount++;
        System.out.println(touchCount);
    }

}
