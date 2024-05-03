package com.example.domain.gameObject.barriers;

import java.awt.Point;

/**
 * Explosive Barrier Class
 * An explosive barrier launches Debree when it is destroyed.
 */
public class ExplosiveBarrier extends Barrier {

    protected static Integer DEFAULT_HP = 1;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Redgem.png";

    public ExplosiveBarrier(Point p) {
        super(p, DEFAULT_IMG_PATH, DEFAULT_HP);
    }
}
