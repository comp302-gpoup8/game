package com.example.domain.gameObject;

import java.awt.Dimension;
import java.awt.Point;

public class Staff extends GameObject {
    
    public Staff(Point p, Dimension d, String imagePath){
        super(p, d, imagePath);
        speed = 120;
    }
}
