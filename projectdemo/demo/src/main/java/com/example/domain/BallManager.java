package com.example.domain;

import java.awt.Point;
import java.io.Serializable;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.Staff;

public class BallManager implements Serializable {

    FireBall ball;
    
    public BallManager(FireBall b){
        ball = b;
    }
    public void placeBallAtStaff(Staff s){
        int staffCenterX = s.getX() + s.getWidth() / 2;
        int staffCenterY = s.getY() + s.getHeight() / 2;
        Point p = new Point(staffCenterX, staffCenterY + 5); //+5 so it doesn't get in the staff.
        resetBall(p);
    }

    public void resetBall(Point p){
        ball.setSpeed(0);
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
        ball.setDirection(new Point(-1, -1));
    }
    /**
     * Launches the FireBall at the mouse direction.
     */
    public void launchFireBall(Point p){
        ball.setDirection(p);
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
