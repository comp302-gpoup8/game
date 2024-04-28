package com.example.domain.gameObject.barriers;

import java.awt.Dimension;
import java.awt.Point;

import com.example.domain.gameObject.GameObject;

public abstract class Barrier extends GameObject {

    protected Integer hitPoints;
    protected static Dimension DEFAULT_BARRIER_SIZE = new Dimension(51, 15);

    public Barrier(Point p, String imagePath, Integer hp){
        super(p, DEFAULT_BARRIER_SIZE, imagePath);
        hitPoints = hp;

    }

    public void reduceHp(int damage){
        hitPoints -= damage;
    }

    public boolean isDestroyed(){
        return hitPoints <= 0;
    }

    public int getHitpoints(){
        return hitPoints;
    }
}
