package com.example.domain;

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
    
    public static void bounceFromObject(FireBall ball, GameObject e){
        Point ballCenter = getObjectCenter(ball);
        Point objectCenter = getObjectCenter(e);
        Point normal = ComputationHelper.normal(ballCenter, objectCenter);
        Point reflection = ComputationHelper.reflectionCalculator(ball.getDirection(), normal);
        ball.setDirection(reflection);
    }

    public static void bounceFromStaff(FireBall ball, GameObject e) {
        Point ballCenter = getObjectCenter(ball);
        Point objectCenter = getObjectCenter(e);
        Point normal = ComputationHelper.normal(ballCenter, objectCenter);
        Point reflection = ComputationHelper.reflectionCalculator(ball.getDirection(), normal);
        if (reflection.getY() < 0){
            reflection.y *= -1;
        }
        ball.setDirection(reflection);
    }

    private static Point getObjectCenter(GameObject e){
        int centerX = e.getX() + e.getWidth() / 2;
        int centerY = e.getY() + e.getHeight() / 2;
        return new Point(centerX, centerY);
    }
}
