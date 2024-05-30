package group8.domain.engine;

import org.jetbrains.annotations.NotNull;

import group8.domain.interactables.Game;
import group8.domain.objects.Fireball;
import group8.domain.objects.Staff;

import java.awt.Point;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GameManager implements BallMovement, CollisionListener{
    
    private Game game;
    private Staff staff;
    private Fireball ball;
    
    private Boolean launched;

    private static final Integer SCREEN_WIDTH = 1200;
    private static final Integer SCREEN_HEIGHT = 680;
    
    public GameManager (@NotNull Game g){
        this.game = g;
        this.staff = game.getStaff();
        this.ball = game.getBall();
        launched = false;
    }

    /**
     * Launches the ball at the specific direction.
     * @param direction
     */
    public void launch(Point direction){
        BallMovement.launchBall(ball, direction);
    }

    public void moveBall(){

    }

    public void moveStaff(int movementSignal){
        staff.getDirection().x = movementSignal;
        int staffX = staff.getLocation().x;
        int staffDX = movementSignal * staff.getSpeed();

        int nextX = Math.max(0, Math.min(staffX + staffDX, SCREEN_WIDTH - staff.getSize().width));
        staff.setLocation(new Point(nextX, staff.getLocation().y));
        if (!launched) BallMovement.placeBallOnStaff(ball, staff);
    }


}
