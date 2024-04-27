package com.example.domain.gameObject;

import java.awt.Dimension;
import java.awt.Point;

public class FireBall extends GameObject {

    private static String IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Fireball.png";

    public FireBall(Point p, Dimension d) {
        super(p, d, IMG_PATH);
    }
    

}
