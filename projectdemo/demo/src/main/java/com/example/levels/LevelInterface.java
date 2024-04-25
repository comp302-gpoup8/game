package com.example.levels;

import com.example.barriers.Barrier;

public interface LevelInterface extends LevelRandomizer {
    public static void addBarrier(Level l, Barrier b){
        l.barriers.add(b);
    }

    public static void removeBarrier(Level l, Barrier b){
        l.barriers.remove(b);
    }

    public static boolean cleared(Level l){
        return l.barriers.isEmpty();
    }
}
