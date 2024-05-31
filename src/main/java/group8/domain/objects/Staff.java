package group8.domain.objects;

import java.awt.Dimension;
import java.awt.Point;

import lombok.NonNull;

public class Staff extends GameObject {

    private Integer rotation;

    public Staff(@NonNull Point location, @NonNull Dimension size) {
        super(location, size);
        rotation = 0;
    }

    public int getRotation(){
        return rotation;
    }

    public void rotate(int angle){
        this.getHitbox().rotate(angle);
        this.rotation += angle;
    }
}
