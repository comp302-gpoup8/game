package com.example.domain.gameObject;

import java.awt.Dimension;
import java.awt.Point;

public class Debree extends GameObject {

    private static String IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/iconredgem.png";

    public Debree(Point p, Dimension d) {
        super(p, d, IMG_PATH);
    }
}