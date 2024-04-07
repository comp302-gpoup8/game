package domain.barriers;
import javax.swing.JLabel;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Barrier extends JLabel {
    protected Point position;
    protected Dimension size;
    protected Rectangle hitbox;
    protected Integer hitpoints;
    protected ImageIcon image;

    public Barrier(Point position, Dimension size, Integer hitpoints, String imagePath) {
        this.position = position;
        this.size = size;
        this.hitpoints = hitpoints;
        this.image = new ImageIcon(imagePath);
        this.hitbox = new Rectangle(position, size);

        // Set the JLabel properties
        setIcon(this.image);
        setSize(this.size);
        setLocation(this.position.x, this.position.y);
    }
}
