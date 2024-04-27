package com.example.domain.gameObject.barriers;

import java.awt.Dimension;
import java.awt.Point;

import com.example.domain.gameObject.GameObject;

public abstract class Barrier extends GameObject {

    protected Integer hitPoints;

    public Barrier(Point p, Dimension d, String imagePath, Integer hp){
        super(p, d, imagePath);
        hitPoints = hp;

    }

    public void reduceHp(int damage){
        hitPoints -= damage;
    }

    public boolean isDestroyed(){
        return hitPoints <= 0;
    }
}
