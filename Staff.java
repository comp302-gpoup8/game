import java.awt.Point;
import java.awt.Dimension;

public class Staff extends GameElement {
    private Integer speed = 10; // Speed of staff movement
    private final Integer windowWidth;

    public Staff(int startX, int startY, int windowWidth) {
        super(new Point(startX, startY), new Dimension(16, 16), "Graphical-Assets/Player.png"); 
        this.windowWidth = windowWidth;
    }

    public void moveLeft() {
        if (getPoint().x - speed >= 0) {
            getPoint().setLocation(getPoint().x - speed, getPoint().y);
        }
    }

    public void moveRight() {
        if (getPoint().x + getSize().width + speed <= windowWidth) {
            getPoint().setLocation(getPoint().x + speed, getPoint().y);
        }
    }
}
