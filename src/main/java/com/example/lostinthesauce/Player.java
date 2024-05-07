package com.example.lostinthesauce;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Player {

    private int x;
    private int y;
    private boolean isFalling;
    private double velY = 0;
    private final double GRAVITY = -0.5;
    private final double MAX_SPEED = 30;
    private ArrayList<ImageView> coinList = new ArrayList<ImageView>();
    private ArrayList<Rectangle> platformList = new ArrayList<Rectangle>();

    public Player(int x, int y){
        x = this.x;
        y = this.y;

    }

    public void playerGravity(ImageView player){
        player.setLayoutY(player.getLayoutY() - velY);
        if (isFalling) {
            velY += GRAVITY;
            if (velY >= MAX_SPEED) {
                velY = MAX_SPEED;
            }
        } else if(!isFalling) {
            velY = 0;

        }
    }

    public void platformCollision(ArrayList<Rectangle> platforms, ImageView player){
        for(Rectangle i : platforms){
            if(player.getBoundsInParent().intersects(i.getBoundsInParent())&&player.getLayoutY() < i.getLayoutY()){
                isFalling = false;
                player.setLayoutY(i.getLayoutY()-player.getFitHeight());
                System.out.println("Collision");
            }
        }
    }

    public void coinCollision(ArrayList<ImageView> coins, ImageView player){
        for(ImageView i : coinList){
            i.setVisible(false);
            System.out.println("Collected coin 1");
        }
    }

}
