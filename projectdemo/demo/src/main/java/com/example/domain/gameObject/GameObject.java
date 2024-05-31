package com.example.domain.gameObject;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

/** GameObject Abstract Class
 * Contains the common attributes for all GameObjects.
 * A game object is any element in the game such as the staff, barrier and fireball.
 */
public abstract class GameObject extends JLabel {
    
    /**
     *  Hitbox of the object. Two gameobjects intersect if their hitBoxes intersect 
     */
    protected HitBox hitBox;
    /** 
     * ImageIcon of the GameObject. All GameObject classes have unique icons.  
     */
    protected ImageIcon icon;
    /** Speed of the GameObject. Static gameObjects such as barriers have this value set to 0. 
     * In later versions they may move.
     */
    protected Integer speed;
    /**
     * The direction that the GameObject moves in.
     */
    protected Point direction;

    /**
     * Constructor
     * @param p : Initial location of the GameObject
     * @param d : Size of the Game Object in two dimensions.
     * @param imagePath : Location of the ImageIcon of the Object.
     */
    public GameObject(Point p, Dimension d, String imagePath){
        createHitbox(p, d);
        placeImageIcon(imagePath);
        defaultMovement();
    }



    /**
     * Creates the hitbox of the GameObject with the given values.
     * @param p : Initial Point
     * @param d : Size
     */
    protected void createHitbox(Point p, Dimension d){
        hitBox = new HitBox(p, d);
    }

    /**
     * Locates the ImageIcon associated with the object and places it on its hitbox.
     * @param imagePath
     */
    protected void placeImageIcon(String imagePath){
        icon = new ImageIcon(imagePath);
        setIcon(icon);
        setSize(hitBox.getSize());
        setLocation(hitBox.getLocation());
    }

    /**
     * Initializes the default movement values for the gameObject.
     */
    protected void defaultMovement(){
        speed = 0;
        direction = new Point(-1, -1);
    }

    // Getters / Setters  
    public HitBox getHitBox(){
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

    public Point getDirection(){
        return direction;
    }

    public void setDirection(Point p){
        direction = p;
    }

}
