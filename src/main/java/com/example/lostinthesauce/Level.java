package com.example.lostinthesauce;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level {
    private int[][] levelData;
    private int numRows;
    private int numCols;
    private Group levelGroup;

    private static final int TILE_SIZE = 50;

    public Level(int width, int height) {
        // Calculate the number of rows and columns based on the specified area
        this.numRows = height / TILE_SIZE;
        this.numCols = width / TILE_SIZE;

        // Initialize the level data array with the calculated size
        this.levelData = new int[numRows][numCols];

        // Fill the borders with platform tiles
        fillBorders();

        // Create the group to hold all elements of the level
        this.levelGroup = new Group();

        // Load the level
        loadLevel();
    }

    private void fillBorders() {
        // Fill top and bottom borders
        for (int i = 0; i < numCols; i++) {
            levelData[0][i] = 1; // Top border
            levelData[numRows - 1][i] = 1; // Bottom border
        }
        // Fill left and right borders
        for (int i = 0; i < numRows; i++) {
            levelData[i][0] = 1; // Left border
            levelData[i][numCols - 1] = 1; // Right border
        }
    }

    public void addPlatform(int row, int col) {
        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            levelData[row][col] = 1; // Set the specified position as a platform
            loadLevel();
        }
    }
    private void loadLevel() {
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                int tileType = levelData[y][x];
                if (tileType == 1) { // 1 represents a platform tile
                    Rectangle platform = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    platform.setFill(Color.GRAY);
                    levelGroup.getChildren().add(platform);
                }
            }
        }
    }

    public Group getLevelGroup() {
        return levelGroup;
    }
}
