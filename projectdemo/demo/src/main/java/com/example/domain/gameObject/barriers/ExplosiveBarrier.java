package com.example.domain.gameObject.barriers;

import java.awt.Dimension;
import java.awt.Point;

public class ExplosiveBarrier extends Barrier {

    protected static Integer DEFAULT_HP = 1;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Redgem.png";
    protected static Dimension DEFAULT_SIZE = new Dimension(51, 21);

    public ExplosiveBarrier(Point p) {
        super(p, DEFAULT_IMG_PATH, DEFAULT_HP);
        setSize(DEFAULT_SIZE);
        hitBox.setSize(DEFAULT_SIZE);
    }
}
