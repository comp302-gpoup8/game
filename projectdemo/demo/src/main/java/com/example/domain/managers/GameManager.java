package com.example.domain.managers;

import com.example.domain.Game;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;
import com.example.domain.gameObject.barriers.ExplosiveBarrier;
import com.example.domain.gameObject.barriers.HollowBarrier;
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
import java.util.Random;

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
    private static final String[] SPELLS = {"MagicalStaffExpansion", "Hex", "OverwhelmingFireBall"};
    private Random random;

    public GameManager(Game g, List<GameObject> e) {
        game = g;
        elements = e;
        ball = game.getPanel().getBall();
        random = new Random();
    }

    public void launchBall() {
        BallManager.launchFireBall(ball, new Point(-1, -1));
    }

    public void moveBall() {
        updateElements();
        checkBounds();
        BallManager.moveFireBall(ball);
    }

    public void moveStaff(Staff s, int x) {
        StaffManager.moveStaff(s, x, game.getPanel().getWidth());
        moveBallBeforeLaunch();
        game.getPanel().refreshLevel();
    }

    private void moveBallBeforeLaunch() {
        if (ball.getSpeed() == 0) {
            reinitiateBall(ball);
        }
    }

    public void rotateStaff(Staff s, int x) {
        System.out.println(s.getRotation());
        double dw = StaffManager.rotateStaff(s, x);
        double srot = s.getRotation();
        double rot = 0;
        if (srot > 0 && dw > 0) {
            rot = Math.min(45, dw + srot);
        } else if (srot < 0 && dw < 0) {
            rot = Math.max(-45, srot + dw);
        } else {
            rot = srot + dw;
        }
        s.setRotation(rot);
    }

    public void updateElements() {
        PhysicsManager.checkBounds(ball, game.getPanel().getHeight(), game.getPanel().getWidth());
        game.getPanel().refreshLevel();
    }

    public void checkBounds() {
        boolean inGame = PhysicsManager.checkBounds(ball, game.getPanel().getHeight(), game.getPanel().getWidth());
        if (!inGame) {
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
                if (destroyedObject != null) {
                    toRemove.add(destroyedObject);
                }
            }
        }
        handleDestroyedBarriers(toRemove);
        elements.removeAll(toRemove);
        game.getPanel().refreshLevel();
    }

    private void handleDestroyedBarriers(List<GameObject> toRemove) {
        for (GameObject obj : toRemove) {
            if (obj instanceof Barrier) {
                Barrier barrier = (Barrier) obj;
                if (barrier instanceof RewardingBarrier) {
                    String randomSpell = SPELLS[random.nextInt(SPELLS.length)];
                    game.acquireSpell(randomSpell);
                    System.out.println("Player acquired spell: " + randomSpell);
                }
                increaseScore(barrier);
            }
        }
    }

    private void increaseScore(Barrier barrier) {
        if (barrier instanceof SimpleBarrier) {
            game.getPlayer().score += 10;
        } else if (barrier instanceof HollowBarrier) {
            game.getPlayer().score += 20;
        } else if (barrier instanceof ExplosiveBarrier) {
            game.getPlayer().score += 15;
        } else if (barrier instanceof RewardingBarrier) {
            game.getPlayer().score += 15;
        }
    }
}
