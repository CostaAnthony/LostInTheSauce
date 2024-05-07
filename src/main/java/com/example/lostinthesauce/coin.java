package com.example.lostinthesauce;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class coin {
    private ImageView circle;

    private Image sauce = new Image("@Assets/hot sauce.png");


    public coin(double centerX, double centerY) {
        circle = new ImageView();
        circle.setImage(sauce);
        circle.setLayoutX(centerX);
        circle.setLayoutX(centerY);
    }

    public ImageView getCircle() {
        return circle;
    }

    public void setCollected(boolean collected) {
        circle.setVisible(!collected);
    }

}
