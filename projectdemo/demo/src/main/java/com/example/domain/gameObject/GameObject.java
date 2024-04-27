package com.example.domain.gameObject;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class GameObject extends JLabel {
    
    protected Rectangle hitBox;
    protected ImageIcon icon;

    public GameObject(Point p, Dimension d, String imagePath){
        createHitbox(p, d);
        placeImageIcon(imagePath);
    }

    private void createHitbox(Point p, Dimension d){
        hitBox = new Rectangle(p, d);
    }

    private void placeImageIcon(String imagePath){
        icon = new ImageIcon(imagePath);
        setIcon(icon);
        setSize(hitBox.getSize());
        setLocation(hitBox.getLocation());
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public ImageIcon getIcon(){
        return icon;
    }

}
