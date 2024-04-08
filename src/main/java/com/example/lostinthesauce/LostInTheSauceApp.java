package com.example.lostinthesauce;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LostInTheSauceApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Level.fxml"));
        primaryStage.setScene(new Scene(root, 1705.0, 890.0));
        primaryStage.setTitle("Lost in the Sauce");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}