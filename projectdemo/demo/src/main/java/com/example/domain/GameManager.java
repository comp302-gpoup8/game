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
import java.io.Serializable;
import java.util.List;

import javax.swing.JLabel;


public class GameManager implements Serializable {
    private Game game;
    private List<GameObject> elements;

    public GameManager(Game g, List<GameObject> e){
        game = g;
        elements = e;
    }

    public void launchBall(FireBall f){
        f.setSpeed(4);
        moveBall(f);
    }

    public void moveBall(FireBall f){
        f.getHitBox().getLocation().translate(f.getDirection().x * f.getSpeed(), f.getDirection().y * f.getSpeed());
        f.setLocation(f.getHitBox().getLocation());
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

    public void checkBounds(FireBall f){
        int x = f.getHitBox().x;
        int y = f.getHitBox().y;
        int r = f.getSize().height / 2;

        if (x - r <= 0 || x + r >= 1200){
            f.getDirection().x *= (-1);
        }
        if (y - r <= 0 || y + r >= 680){
            f.getDirection().y *= (-1);
        }
    }

    public void checkCollisions(){
        for (int i = 0; i < elements.size(); i++){
            for (int j = i + 1; j < elements.size(); j++){
                GameObject o1 = elements.get(i);
                GameObject o2 = elements.get(j);

                if (o1.getHitBox().intersects(o2.getHitBox())){
                    if (o1 instanceof FireBall){
                        collision((FireBall) o1, o2);
                    } else if (o2 instanceof FireBall){
                        collision((FireBall) o2, o1);
                    }
                }
            }
        }
    }

    public void collision(FireBall f, GameObject e) {
        if (e instanceof Barrier) {
            bounce(f, e);
            Barrier bar = barrierParser((Barrier) e);
            if (bar.isDestroyed()) {
                elements.remove(bar);
                game.getPanel().refreshLevel();
            }
        } else if (e instanceof Staff) {
            bounce(f, e);
        }
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
