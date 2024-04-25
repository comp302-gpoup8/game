package com.example.levels;

import java.awt.Point;
import java.awt.Dimension;
import java.util.Random;

import com.example.barriers.Barrier;
import com.example.barriers.ExplosiveBarrier;
import com.example.barriers.ReinforcedBarrier;
import com.example.barriers.RewardingBarrier;
import com.example.barriers.SimpleBarrier;

public interface LevelRandomizer {

    public static Dimension B_SIZE = new Dimension(51, 20);
    
    public static void randomizeLevel(Level level, int BarrierCount){
        Random random = new Random();
        int barrierType = random.nextInt(0, 20);
        for (int i = 0; i < BarrierCount; i++){
            barrierType = random.nextInt(0, 20);
            level.barriers.add(getRandomBarrier(i, barrierType));
        }
    }

    public static Barrier getRandomBarrier(int i, int barrierType){

        Barrier barrier = null;
        Point p = new Point (100 * i, 100 * i);
        
        switch (barrierType) {
            default -> barrier = new SimpleBarrier(p, B_SIZE);
            case 0,1,2,3 -> barrier = new ExplosiveBarrier(p, B_SIZE);
            case 4,5,6,7,8,9,10 -> barrier = new ReinforcedBarrier(p, B_SIZE);
            case 19 -> barrier = new RewardingBarrier(p, B_SIZE);
        }
        return barrier;
    }
}