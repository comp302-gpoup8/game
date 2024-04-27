package com.example.domain.elements;

import java.awt.Point;

public interface BallMovement {

    public static void launch(FireBall f, Point d){
        f.position.translate(-1 * f.speed, -1* f.speed);
        f.setLocation(f.position);
    }


    public static void checkBounds(FireBall f){
        int x = f.position.x, y = f.position.y, rad = f.size.height;
        if (x - rad <= 0 || x + rad >= 800) f.direction.x = -f.direction.x;
        if (y - rad <= 0 || y + rad >= 1200) f.direction.y = -f.direction.y;
    }

}
