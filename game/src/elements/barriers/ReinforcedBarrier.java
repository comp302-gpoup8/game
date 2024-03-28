package elements.barriers;
import java.awt.Dimension;
import java.awt.Point;

public class ReinforcedBarrier extends Barrier {

    protected static int DEFAULT_HP = 3;
    protected static int DEFAULT_VALUE = 300;
    protected static String DEFAULT_IMAGE_PATH = "Graphical-Assets/Firm.png";
    protected static int DEFAULT_WIDTH = 50;
    protected static int DEFAULT_HEIGHT = 20;

    public ReinforcedBarrier(Point p) {
        super(p, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT), DEFAULT_IMAGE_PATH, DEFAULT_HP, DEFAULT_VALUE);
    }

}
