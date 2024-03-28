package elements.barriers;
import java.awt.Dimension;
import java.awt.Point;

public class ExplosiveBarrier extends Barrier {

    protected static int DEFAULT_HP = 1;
    protected static int DEFAULT_VALUE = 150;
    protected static String DEFAULT_IMAGE_PATH = "Graphical-Assets/RedGem.png";
    protected static int DEFAULT_WIDTH = 50;
    protected static int DEFAULT_HEIGHT = 20;

    public ExplosiveBarrier(Point p){
        super(p, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT), DEFAULT_IMAGE_PATH, DEFAULT_HP, DEFAULT_VALUE);
    }
}
