package group8.domain.objects;

import java.awt.Dimension;
import java.awt.Point;

import lombok.NonNull;

public class Staff extends GameObject {

    public Staff(@NonNull Point location, @NonNull Dimension size) {
        super(location, size);
    }
}
