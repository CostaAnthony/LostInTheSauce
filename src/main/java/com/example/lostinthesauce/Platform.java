package com.example.lostinthesauce;

import javafx.scene.shape.Rectangle;

public class Platform {

    private double x;
    private double y;
    private double width;
    private double height;

    public Platform(double x, double y, double width, double height){
        x = this.x;
        y = this.y;
        width = this.width;
        height = this.height;
    }

    public Rectangle createPlatform(){
        return new Rectangle(x, y, width, height);
    }
}
