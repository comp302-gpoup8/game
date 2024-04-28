package com.example.domain;

import java.awt.Point;
import java.io.Serializable;

import com.example.domain.gameObject.FireBall;

public class BallMovementManager implements Serializable {

    FireBall ball;
    
    public BallMovementManager(FireBall b){
        ball = b;
    }
    /**
     * Launches the FireBall at the mouse direction.
     */
    public void launchFireBall(){
        ball.setDirection(ball.getMousePosition());
        ball.setSpeed(4);
        moveFireBall();
    }

    /**
     * Computes the next point that the ball will move towards.
     * @return
     */
    public Point nextPoint(){
        int x = ball.getX() + (ball.getDirection().x * ball.getSpeed());
        int y = ball.getY() + (ball.getDirection().y * ball.getSpeed());
        return new Point(x, y);
    }

    public void moveFireBall(){
        Point p = nextPoint();
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
    }
}
