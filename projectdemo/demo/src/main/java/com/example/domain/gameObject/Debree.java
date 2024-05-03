package com.example.domain.gameObject;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Debree Class
 * Debree is launched from the Explosive Barriers once they are destroyed.
 * They damage the Player if it touches the Staff.
 * It passes through barriers.
 */
public class Debree extends GameObject {

    private static String IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/iconredgem.png";

    public Debree(Point p, Dimension d) {
        super(p, d, IMG_PATH);
    }
    //TODO
}