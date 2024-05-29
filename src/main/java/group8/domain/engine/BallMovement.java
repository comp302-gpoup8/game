package group8.domain.engine;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import org.jetbrains.annotations.NotNull;

import group8.domain.objects.Fireball;
import group8.domain.objects.GameObject;
import group8.domain.objects.Staff;

/**
 * The BallMovement interface
 * It is used to define the movement of the ball in relation to other objects.
 * 
 * @author Bedran Yilmaz Bakay
 */
public interface BallMovement{
    
    public static void placeBallOnStaff(@NotNull Fireball ball, Staff staff) {
        Point staffCenter = PointOperations.getCenterPoint(staff);
        ball.getHitbox().setLocation(staffCenter);
    }

    public static void launchBall(@NotNull Fireball ball, Point point) {
        ball.setDirection(point);
        moveBall(ball);
    }

    public static void moveBall(@NotNull Fireball ball) {
        Point location = ball.getHitbox().getLocation();
        location.translate(PointOperations.getDx(ball), PointOperations.getDy(ball));
    }

        public static boolean checkBounds(Fireball fireball, Dimension bounds) {
        Rectangle hitbox = fireball.getHitbox();

        int x = hitbox.x;
        int y = hitbox.y;
        int r = hitbox.width / 2;

        int height = bounds.height;
        int width = bounds.width;

        if (y + r >= height) return false;

        if (x - r <= 0 || x + r >= width){
            fireball.getDirection().x *= -1;
        }
        if (y - r <= 0){
            fireball.getDirection().y *= -1;
        }
        return true;
    }

    public static void bounce(Fireball ball, GameObject e){
        Point ballCenter = PointOperations.getCenterPoint(ball);
        Point objectCenter = PointOperations.getCenterPoint(e);

        int collisionPoint = ballCenter.x - objectCenter.x;
        ball.getDirection().x += 2 * collisionPoint / objectCenter.x;
        ball.getDirection().y *= -1;
        BallMovement.moveBall(ball);
    }
}