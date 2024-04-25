package com.example.barriers;

import java.awt.Point;
import java.awt.Dimension; 
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
/**
 * Abstract Barrier:
 * Contains the fields shared for every barrier type and the constructor
 */
public abstract class Barrier extends JLabel { 
    protected Rectangle hitBox;
    protected Integer hitpoints;

    public Barrier(Point p, Dimension d, Integer hp, String imagePath){
        createHitbox(p, d);
        placeImageIcon(imagePath);
    }

    private void createHitbox(Point p, Dimension d){
        hitBox = new Rectangle(p, d);
    }

    private void placeImageIcon(String path){
        setIcon(new ImageIcon(path));
        setSize(hitBox.getSize());
        setLocation(hitBox.getLocation());
    }
}
