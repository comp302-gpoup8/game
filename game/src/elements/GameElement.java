package elements;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
     * GameElement Class:
     * GameElement refers to all game components you can see within the level
     * such as the fireball, the staff, and the different barrier types.
     * It is anything within a level that has a image, and can intersect with another element.
 */
public abstract class GameElement extends JPanel{
    protected Point point; //Top left corner of the element.
    protected Dimension size;
    public ImageIcon imageIcon;

    public GameElement(Point p, Dimension s, String iPath){
        point = p;
        size = s;
        imageIcon = new ImageIcon(iPath);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imageIcon.getImage(), 0, 0, size.width, size.height, this);
    }

    public Point getPoint(){
        return point;
    }

    public Dimension getSize(){
        return size;
    }

    public ImageIcon getImageIcon(){
        return imageIcon;
    }

    public void setPoint(Point p){
        point = p;
    }

    public void setElementSize(Dimension s){
        size = s;
    }

    public void setImageIcon(ImageIcon i){
        imageIcon = i;
    }

    public void setImageIcon(String s){
        setImageIcon(new ImageIcon(s));
    }
}
