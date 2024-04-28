package com.example.domain.gameObject.barriers;

import java.awt.Point;
import java.awt.Dimension;

public class SimpleBarrier extends Barrier {

    protected static Integer DEFAULT_HP = 1;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Bluegem.png";

    public SimpleBarrier(Point p) {
        super(p, DEFAULT_IMG_PATH, DEFAULT_HP);
    }
}