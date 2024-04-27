package com.example.domain.gameObject.barriers;

import java.awt.Point;
import java.awt.Dimension;

public class ReinforcedBarrier extends Barrier {
    

    protected static Integer DEFAULT_HP = 3;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Firm.png";

    public ReinforcedBarrier(Point p, Dimension d){
        super(p, d, DEFAULT_IMG_PATH, DEFAULT_HP);
    }
}
