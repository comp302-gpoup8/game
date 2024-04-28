package com.example.domain;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import java.util.ArrayList;
import java.util.List;



public class GameManager {
    private Game game;
    private List<GameObject> elements;
    private BallManager ballManager;
    private StaffManager staffManager;

    public GameManager(Game g, List<GameObject> e){
        game = g;
        elements = e;
        ballManager = new BallManager(g.getPanel().getBall());
        staffManager = new StaffManager(g.getPanel().getStaff());
    }

    public void launchBall(FireBall f){
        ballManager.launchFireBall(game.getPanel().getMousePosition());
    }

    public void moveBall(FireBall f){
        ballManager.moveFireBall();
    }

    public void moveStaff(Staff s, int x) {
        staffManager.moveStaff(x);
        if (ballManager.ball.getSpeed() == 0){
            reinitiateBall(ballManager.ball);
        }
    }
    
    public void updateElements() {
        for (GameObject obj : elements) {
            if (obj instanceof FireBall) {
                FireBall ball = (FireBall) obj;
                moveBall(ball);
                checkBounds(ball);
            }
        }
    }

    public void checkBounds(FireBall f) {
        boolean inGame = PhysicsManager.checkBounds(f, game.getPanel().getHeight(), game.getPanel().getWidth());
        if (!inGame){
            game.lostBall();
            reinitiateBall(f);
        }
    }

    private void reinitiateBall(FireBall f) {
        ballManager.placeBallAtStaff(game.getPanel().getStaff());
    }

    public void checkCollisions() {
        List<GameObject> toRemove = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                GameObject destroyedObject = CollisionHandler.checkCollision(elements.get(i), elements.get(j));
                if (destroyedObject != null && !toRemove.contains(destroyedObject)){
                    toRemove.add(destroyedObject);
                }
            }
        }
        elements.removeAll(toRemove);
        game.getPanel().refreshLevel();

    }

    public void bounce(FireBall f, GameObject e) {
        PhysicsManager.bounceFromObject(f, e);
    }
    

}
