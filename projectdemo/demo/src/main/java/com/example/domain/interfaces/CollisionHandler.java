package com.example.domain.interfaces;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;

/**
 * Collision Handler Inteface
 * Used to handle the object collisions. Currently only supports the collisions between
 * Staff-Fireball and Barrier-FireBall
 */
public interface CollisionHandler {
    //TODO - Possibly due to a threading issue, the ball sometimes doesn't bounce after a collision
    //Even though the collision occurs correctly.
    //Last few tests have been fine but can't guarantee it's working completely fine.

    /**
     * Determines whether or not a collision occurs between two objects,
     * and handles it accordingly.
     * @param p : GameObject p
     * @param q : GameObject q
     * @return One of the Objects, or Null, depending on the case. 
     */
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
    
    /**
     * Handles the collision between the FireBall and the other game Objects.
     * Right now only supports the Barriers and the Staff.
     * @param ball
     * @param obj
     * @return One of the Objects, or Null, depending on the case. 
     */
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

    /**
     * Determines if two GameObjects intersect / collide.
     * Used to improve the readablitiy of the checkCollision method.
     * @param p : GameObject p
     * @param q : GameObject q
     * @return true if the objects intersect, false otherwise.
     */
    private static boolean intersects(GameObject p, GameObject q){
        if (p.getHitBox() == null || q.getHitBox() == null){
            return false;
        }
        return p.getHitBox().intersects(q.getHitBox());
    }
}
