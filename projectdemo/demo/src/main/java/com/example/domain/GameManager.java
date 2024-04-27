package com.example.domain;

import java.awt.Rectangle;
import java.util.List;
import javax.swing.JLabel;

import com.example.domain.barriers.Barrier;
import com.example.domain.barriers.BarrierITF;
import com.example.domain.elements.FireBall;
import com.example.domain.elements.Staff;

public class GameManager {
    private Game game;
    private List<JLabel> elements;
    
    public GameManager(Game g, List<JLabel> e){
        game = g;
        elements = e;
    }

    public void updateElements(){
        for (JLabel obj : elements){
            if (obj instanceof FireBall){
                FireBall ball = (FireBall) obj;
                move(ball);
                checkBounds(ball);
            }
        }
    }

    public void move(FireBall f){
        f.position.translate(f.direction.x * f.speed, f.direction.y * f.speed);
        f.setLocation(f.position);
        f.hitBox.setLocation(f.position);
    }

    public void checkBounds(FireBall f){
        int x = f.position.x, y = f.position.y, r = f.size.height / 2;

        if (x - r <= 0 || x + r >= 800){
            f.direction.x = -f.direction.x;
        }

        if (y - r <= 0 || y + r >= 1200){
            f.direction.y = -f.direction.y;
        }
    }

    public void checkCollisions(){
        for (int i = 0; i < elements.size(); i++){
            for (int j = i + 1; j < elements.size(); j++){
                JLabel e1 = elements.get(i);
                JLabel e2 = elements.get(j);
                Rectangle r1 = e1.getBounds();
                Rectangle r2 = e2.getBounds();

                if (r1.intersects(r2)){
                    if (e1 instanceof FireBall){
                        collision((FireBall) e1, e2);
                    } else if (e2 instanceof FireBall){
                        collision((FireBall) e2, e1);
                    }
                }
            }
        }
    }

    public void collision(FireBall f, JLabel e){
        if (e instanceof Barrier){
            Barrier bar = (Barrier) e;
            BarrierITF.reduceHp(bar);
            if (BarrierITF.isDestroyed(bar)){
                game.level.barriers.remove(bar);
                game.panel.refreshLevel();
            }
            bounce(f);
        } else if (e instanceof Staff){
            bounce(f);
        }
    }

    public void bounce(FireBall f){
        f.direction.x = -f.direction.x;
        f.direction.y = -f.direction.y;
    }
}
