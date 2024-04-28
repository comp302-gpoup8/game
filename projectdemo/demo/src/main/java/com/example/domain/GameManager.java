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



public class GameManager extends BallMovementManager {
    private Game game;
    private List<GameObject> elements;

    public GameManager(Game g, List<GameObject> e){
        super(g.getPanel().getBall());
        game = g;
        elements = e;
    }

    public void launchBall(FireBall f){
        super.launchFireBall();
    }

    public void moveBall(FireBall f){
        super.moveFireBall();
    }

    public void moveStaff(Staff s, int x) {
        int staffX = s.getX() + (x * s.getSpeed());
        int screenWidth = 1200;
        int staffWidth = s.getSize().width;

        staffX = Math.max(0, Math.min(staffX, screenWidth - staffWidth));

        s.setLocation(new Point(staffX, s.getY()));
        s.getHitBox().setLocation(s.getLocation());
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
        Staff staff = game.getPanel().getStaff();
        int staffCenterX = staff.getX() + staff.getWidth() / 2;
        int staffCenterY = staff.getY() - f.getSize().height / 2; // Place ball just above the staff
        f.setLocation(new Point(staffCenterX, staffCenterY));
        f.setSpeed(0);
        f.setDirection(new Point(1, -1)); // Set initial direction upwards
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
