package domain.elements;

import javax.swing.JLabel;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Staff extends JLabel{
    protected Point position;
    protected Dimension size;
    protected Rectangle hitbox;
    protected Integer speed;
    protected ImageIcon image;
    protected static String DEFAULT_IMAGE_PATH = "Game/Graphical-Assets/Player.png";

    public Staff(Point position, Dimension size) {
        this.position = position;
        this.size = size;
        this.speed = 5;
        this.image = new ImageIcon(DEFAULT_IMAGE_PATH);
        this.hitbox = new Rectangle(position, size);

        setIcon(this.image);
        setSize(this.size);
        setLocation(this.position.x, this.position.y);
    }

    public void moveLeft() {
        this.position.x -= this.speed;
        setLocation(this.position.x, this.position.y);
    }

    public void moveRight() {
        this.position.x += this.speed;
        setLocation(this.position.x, this.position.y);
    }

    public Point getPosition() {
        return this.position;
    }

    // public Rectangle getHitbox() {
    //     return this.hitbox;
    // }

    // public void setSpeed(Integer speed) {
    //     this.speed = speed;
    // }

    // public void setHitbox(Rectangle hitbox) {
    //     this.hitbox = hitbox;
    // }

    // public void setPosition(Point position) {
    //     this.position = position;
    // }

    // public void setSize(Dimension size) {
    //     this.size = size;
    // }
}
