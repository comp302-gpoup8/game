package domain.levels;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

import domain.barriers.Barrier;
import domain.barriers.ExplosiveBarrier;
import domain.barriers.ReinforcedBarrier;
import domain.barriers.RewardingBarrier;
import domain.barriers.SimpleBarrier;

public interface LvInterface {
    public static void addBarrier(Level level, Barrier barrier){
        level.barriers.add(barrier);
    }

    public static void removeBarrier(Level level, Barrier barrier){
        level.barriers.remove(barrier);
    }

    public static void randomizeLevel(Level level){
        Random r = new Random();
        // int numBarriers = r.nextInt(75, 99) + 1;
        int barrierType = r.nextInt(0, 4);
        for (int i = 0; i < 126; i++){
            switch (barrierType) {
                default -> addBarrier(level, new SimpleBarrier(new Point(100 * i, 100 * i), new Dimension(51, 20))); // %40 chance
                case 4,5,6,7,8,9,10 -> addBarrier(level, new ReinforcedBarrier(new Point(100 * i, 100 * i), new Dimension(51, 20))); // %30 chance
                case 0,1,2,3 -> addBarrier(level, new ExplosiveBarrier(new Point(100 * i, 100 * i), new Dimension(51, 20))); // %20 chance
                case 19 -> addBarrier(level, new RewardingBarrier(new Point(100 * i, 100 * i), new Dimension(51, 20))); // %5 chance

            }
            barrierType = r.nextInt(0, 20);
            
        }
    }
}
