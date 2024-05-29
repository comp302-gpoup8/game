package group8.domain.objects;

import java.awt.Dimension;
import java.awt.Point;

import group8.domain.engine.BallMovement;
import group8.domain.engine.CollisionListener;
import lombok.NonNull;

public class Fireball extends GameObject implements BallMovement, CollisionListener{
    
    public Fireball(@NonNull Point location, @NonNull Dimension size) {
        super(location, size);
    }

    public Fireball(@NonNull Point location, @NonNull Dimension size, Integer speed, Point direction) {
        super(location, size, speed, direction);
    }

    public void reset(Staff staff){
        BallMovement.placeBallOnStaff(this, staff);
    }

    public void launch(){
        BallMovement.launchBall(this, new Point(0, -1));
    }

    public void move(){
        BallMovement.moveBall(this);
    }

    public GameObject checkCollision(GameObject obj) {
        return CollisionListener.checkCollision(this, obj);
    }
}
