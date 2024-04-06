package com.example.lostinthesauce;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Border {

    public static void addBorders(Pane root) {
        double width = root.getPrefWidth();
        double height = root.getPrefHeight();

        Rectangle topBorder = new Rectangle(0, 0, width, 10);
        Rectangle bottomBorder = new Rectangle(0, height - 10, width, 10);
        Rectangle leftBorder = new Rectangle(0, 0, 10, height);
        Rectangle rightBorder = new Rectangle(width - 10, 0, 10, height);

        root.getChildren().addAll(topBorder, bottomBorder, leftBorder, rightBorder);
    }
}