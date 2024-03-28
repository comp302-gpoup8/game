import java.awt.Dimension;
import java.awt.Point;

public class RewardingBarrier extends Barrier {

    protected static Integer DEFAULT_HP = 1;
    protected static Integer DEFAULT_VALUE = 150;
    protected static String DEFAULT_IMAGE_PATH = "Graphical-Assets/GreenGem.png";
    protected static Integer DEFAULT_WIDTH = 50;
    protected static Integer DEFAULT_HEIGHT = 20;

    public RewardingBarrier(Point p) {
        super(p, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT), DEFAULT_IMAGE_PATH, DEFAULT_HP, DEFAULT_VALUE);
    }

}
