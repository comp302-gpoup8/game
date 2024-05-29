package group8.domain.engine;

import group8.domain.objects.GameObject;
import java.awt.Point;
import java.awt.Rectangle;


public interface PointOperations {
    
    public static Point getCenterPoint(GameObject object) {
        Rectangle hitbox = object.getHitbox();
        int centerX = (int) hitbox.getCenterX();
        int centerY = (int) hitbox.getCenterY();
        return new Point(centerX, centerY);
    }

    public static int getDx(GameObject object) {
        return object.getDirection().x * object.getSpeed();
    }

    public static int getDy(GameObject object) {
        return object.getDirection().y * object.getSpeed();
    }

    public static Point normal(Point v, Point w) {
        if (Math.abs(v.x - w.x) >= Math.abs(v.y - w.y)) {
            return new Point(v.x < w.x ? -1 : 1, 0);
        }
        return new Point(0, v.y < w.y ? -1 : 1);
    }

    public static int dotProduct(Point p, Point q) {
        return p.x * q.x + p.y * q.y;
    }

    public static Point reflectedPoint(Point p, Point q) {
        int dotProduct = dotProduct(p, q);
        int x = p.x - 2 * dotProduct * q.x;
        int y = p.y - 2 * dotProduct * q.y;
        return new Point(x, y);
    }
}
