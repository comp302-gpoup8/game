package com.example.domain.barriers;

public interface BarrierITF {

    public static void reduceHp(Barrier barrier){
        barrier.hitpoints--;
    }

    public static boolean isDestroyed(Barrier barrier){
        return barrier.hitpoints <= 0;
    }
}
