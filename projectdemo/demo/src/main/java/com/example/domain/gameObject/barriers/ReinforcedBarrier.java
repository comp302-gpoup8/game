package com.example.domain.gameObject.barriers;

import java.awt.Point;
/**
 * Reinforced Barrier Class
 * Also referred as Firm Barrier
 * Has 3 hitPoints instead of the default 1, making it harder to destroy.
 */
public class ReinforcedBarrier extends Barrier {
    

    protected static Integer DEFAULT_HP = 3;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Firm.png";

    public ReinforcedBarrier(Point p){
        super(p, DEFAULT_IMG_PATH, DEFAULT_HP);
    }
}
