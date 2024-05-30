package group8.domain.objects;

import java.awt.Dimension;
import java.awt.Point;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class Barrier extends GameObject {

    private Integer hitpoints;
    private Integer type; 

    public Barrier(@NonNull Integer type){
        this(new Point(0, 0), type);
    }

    public Barrier(Point location, Integer type) {
        super(location, new Dimension(50, 20));
        this.type = type;
        this.hitpoints = type == 3 ? 3 : 1;
    }

    public void reduceHp(Integer damage){
        hitpoints -= damage;
    }

    public void reduceHpToZero(){
        hitpoints = 0;
    }

    public boolean isDestroyed(){
        return hitpoints <= 0;
    }

}
