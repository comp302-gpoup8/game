package com.example.domain.gameObject;

import java.awt.Dimension;
import java.awt.Point;

/**
 * FireBall Class
 * A FireBall is launched by the player and damages the Barriers when it comes into contact with them.
 * The player loses a life if they lose possesion of the fireball (if it reaches to the bottom of the screen).
 * The fireball bounces off of the game objects as well as the boundaries of the Game Panel.
 */
public class FireBall extends GameObject {

    private static String IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Fireball.png";

    public FireBall(Point p, Dimension d) {
        super(p, d, IMG_PATH);
    }
    

}
