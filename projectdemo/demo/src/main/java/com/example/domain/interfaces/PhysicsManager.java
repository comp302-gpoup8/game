package com.example.domain.interfaces;

import java.awt.Point;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;

/**
 * Physics Manager Class
 * Handles the bounce operation of the fireball as well as makes sure that it stays within
 * the boundaries of the level.
 */
public interface PhysicsManager {


    /**
     * Returns true if the FireBall comes in contact with the boundries of the level and makes it bounce off of them.
     * Returns false if it reaches to the bottom of the level which causes the player to lose a life.
     * @param ball
     * @param screenHeight
     * @param screenWidth
     * @return
     */
    public static boolean checkBounds(FireBall ball, int screenHeight, int screenWidth){
        int x = ball.getX();
        int y = ball.getY();
        int r = ball.getSize().width / 2;

        if (y + r >= screenHeight){
            return false;
        } else {
        
            if (x - r <= 0 || x + r >= screenWidth) {
                ball.getDirection().x *= -1;
            }

            if (y - r <= 0) {
                ball.getDirection().y *= -1;
            }

            return true;
        }
    }
    /**
     * Bounces the FireBall off of an object, based on the point of contact.
     * @param ball
     * @param e
     */
    public static void bounce(FireBall ball, GameObject e) {
        Point ballCenter = getObjectCenter(ball);
        Point objectCenter = getObjectCenter(e);

        int hitPoint = ballCenter.x - objectCenter.x;
        ball.getDirection().x += 2 * hitPoint / e.getWidth();
        ball.getDirection().y *= -1;
        BallManager.moveFireBall(ball);
    }

    /**
     * Gets the center point of a gameObject.
     * Used for the computation of the Bounce Method.
     * @param e
     * @return
     */
    private static Point getObjectCenter(GameObject e){
        int centerX = e.getX() + e.getWidth() / 2;
        int centerY = e.getY() + e.getHeight() / 2;
        return new Point(centerX, centerY);
    }
}
