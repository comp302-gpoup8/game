package com.example.domain.gameObject.barriers;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

import com.example.domain.gameObject.GameObject;

import lombok.Getter;
import lombok.Setter;

/**
 * Barrier Class (Abstract)
 * Contains the common attributes shared by the different barrier types.
 */
@Getter @Setter
public abstract class Barrier extends GameObject {

    /**
     * Hitpoints of the barrier. A barrier is destroyed when it reaches 0 hitpoints.
     */
    protected Integer hitPoints;
    protected Boolean frozen;
    protected boolean isMoving;

    /**
     * Default size for all barrier types. 
     */
    protected static Dimension DEFAULT_BARRIER_SIZE = new Dimension(51, 21);

    public Barrier(Point p, String imagePath, Integer hp){
        super(p, DEFAULT_BARRIER_SIZE, imagePath);
        hitPoints = hp;
        frozen = false;
        Random rand = new Random();
        isMoving = (rand.nextInt(1, 100) + 1) > 90;
        if (isMoving){
            setSpeed(rand.nextInt(1));
            direction = new Point(-1, -1);
        }
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
