package group8.domain.managers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import group8.domain.objects.Barrier;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Level implements Serializable {

    private List<Barrier> barriers;

    public Level(){
        this(2);
    }

    public Level(int difficulty){
        Builder builder = new Builder(difficulty);
        barriers = builder.buildLevel();
    }

    public Level(int[] barrierCounts){
        Builder builder = new Builder(barrierCounts);
        barriers = builder.buildLevel();
    }

    public void removeBarrier(Barrier barrier){
        barriers.remove(barrier);
    }

    public boolean isCleared(){
        return barriers.isEmpty();
    }

    private static class Builder {

        private int[] barrierCounts;

        public Builder(int difficulty) {
            generateBarrierCounts(difficulty);
        }

        public Builder (int[] barrierCounts) {
            this.barrierCounts = barrierCounts;
        }

        private void generateBarrierCounts(int difficulty) {
            int simpleCount = 0;
            int firmCount = 0;
            int explosiveCount = 0;
            int rewardingCount = 0;

            Random random = new Random();
            switch(difficulty) {
                case 1: // Easy
                    simpleCount = random.nextInt(50, 60) + 1;
                    firmCount = random.nextInt(30, 40) + 1;
                    explosiveCount = random.nextInt(10, 20) + 1;
                    rewardingCount = random.nextInt(5, 10) + 1;
                    break;
                case 2:
                    simpleCount = random.nextInt(40, 50) + 1;
                    firmCount = random.nextInt(30, 40) + 1;
                    explosiveCount = random.nextInt(10, 20) + 1;
                    rewardingCount = random.nextInt(5, 10) + 1;
                    break;
                default:
                    simpleCount = random.nextInt(20, 30) + 1;
                    firmCount = random.nextInt(35, 40) + 1;
                    explosiveCount = random.nextInt(20, 25) + 1;
                    rewardingCount = random.nextInt(1, 5) + 1;
                    break;
            }
            barrierCounts = new int[]{simpleCount, firmCount, explosiveCount, rewardingCount};
            checkAndFill(difficulty);
        }

        private void checkAndFill(int difficulty){
            int sum = barrierCount();
            while (sum < 114){
                switch (difficulty) {
                    case 1 -> barrierCounts[0]++;
                    case 2 -> barrierCounts[1]++;
                    default -> barrierCounts[3]++;
                }
                sum++;
            }
        }
        
        private int barrierCount(){
            return barrierCounts[0] + barrierCounts[1] + barrierCounts[2] + barrierCounts[3];
        }

        private List<Barrier> buildLevel(){
            List<Barrier> barriers = new ArrayList<>();
            
            for (int i = 0; i < 3; i++){
                while (barrierCounts[i] > 0){
                    barriers.add(new Barrier(i));
                    barrierCounts[i]--;
                }
            }

            Collections.shuffle(barriers);
            return barriers;
        }
    }
}
