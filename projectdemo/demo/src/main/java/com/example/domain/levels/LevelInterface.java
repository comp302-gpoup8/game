package com.example.domain.levels;

import java.awt.Point;

import com.example.domain.barriers.Barrier;

public interface LevelInterface extends LevelRandomizer {
    public static void addBarrier(Level l, Barrier b){
        l.barriers.add(b);
    }

    public static void removeBarrier(Level l, Barrier b){
        l.barriers.remove(b);
    }

    public static void placeBarriers(Level level){
        Point p = new Point(10, 0);
        for (Barrier barrier : level.barriers){
            if (outOfRow(p.x, barrier.getWidth())){
                p = new Point(10, p.y + barrier.getHeight());
            }
            barrier.setLocation(p);
            p.x += 5;
        }
    }

    private static boolean outOfRow(int x, int w){
        return x + w > 1200;
    }

    public static boolean cleared(Level l){
        return l.barriers.isEmpty();
    }
}
