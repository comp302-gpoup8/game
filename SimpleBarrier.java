import java.awt.Dimension;
import java.awt.Point;

public class SimpleBarrier extends Barrier {

    private static Integer DEFAULT_HP = 1;
    private static Integer DEFAULT_VALUE = 100;
    private static String DEFAULT_IMAGE_PATH = "Graphical-Assets/BlueGem.png";
    private static Integer DEFAULT_WIDTH = 50;
    private static Integer DEFAULT_HEIGHT = 20;
    public SimpleBarrier(Point p) {
        super(p, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT), DEFAULT_IMAGE_PATH, DEFAULT_HP, DEFAULT_VALUE);
    }
    
}
