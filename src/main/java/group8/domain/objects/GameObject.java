package group8.domain.objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class GameObject {
    private HitBox hitbox;
    private Integer speed;
    private Point direction;

    public GameObject (@NonNull Point location, @NonNull Dimension size){
        this(location, size, 0, new Point(-1,-1));
    }

    public GameObject(@NonNull Point location, @NonNull Dimension size, @NonNull Integer speed, @NonNull Point direction) {
        hitbox = new HitBox(location, size);
        this.speed = speed;
        this.direction = direction;
    }

    // Manually handled getters and setters.
    
    public Point getLocation(){
        return hitbox.getLocation();
    }

    public void setLocation(Point location){
        hitbox.setLocation(location);
    }

    public Dimension getSize(){
        return hitbox.getSize();
    }

    public boolean intersects(GameObject e){
        return this.hitbox.intersects(e.hitbox);
    }
}
