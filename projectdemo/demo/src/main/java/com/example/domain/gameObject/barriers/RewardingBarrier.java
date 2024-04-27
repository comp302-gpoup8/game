package com.example.domain.gameObject.barriers;

import java.awt.Point;
import java.awt.Dimension;

public class RewardingBarrier extends Barrier {

    protected static Integer DEFAULT_HP = 1;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Greengem.png";

    public RewardingBarrier(Point p, Dimension d) {
        super(p, d, DEFAULT_IMG_PATH, DEFAULT_HP);
    }
}
