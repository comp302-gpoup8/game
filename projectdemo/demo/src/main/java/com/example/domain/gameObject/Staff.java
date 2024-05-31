package com.example.domain.gameObject;

import lombok.Getter;
import lombok.Setter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Staff Class
 * The staff is controlled by the player and moves horizontally.
 * It is used to keep the FireBall in the game. 
 */
@Getter @Setter
public class Staff extends GameObject {

    private static String IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/Player.png";
    private int rotation;

    public Staff(Point p, Dimension d){
        super(p, d, IMG_PATH);
        speed = 40;
        rotation = 0;
    }

    public int getRotation(){
        return rotation;
    }
    
    public void setRotation(double r){
        rotate(r - rotation);
    }

    public void rotate(double angle){
        this.getHitBox().rotate(angle);
        this.rotation += angle;
        paintComponent(getGraphics());
    }

    public void setWidth(int w){
        this.getHitBox().setWidth(w);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(rotation, getIcon().getIconWidth() / 2, getIcon().getIconHeight() / 2);
        g2.drawImage(getIcon().getImage(), 0, 0, null);
    }

}
