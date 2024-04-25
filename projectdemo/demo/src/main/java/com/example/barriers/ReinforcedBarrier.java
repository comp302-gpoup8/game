package com.example.barriers;

import java.awt.Point;
import java.awt.Dimension;

public class ReinforcedBarrier extends Barrier {

    protected static Integer DEFAULT_HP = 3;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Firm.png";

    public ReinforcedBarrier(Point p, Dimension d){
        super(p, d, DEFAULT_HP, DEFAULT_IMG_PATH);
    }
}
