package group8.domain.engine;

import group8.domain.objects.GameObject;
import group8.domain.objects.Fireball;
import group8.domain.objects.Barrier;

public interface CollisionListener {
    public static GameObject checkCollision(Fireball ball, GameObject obj) {
        if ((ball.getHitbox().intersects(obj.getHitbox()))) {
            return collision(ball, obj);
        }
        return null;
    }

    private static GameObject collision(Fireball ball, GameObject obj) {
        BallMovement.bounce(ball, obj);
        if (obj instanceof Barrier barrier) {
            barrier.reduceHp(1);
            if (barrier.isDestroyed()) {
                return obj;
            }
        }
        return null;
    }
}