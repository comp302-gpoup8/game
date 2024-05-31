package group8.domain.engine;

import org.jetbrains.annotations.NotNull;

import group8.domain.interactables.Game;
import group8.domain.objects.Barrier;
import group8.domain.objects.Fireball;
import group8.domain.objects.GameObject;
import group8.domain.objects.Staff;

import java.awt.Dimension;
import java.awt.Point;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GameManager {
    
    private Game game;
    private Staff staff;
    private Fireball ball;
    
    private Boolean launched;

    private static final Integer SCREEN_WIDTH = 1200;
    private static final Integer SCREEN_HEIGHT = 680;
    private static final Dimension SCREEN_DIMENSIONS = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

    
    public GameManager (@NotNull Game g){
        this.game = g;
        this.staff = game.getStaff();
        this.ball = game.getBall();
        launched = false;
    }

    // /**
    //  * Launches the ball at the specific direction.
    //  * @param direction
    //  */
    // public void launch(Point direction){
    //     BallMovement.launchBall(ball, direction);
    // }

    // public boolean moveBall(){

    // }

    // // public void handleCollisions(){
    // //     if (ball.getHitbox().intersects(game.))
    // // }

    // public void moveStaff(int movementSignal){
    //     staff.getDirection().x = movementSignal;
    //     int staffX = staff.getLocation().x;
    //     int staffDX = movementSignal * staff.getSpeed();
    //     int nextX = Math.max(0, Math.min(staffX + staffDX, SCREEN_WIDTH - staff.getSize().width));
    //     staff.setLocation(new Point(nextX, staff.getLocation().y));
    //     if (!launched) BallMovement.placeBallOnStaff(ball, staff);
    // }

    // public void updateElements(){
    //     BallMovement.checkBounds(ball, SCREEN_DIMENSIONS);
    //     game.getPanel().refresh();
    // }

    public void moveBallWithStaff(){
        Point staffCenter = PointOperations.getCenterPoint(staff);
        ball.getHitbox().setLocation(staffCenter.x, staffCenter.y + 20);
    }

    public void launchBall(){
        /* If the speed of the ball is > 0 that means it's already in motion,
        and this method should not do anything. */
        if (ball.getSpeed() > 0){
            return;
        }

        ball.setSpeed(4);

        /*
         * There is a weird bug causing the initial ball direction to be null, so we use this if check here.
         * It is going to be extended to receive mouse direction anyway, TODO.
         */
        if (ball.getDirection() == null){
            ball.setDirection(new Point(-1, -1)); 
        }

        /* Now that the speed and direction is defined the ball should move. */
        moveBall();
    }

    public boolean moveBall(){

        Point center; // Center x,y coordinates of the ball.
        int radius; // Radius of the ball (can be altered with spells)
        int speed = ball.getSpeed(); // Speed of the ball.
        boolean directionChanged = false; // Flag that keeps track of direction changes.
        
        if (speed != 0){ 
            moveBallToNextPoint();

            center = PointOperations.getCenterPoint(ball);
            radius = ball.getSize().width;

            directionChanged = checkBoundaries(center, radius);
            if (!directionChanged){
                directionChanged = (ball.intersects(staff) ? bounce(center, staff) : bounceOfOffBarrier(center));
            }
        }
        return directionChanged;
    }

    private Barrier checkBarrierIntersections(){
        for (Barrier barrier : game.getLevel().getBarriers()){
            if (ball.intersects(barrier)){
                return barrier;
            }
        }
        return null;
    }

    private void moveBallToNextPoint(){
        int dx = PointOperations.getDx(ball);
        int dy = PointOperations.getDy(ball);
        ball.setLocation(new Point(dx, dy));
    }

    /**
     * Checks whether or not the fireball collides with the sides or the top of the game panel.
     * @return true if such a collision happened, and the ball direction is changed.
     */
    private boolean checkBoundaries(Point center, int radius){
        /* Ball hits the side corners */
        if (center.x - radius <= 0 || center.x + radius >= SCREEN_WIDTH){
            ball.getDirection().x *= -1;
            return true;
        }

        /* Ball hits the top of the screen */
        if (center.y - radius <= 0){
            ball.getDirection().y *= -1;
            return true;
        }
        return false;
    }

    /**
     * Checks whether or not the ball is still in possession, meaning that it did not reach the bottom of the screen.
     * @return true if the ball is in appropriate boundaries, false otherwise.
     */
    private boolean checkPossesion(Point center, int radius){
        return center.y + radius <= SCREEN_HEIGHT;
    }

    /**
     * When the hitbox of the ball comes into contact with that of another gameObject such as the staff or the barriers,
     * adjusts the direction of the fireball depending on how it bounces from the object.
     * @param center
     * @param e
     */
    private boolean bounce(Point center, GameObject e){
        Point objCenter = PointOperations.getCenterPoint(e);
        int collisionPoint = center.x - objCenter.x;
        ball.setDirection(new Point(ball.getDirection().x + 2 * collisionPoint / objCenter.x, ball.getDirection().y *= -1));
        return true;
    }

    private boolean bounceOfOffBarrier(Point center){
        Barrier barrier = checkBarrierIntersections();
        if (barrier == null){
            return false;
        }
        bounce(center, barrier);
        barrier.reduceHp(1);
        return true;
    }
}
