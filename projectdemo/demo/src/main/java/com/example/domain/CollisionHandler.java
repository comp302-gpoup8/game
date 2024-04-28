package com.example.domain;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;
import com.example.domain.gameObject.barriers.ExplosiveBarrier;
import com.example.domain.gameObject.barriers.ReinforcedBarrier;
import com.example.domain.gameObject.barriers.RewardingBarrier;
import com.example.domain.gameObject.barriers.SimpleBarrier;


public interface CollisionHandler {

    public static GameObject checkCollision(GameObject p, GameObject q){
        if (p.getHitBox().intersects(q.getHitBox())){
            if (p instanceof FireBall){
                return collision((FireBall) p, q);
            } else if (q instanceof FireBall){
                return collision((FireBall) q, p);
            }
        }
        return null;
    }
    
    public static GameObject collision(FireBall ball, GameObject obj){
        if (obj instanceof Barrier){
            PhysicsManager.bounceFromObject(ball, obj);
            Barrier bar = barrierParser((Barrier) obj);
            bar.reduceHp(1);
            if (bar.isDestroyed()){
                return obj;
            }
        } else if (obj instanceof Staff){
            PhysicsManager.bounceFromStaff(ball, obj);
        }
        return null;
    }

    public static Barrier barrierParser(Barrier e) {
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
