package com.example.lostinthesauce;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Window");

        // Create the root pane for the game elements
        Pane root = new Pane();
        root.setPrefSize(1600, 850); // Set initial size for the root pane

        // Add borders
        Border.addBorders(root);

        // Add Platforms
        Level.addPlatforms(root);

        // Debugging: Check if borders are added to the root pane
        System.out.println("Number of children in root pane: " + root.getChildren().size());

        // Create the scene
        Scene scene = new Scene(root);

        // Bind the scene size to the window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}