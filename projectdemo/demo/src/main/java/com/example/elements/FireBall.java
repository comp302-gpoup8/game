package com.example.elements;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FireBall extends JLabel implements BallMovement{
    protected static String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Fireball.png";
    protected Point position, direction;
    protected Dimension size;

    public FireBall(Point p){
        position = p;
        direction = new Point(0, 0);
        size = new Dimension(11, 11);
        placeImageIcon(DEFAULT_IMG_PATH);
    }
    
    private void placeImageIcon(String path) {
        setIcon(new ImageIcon(path));
    }
}
