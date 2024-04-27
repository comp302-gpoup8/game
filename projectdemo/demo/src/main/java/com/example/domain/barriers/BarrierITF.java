package com.example.domain.barriers;

public interface BarrierITF {

    public static void reduceHp(Barrier barrier){
        barrier.hitpoints--;
    }

    public static boolean isDestroyed(Barrier barrier){
        try{
            return barrier.hitpoints <= 0;
        } catch (NullPointerException e){
            return true;
        }
    }
}
