package com.example.domain.interfaces;

import java.awt.Point;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;

public interface PhysicsManager {

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
    
    public static void bounce(FireBall ball, GameObject e) {
        Point ballCenter = getObjectCenter(ball);
        Point staffCenter = getObjectCenter(e);

        int hitPoint = ballCenter.x - staffCenter.x;
        ball.getDirection().x += 2 * hitPoint / e.getWidth();
        ball.getDirection().y *= -1;
    }


    private static Point getObjectCenter(GameObject e){
        int centerX = e.getX() + e.getWidth() / 2;
        int centerY = e.getY() + e.getHeight() / 2;
        return new Point(centerX, centerY);
    }
}
