package com.example.domain.elements;


import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Staff extends JLabel implements StaffMovement {

    protected Rectangle hitBox;
    protected Integer speed;
    protected ImageIcon image;
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Player.png";

    public Staff (Point p, Dimension d){
        createHitbox(p, d);
        placeImageIcon(DEFAULT_IMG_PATH);
        speed = 5;
    }
    
    private void createHitbox(Point p, Dimension d) {
        hitBox = new Rectangle(p, d);
    }

    private void placeImageIcon(String path) {
        setIcon(new ImageIcon(path));
        setSize(hitBox.getSize());
        setLocation(hitBox.getLocation());
    }
}
