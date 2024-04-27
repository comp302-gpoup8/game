package com.example.domain.elements;

import java.awt.Point;

public interface BallMovement {

    public static void launch(FireBall f){
        f.direction = new Point(20, 20);
        move(f);
    }

    public static void move(FireBall f){
        addPoints(f.position, f.direction);
        checkBounds(f);
        // updatePosition(f);
    }

    public static Point addPoints(Point p, Point q){
        return new Point(p.x + q.x, p.y + q.y);
    }

    public static void checkBounds(FireBall f){
        int x = f.position.x, y = f.position.y, rad = f.size.height;
        if (x - rad <= 0 || x + rad >= 800) f.direction.x = -f.direction.x;
        if (y - rad <= 0 || y + rad >= 1200) f.direction.y = -f.direction.y;
    }

    // public static void updatePosition(FireBall f){
    //     f.setLocation(f.position.x - 11, f.position.y - 11);
    // }
}
