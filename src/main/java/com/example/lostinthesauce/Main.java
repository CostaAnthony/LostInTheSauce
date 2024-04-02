package com.example.lostinthesauce;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        int width = 1640; // Desired width of the area
        int height = 820; // Desired height of the area

        Level level = new Level(width, height);

        Group root = new Group();
        root.getChildren().add(level.getLevelGroup());

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lost in the Sauce (LEVEL 1)");
        primaryStage.show();

        level.addPlatform(4, 4); // Add a platform at row 4, column 4
    }

    public static void main(String[] args) {
        launch(args);
    }
}
