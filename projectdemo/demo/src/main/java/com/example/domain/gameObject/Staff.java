package com.example.domain.gameObject;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Staff Class
 * The staff is controlled by the player and moves horizontally.
 * It is used to keep the FireBall in the game. 
 */
public class Staff extends GameObject {

    private static String IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Player.png";
    
    public Staff(Point p, Dimension d){
        super(p, d, IMG_PATH);
        speed = 40;
    }
}
