package com.example.domain.elements;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FireBall extends JLabel implements BallMovement{
    public static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Fireball.png";
    public Point position, direction;
    public Dimension size;
    public Integer speed;
    public Rectangle hitBox;

    public FireBall(Point p, Dimension d){
        createHitbox(p, d);
        speed = 20;
        placeImageIcon(DEFAULT_IMG_PATH);
    }
    
    private void createHitbox(Point p, Dimension d) {
        position = p;
        size = d;
        hitBox = new Rectangle(p, d);
    }

    private void placeImageIcon(String path) {
        setIcon(new ImageIcon(path));
        setSize(hitBox.getSize());
        setLocation(hitBox.getLocation());
    }
}
