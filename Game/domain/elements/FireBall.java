package domain.elements;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FireBall extends JLabel {
    protected int x, y; // Center of the fireball
    protected final int radius = 11; // Half the diameter for a 22x22 circle
    protected int dx, dy; // Direction and speed
    protected final int windowWidth;
    protected final int windowHeight;
    protected final ImageIcon imageIcon;

    public FireBall(int startX, int startY, int windowWidth, int windowHeight) {
        this.x = startX;
        this.y = startY;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.dx = 2; // Initial direction and speed
        this.dy = -2;
        this.imageIcon = new ImageIcon("Game/Graphical-Assets/Fireball.png");

        setIcon(imageIcon);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        updatePosition();
    }

    public void move() {
        x += dx;
        y += dy;
        checkBounds();
        updatePosition();
    }

    private void checkBounds() {
        // Adjust for circle collision with window borders
        if (x - radius <= 0 || x + radius >= windowWidth) {
            dx = -dx; // Reverse x direction
        }
        if (y - radius <= 0 || y + radius >= windowHeight) {
            dy = -dy; // Reverse y direction
        }
    }

    // bounce if intersects with barrier or staff hitbox
    public void bounce() {
        dx = -dx;
        dy = -dy;
    }

    private void updatePosition() {
        // Update JLabel's position to reflect FireBall's current location
        setLocation(x - radius, y - radius);
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    // Setters
    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
    
}
