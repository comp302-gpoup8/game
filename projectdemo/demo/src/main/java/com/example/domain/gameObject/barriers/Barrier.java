package com.example.domain.gameObject.barriers;

import java.awt.Dimension;
import java.awt.Point;

import com.example.domain.gameObject.GameObject;

/**
 * Barrier Class (Abstract)
 * Contains the common attributes shared by the different barrier types.
 */
public abstract class Barrier extends GameObject {

    /**
     * Hitpoints of the barrier. A barrier is destroyed when it reaches 0 hitpoints.
     */
    protected Integer hitPoints;

    /**
     * Default size for all barrier types. 
     */
    protected static Dimension DEFAULT_BARRIER_SIZE = new Dimension(51, 21);

    public Barrier(Point p, String imagePath, Integer hp){
        super(p, DEFAULT_BARRIER_SIZE, imagePath);
        hitPoints = hp;

    }

    /**
     * Reduces the hitpoints of the barrier by the given value.
     * @param damage
     */
    public void reduceHp(int damage){
        hitPoints -= damage;
    }

    /**
     * Returns true if the barrier is destroyed, meaning it has 0 > hitpoints.
     * @return
     */
    public boolean isDestroyed(){
        return hitPoints <= 0;
    }

    //Getters and setters.
    public int getHitpoints(){
        return hitPoints;
    }
}
