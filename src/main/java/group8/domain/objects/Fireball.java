package group8.domain.objects;

import java.awt.Dimension;
import java.awt.Point;

import lombok.NonNull;

public class Fireball extends GameObject {
    
    public Fireball(@NonNull Point location, @NonNull Dimension size) {
        super(location, size);
    }

    public Fireball(@NonNull Point location, @NonNull Dimension size, Integer speed, Point direction) {
        super(location, size, speed, direction);
    }
}

