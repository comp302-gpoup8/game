package com.example.domain;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;
import com.example.domain.gameObject.barriers.ExplosiveBarrier;
import com.example.domain.gameObject.barriers.ReinforcedBarrier;
import com.example.domain.gameObject.barriers.RewardingBarrier;
import com.example.domain.gameObject.barriers.SimpleBarrier;

import java.awt.Point;
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
        int x = f.getHitBox().x;
        int y = f.getHitBox().y;
        int r = f.getSize().height / 2;
        int screenWidth = 1200;
        int screenHeight = 680;

        if (x - r <= 0 || x + r >= screenWidth) {
            f.getDirection().x *= (-1);
        }

        if (y - r <= 0) {
            f.getDirection().y *= (-1);
        } else if (y + r >= screenHeight) {
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
                GameObject o1 = elements.get(i);
                GameObject o2 = elements.get(j);

                if (o1.getHitBox().intersects(o2.getHitBox())) {
                    if (o1 instanceof FireBall) {
                        collision((FireBall) o1, o2, toRemove);
                    } else if (o2 instanceof FireBall) {
                        collision((FireBall) o2, o1, toRemove);
                    }
                }
            }
        }

        elements.removeAll(toRemove);
    }

    public void collision(FireBall f, GameObject e, List<GameObject> toRemove) {
        if (e instanceof Barrier) {
            bounce(f, e);
            Barrier bar = barrierParser((Barrier) e);
            bar.reduceHp(1);
            if (bar.isDestroyed()) {
                if (!toRemove.contains(bar)) {
                    toRemove.add(bar);
                }
            }
        } else if (e instanceof Staff) {
            bounce(f, e);
        }
        game.getPanel().refreshLevel();
    }

    public void bounce(FireBall f, GameObject e) {
        Point normal = new Point(0, (f.getHitBox().y < e.getY()) ? 1 : -1);
        int dx = f.getDirection().x;
        int dy = f.getDirection().y;

        int dotProduct = dx * normal.x + dy * normal.y;
        int dx2 = dx - 2 * dotProduct * normal.x;
        int dy2 = dy - 2 * dotProduct * normal.y;
        f.setDirection(new Point(dx2, dy2));
    }
    
    public Barrier barrierParser(Barrier e) {
        if (e instanceof SimpleBarrier) {
            return (SimpleBarrier) e;
        } else if (e instanceof RewardingBarrier) {
            return (RewardingBarrier) e;
        } else if (e instanceof ExplosiveBarrier) {
            return (ExplosiveBarrier) e;
        }
        return (ReinforcedBarrier) e;
    }
}
