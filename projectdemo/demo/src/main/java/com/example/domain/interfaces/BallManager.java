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
     * @param ball - the FireBall to be placed.
     * @param s - the Staff relative to which the FireBall is placed.
     * REQUIRES: ball and s are not null.
     * MODIFIES: the location of the FireBall.
     * EFFECTS: places the FireBall at the calculated position relative to the Staff.
     */
    public static void placeBallAtStaff(FireBall ball, Staff s) {
        int staffCenterX = s.getX() + s.getWidth() / 2;
        int staffCenterY = s.getY() + s.getHeight() / 2;
        Point p = new Point(staffCenterX - 10, staffCenterY - 30); //Additional Values to prevent the ball from being placed awkwardly.
        resetBall(ball, p);
    }

    /**
     * Resets the values associated with the FireBall
     * Used for re-initiating the ball when the possession is lost.
     * @param ball - the FireBall to be reset.
     * @param p - the Point to reset the FireBall to.
     * REQUIRES: ball and p are not null.
     * MODIFIES: the location, speed, and direction of the FireBall.
     * EFFECTS: resets the FireBall to the given point, sets speed to 0, and direction to (-1, -1).
     */
    public static void resetBall(FireBall ball, Point p) {
        ball.setSpeed(0);
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
        ball.setDirection(new Point(-1, -1));
    }

    /**
     * Used to launch the FireBall from the Staff at the beginning of each try.
     * @param ball - the FireBall to be launched.
     * @param p - the direction to launch the FireBall in.
     * REQUIRES: ball and p are not null.
     * MODIFIES: the speed and direction of the FireBall.
     * EFFECTS: sets the FireBall's speed to 4 and moves it in the specified direction.
     */
    public static void launchFireBall(FireBall ball, Point p) {
        ball.setDirection(p);
        ball.setSpeed(4);
        moveFireBall(ball);
    }

    /**
     * Used to calculate the next point that the FireBall will move towards while it is in motion.
     * @param ball - the FireBall whose next point is to be calculated.
     * @return the next Point the FireBall will move to.
     * REQUIRES: ball is not null.
     * MODIFIES: nothing.
     * EFFECTS: returns the next point based on the FireBall's current position, direction, and speed.
     */
    public static Point nextPoint(FireBall ball) {
        Point ballDirection = validateBallDirection(ball);
        int x = ball.getX() + (ballDirection.x * ball.getSpeed());
        int y = ball.getY() + (ballDirection.y * ball.getSpeed());
        return new Point(x, y);
    }

    /**
     * Used to deal with an issue which sometimes causes the direction of the FireBall to be null.
     * @param ball - the FireBall whose direction is to be validated.
     * @return the validated direction of the FireBall.
     * REQUIRES: ball is not null.
     * MODIFIES: the direction of the FireBall if it is null.
     * EFFECTS: returns the current direction if not null, otherwise sets and returns (-1, -1).
     */
    public static Point validateBallDirection(FireBall ball) {
        Point direction = ball.getDirection();
        if (direction == null) {
            direction = new Point(-1, -1);
            ball.setDirection(direction);
        }
        return direction;
    }

    /**
     * Used to move the FireBall and its hitbox to the next point.
     * @param ball - the FireBall to be moved.
     * REQUIRES: ball is not null.
     * MODIFIES: the location of the FireBall and its hitbox.
     * EFFECTS: moves the FireBall to the next point.
     */
    public static void moveFireBall(FireBall ball) {
        Point p = nextPoint(ball);
        ball.setLocation(p);
        ball.getHitBox().setLocation(p);
    }
}