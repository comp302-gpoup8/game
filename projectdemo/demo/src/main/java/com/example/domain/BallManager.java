package com.example.domain;

import java.awt.Point;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.Staff;

public interface BallManager {

    public static void placeBallAtStaff(FireBall ball, Staff s){
        int staffCenterX = s.getX() + s.getWidth() / 2;
        int staffCenterY = s.getY() + s.getHeight() / 2;
        Point p = new Point(staffCenterX - 10, staffCenterY - 30); //+5 so it doesn't get in the staff.
        resetBall(ball, p);
    }

    public static void resetBall(FireBall ball, Point p){
        ball.setSpeed(0);
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
        ball.setDirection(new Point(-1, -1));
    }

    public static void launchFireBall(FireBall ball, Point p){
        ball.setDirection(p);
        ball.setSpeed(4);
        moveFireBall(ball);
    }

    public static Point nextPoint(FireBall ball){
        Point ballDirection = validateBallDirection(ball);
        int x = ball.getX() + (ballDirection.x * ball.getSpeed());
        int y = ball.getY() + (ballDirection.y * ball.getSpeed());
        return new Point(x, y);
    }

    public static Point validateBallDirection(FireBall ball){
        Point direction = ball.getDirection();
        if (direction == null){
            direction = new Point(-1, -1);
            ball.setDirection(direction);
        }
        return direction;
    }

    public static void moveFireBall(FireBall ball){
        Point p = nextPoint(ball);
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
    }
}
