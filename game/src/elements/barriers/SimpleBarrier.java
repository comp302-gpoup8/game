package domain.barriers;

import java.awt.Dimension;
import java.awt.Point;

public class SimpleBarrier extends Barrier {

    protected static Integer DEFAULT_HITPOINTS = 1;
    protected static String DEFAULT_IMAGE_PATH = "Game/Graphical-Assets/BlueGem.png";

    public SimpleBarrier(Point position, Dimension size) {
        super(position, size, DEFAULT_HITPOINTS, DEFAULT_IMAGE_PATH);
    }

}
