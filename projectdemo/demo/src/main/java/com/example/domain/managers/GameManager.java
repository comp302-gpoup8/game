package com.example.domain.managers;

import com.example.domain.Game;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;
import com.example.domain.gameObject.barriers.ExplosiveBarrier;
import com.example.domain.gameObject.barriers.ReinforcedBarrier;
import com.example.domain.gameObject.barriers.RewardingBarrier;
import com.example.domain.gameObject.barriers.SimpleBarrier;
import com.example.domain.interfaces.BallManager;
import com.example.domain.interfaces.CollisionHandler;
import com.example.domain.interfaces.PhysicsManager;
import com.example.domain.interfaces.StaffManager;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GameManager class
 * Allows interaction with all the manager interfaces.
 * Contains the main game logic.
 * TODO: Used to be too long and now partitioned, so definitely needs to be cleaned up but let's not break anything today.
 */
public class GameManager implements BallManager, CollisionHandler, PhysicsManager, StaffManager, Serializable {
    private Game game;
    private List<GameObject> elements;
    private FireBall ball;


    public GameManager(Game g, List<GameObject> e){
        game = g;
        elements = e;
        ball = game.getPanel().getBall();
    }

    // public void setinitStaffWidth(int w) {
    //     this.staff.getHitbox().setWidth(w);
    // }
    /**
     * Allows the BallManager to launch the Ball.
     * TODO: The ball should be launched in the Mouse Direction. Right now when I try it, it results in a null pointer exception.
     */
    public void launchBall(){
        BallManager.launchFireBall(ball, new Point(-1, -1));
    }

    /**
     * Allows the Ballmanager to move the ball.
     * TODO: All panel updates contain random checkBounds and checkCollision calls
     * to prevent the ball from going through multiple barriers in a second.
     * TODO: We should figure out the appropriate placement.
     */
    public void moveBall(){
        updateElements();
        checkBounds();
        BallManager.moveFireBall(ball);
    }

    /**
     * Allows the StaffManager to move the Staff.
     * @param s
     * @param x
     */
    public void moveStaff(Staff s, int x) {
        StaffManager.moveStaff(s,x, game.getPanel().getWidth());
        moveBallBeforeLaunch();
        game.getPanel().refreshLevel();
    }

    /**
     * Allows the FireBall to move along with the staff before it is launched.
     */
    private void moveBallBeforeLaunch(){
        if (ball. getSpeed() == 0){
            reinitiateBall(ball);
        }
    }
    
    /**
     * Updates the panel GUI.
     * TODO: All panel updates contain random checkBounds and checkCollision calls to prevent the ball from going through multiple barriers in a second.
     * TODO: We should figure out the appropriate placement.
     */
    public void updateElements() {
        PhysicsManager.checkBounds(ball, game.getPanel().getHeight(), game.getPanel().getWidth());
        game.getPanel().refreshLevel();

    }

    /**
     * Calls the PhysicsManager to check if the FireBall is within appropraite bounds.
     * Reduces the player's life points when the ball is lost, and reinitates it.
     */
    public void checkBounds() {
        boolean inGame = PhysicsManager.checkBounds(ball, game.getPanel().getHeight(), game.getPanel().getWidth());
        if (!inGame){
            game.lostBall();
            reinitiateBall(ball);
        }
    }

    /**
     * Calls the BallManager to reset the Ball.
     * @param f
     */
    private void reinitiateBall(FireBall f) {
        BallManager.placeBallAtStaff(ball, game.getPanel().getStaff());
    }

    /**
     * Uses the CollisionHandler to check for collisions and removes destroyed objects as a result, when appropriate.
     */
    public void checkCollisions() {
        List<GameObject> toRemove = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                GameObject destroyedObject = CollisionHandler.checkCollision(elements.get(i), elements.get(j));
                if (destroyedObject != null){
                    toRemove.add(destroyedObject);
                }
            }
        }
        increaseScore(toRemove);
        elements.removeAll(toRemove);
        game.getPanel().refreshLevel();
    }

    /**
     * Adjusts the player's score when they destroy barriers.
     * @param toRemove
     */
    public void increaseScore(List<GameObject> toRemove){
        for (GameObject obj : toRemove){
            if (!(obj instanceof Barrier)){
                continue;
            }
            Barrier bar = (Barrier) obj;
            if (bar instanceof SimpleBarrier){
                game.getPlayer().score += 10;
            } else if (bar instanceof ReinforcedBarrier){
                game.getPlayer().score += 20;
            } else if (bar instanceof ExplosiveBarrier){
                game.getPlayer().score += 15;
            } else if (bar instanceof RewardingBarrier) {
                game.getPlayer().score += 15;
            }
        }
    }

}
