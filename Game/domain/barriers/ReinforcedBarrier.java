package domain.barriers;

import java.awt.Dimension;
import java.awt.Point;

public class ReinforcedBarrier extends Barrier {
    
    protected static Integer DEFAULT_HITPOINTS = 3;
    protected static String DEFAULT_IMAGE_PATH = "Game/Graphical-Assets/Firm.png";

    public ReinforcedBarrier(Point position, Dimension size) {
        super(position, size, DEFAULT_HITPOINTS, DEFAULT_IMAGE_PATH);
    }
    
}
