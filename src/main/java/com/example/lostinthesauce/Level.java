package com.example.lostinthesauce;

import javafx.scene.layout.Pane;

public class Level {

    public static void addPlatforms(Pane root) {
        // Add platforms
        Platform Floor = new Platform(10, 800, 1580, 40);
        Platform platform1 = new Platform(160, 700, 383, 100);
        Platform platform2 = new Platform(693, 600, 383, 200);
        Platform platform3 = new Platform(1309, 500, 281, 75);

        root.getChildren().addAll(Floor.createShape(), platform1.createShape(), platform2.createShape(), platform3.createShape());
    }
}