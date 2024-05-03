package com.example.lostinthesauce;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class coin {
    private Circle circle;
    private boolean collected;
    private int touchCount;

    public coin(double centerX, double centerY) {
        circle = new Circle(centerX, centerY, 20, Color.GOLD);
        this.collected = false;
        this.touchCount = 0;
    }

    public Circle getCircle() {
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
