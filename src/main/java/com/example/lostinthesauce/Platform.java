package com.example.lostinthesauce;

import javafx.scene.shape.Rectangle;

public class Platform {

    private double x;
    private double y;
    private double width;
    private double height;

    public Platform(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle createShape() {
        return new Rectangle(x, y, width, height);
    }
}