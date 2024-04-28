package com.example.domain.interfaces;

import java.awt.Point;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.Staff;

/**
 * BallManager Interface
 * Responsible for the movement of the FireBall, and it's re-initiation when it's lost.
 */
public interface BallManager {

    /**
     * Places the FireBall at the appropriate location relative to the staff.
     * @param ball
     * @param s
     */
    public static void placeBallAtStaff(FireBall ball, Staff s){
        int staffCenterX = s.getX() + s.getWidth() / 2;
        int staffCenterY = s.getY() + s.getHeight() / 2;
        Point p = new Point(staffCenterX - 10, staffCenterY - 30); //Additional Values to prevent the ball from being placed awkwardly.
        resetBall(ball, p);
    }

    /**
     * Resets the values associated with the FireBall
     * Used for re-initating the ball when the possesion is lost.
     * @param ball
     * @param p
     */
    public static void resetBall(FireBall ball, Point p){
        ball.setSpeed(0);
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
        ball.setDirection(new Point(-1, -1));
    }

    /**
     * Used to launch the FireBall from the Staff at the beginning of each try.
     * @param ball
     * @param p
     */
    /**
     * Used to launch the FireBall from the Staff at the beginning of each try.
     * @param ball
     * @param p
     */
    public static void launchFireBall(FireBall ball, Point p){
        if (ball.getSpeed() == 0) {
            ball.setDirection(p);
            ball.setSpeed(4);
            moveFireBall(ball);
        }
    }

    /**
     * Used to calculate the next point that the FireBall will move towards while it is in motion.
     * @param ball
     * @return
     */
    public static Point nextPoint(FireBall ball){
        Point ballDirection = validateBallDirection(ball);
        int x = ball.getX() + (ballDirection.x * ball.getSpeed());
        int y = ball.getY() + (ballDirection.y * ball.getSpeed());
        return new Point(x, y);
    }

    /**
     * Used to deal with an issue which sometimes causes the direction of the FireBall to be null.
     * May no longer be necessary but let's keep it for now just in case.
     * @param ball
     * @return
     */
    public static Point validateBallDirection(FireBall ball){
        Point direction = ball.getDirection();
        if (direction == null){
            direction = new Point(-1, -1);
            ball.setDirection(direction);
        }
        return direction;
    }

    /**
     * Used to move the FireBall and it's hitbox to the next point.
     * @param ball
     */
    public static void moveFireBall(FireBall ball){
        Point p = nextPoint(ball);
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
    }
}
