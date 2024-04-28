package com.example.domain.interfaces;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;


public interface CollisionHandler {

    public static GameObject checkCollision(GameObject p, GameObject q){
        if (intersects(p, q)){
            if (p instanceof FireBall){
                return collision((FireBall) p, q);
            } else if (q instanceof FireBall){
                return collision((FireBall) q, p);
            }
        }
        return null;
    }
    
    public static GameObject collision(FireBall ball, GameObject obj){
        if (obj instanceof Barrier && !((Barrier)obj).isDestroyed()){
            PhysicsManager.bounce(ball, obj);
            Barrier bar = (Barrier) obj;
            bar.reduceHp(1);
            if (bar.isDestroyed()){
                return obj;
            }
        } else if (obj instanceof Staff){
            PhysicsManager.bounce(ball, obj);
        }
        return null;
    }

    private static boolean intersects(GameObject p, GameObject q){
        return p.getHitBox().intersects(q.getHitBox());
    }
}
