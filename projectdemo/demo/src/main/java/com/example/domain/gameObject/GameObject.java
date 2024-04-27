package com.example.domain.gameObject;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class GameObject extends JLabel {
    
    protected Rectangle hitBox;
    protected ImageIcon icon;
    protected Integer speed;
    protected Point direction;

    public GameObject(Point p, Dimension d, String imagePath){
        createHitbox(p, d);
        placeImageIcon(imagePath);
        defaultMovement();
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

    private void defaultMovement(){
        speed = 0;
        direction = new Point(0, 0);
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int s){
        speed = s;
    }

}
