package com.example.domain.managers;

import com.example.domain.Game;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import com.example.domain.interfaces.BallManager;
import com.example.domain.interfaces.CollisionHandler;
import com.example.domain.interfaces.PhysicsManager;
import com.example.domain.interfaces.StaffManager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;



public class GameManager implements BallManager, CollisionHandler, PhysicsManager, StaffManager {
    private Game game;
    private List<GameObject> elements;
    private FireBall ball;


    public GameManager(Game g, List<GameObject> e){
        game = g;
        elements = e;
        ball = game.getPanel().getBall();
    }

    public void launchBall(){
        BallManager.launchFireBall(ball, new Point(-1, -1));
    }

    public void moveBall(){
        updateElements();
        checkBounds();
        BallManager.moveFireBall(ball);
    }

    public void moveStaff(Staff s, int x) {
        StaffManager.moveStaff(s,x, game.getPanel().getWidth());
        moveBallBeforeLaunch();
        game.getPanel().refreshLevel();
    }

    private void moveBallBeforeLaunch(){
        if (ball. getSpeed() == 0){
            reinitiateBall(ball);
        }
    }
    
    public void updateElements() {
        PhysicsManager.checkBounds(ball, game.getPanel().getHeight(), game.getPanel().getWidth());
        game.getPanel().refreshLevel();

    }

    public void checkBounds() {
        boolean inGame = PhysicsManager.checkBounds(ball, game.getPanel().getHeight(), game.getPanel().getWidth());
        if (!inGame){
            game.lostBall();
            reinitiateBall(ball);
        }
    }

    private void reinitiateBall(FireBall f) {
        BallManager.placeBallAtStaff(ball, game.getPanel().getStaff());
    }

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
        elements.removeAll(toRemove);
        game.getPanel().refreshLevel();
    }

}
