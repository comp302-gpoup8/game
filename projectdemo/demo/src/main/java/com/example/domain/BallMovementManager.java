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
        Point ballDirection = validateBallDirection();
        int x = ball.getX() + (ballDirection.x * ball.getSpeed());
        int y = ball.getY() + (ballDirection.y * ball.getSpeed());
        return new Point(x, y);
    }

    public Point validateBallDirection(){
        Point direction = ball.getDirection();
        if (direction == null){
            direction = new Point(-1, -1);
            ball.setDirection(direction);
        }
        return direction;
    }

    public void moveFireBall(){
        Point p = nextPoint();
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
    }
}
