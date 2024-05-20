package com.example.domain.levels;

import com.example.domain.gameObject.barriers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelBuilderTest {

    private LevelBuilder levelBuilder;
    private Level level;

    @BeforeEach
    public void setUp() {
        levelBuilder = new LevelBuilder();
        level = new Level("Test Level");
    }

    /**
     * REQUIRES: difficulty is Easy
     * MODIFIES: this.level
     * EFFECTS: Populates the level with barriers according to the given difficulty
     */
    @Test
    public void testRandomizeLevelEasy() {
        levelBuilder.randomizeLevel(level, 1);
        validateBarrierCounts(level, 60, 10, 10, 20);
    }

    /**
     * REQUIRES: difficulty is Normal
     * MODIFIES: this.level
     * EFFECTS: Populates the level with barriers according to the given difficulty
     */
    @Test
    public void testRandomizeLevelNormal() {
        levelBuilder.randomizeLevel(level, 2);
        validateBarrierCounts(level, 45, 5, 15, 35);
    }

    /**
     * REQUIRES: difficulty is Hard
     * MODIFIES: this.level
     * EFFECTS: Populates the level with barriers according to the given difficulty
     */
    @Test
    public void testRandomizeLevelHard() {
        levelBuilder.randomizeLevel(level, 3);
        validateBarrierCounts(level, 35, 5, 20, 40);
    }

    /**
     * REQUIRES: simpleCount, firmCount, expCount, and rewCount are non-negative
     * MODIFIES: this.level
     * EFFECTS: Populates the level with custom barrier counts as specified
     */
    @Test
    public void testCustomBarrierCounts() {
        levelBuilder.simpleCount = 30;
        levelBuilder.firmCount = 20;
        levelBuilder.expCount = 25;
        levelBuilder.rewCount = 25;
        levelBuilder.addBarriers(level);
        validateBarrierCounts(level, 30, 25, 25, 20);
    }

    /**
     * REQUIRES: simpleCount, firmCount, expCount, and rewCount sum to 100 or less
     * MODIFIES: this.level
     * EFFECTS: Populates the level with barriers ensuring total count is 100
     */

    @Test
    public void testCustomBarrierCountsLessThanHundred() {
        // Set custom barrier counts that sum to less than 100
        levelBuilder.simpleCount = 60;
        levelBuilder.firmCount = 20;
        levelBuilder.expCount = 10;
        levelBuilder.rewCount = 5;

        // Add barriers to the level
        levelBuilder.addBarriers(level);

        // Ensure that the number of barriers is exactly 100
        assertEquals(100, level.getBarriers().size());

        // Count the number of simple barriers
        long simpleBarrierCount = level.getBarriers().stream().filter(b -> b instanceof SimpleBarrier).count();

        // Ensure that the number of simple barriers is the difference between 100 and the total count of other barriers
        assertEquals(100 - (levelBuilder.firmCount + levelBuilder.expCount + levelBuilder.rewCount), simpleBarrierCount);
    }


    /**
     * REQUIRES: simpleCount, firmCount, expCount, and rewCount sum to 100 or less
     * MODIFIES: this.level
     * EFFECTS: Populates the level with barriers ensuring total count does not exceed 100
     */



    @Test
    public void testBarrierCountsAddUpToHundred() {
        levelBuilder.simpleCount = 60;
        levelBuilder.firmCount = 10;
        levelBuilder.expCount = 10;
        levelBuilder.rewCount = 10;
        levelBuilder.addBarriers(level);
        validateBarrierCounts(level, 60, 10, 10, 10);
    }

    private void validateBarrierCounts(Level level, int simpleCount, int rewCount, int expCount, int firmCount) {
        List<Barrier> barriers = level.getBarriers();
        assertEquals(simpleCount + rewCount + expCount + firmCount, barriers.size());

        long simpleBarrierCount = barriers.stream().filter(b -> b instanceof SimpleBarrier).count();
        long reinforcedBarrierCount = barriers.stream().filter(b -> b instanceof ReinforcedBarrier).count();
        long explosiveBarrierCount = barriers.stream().filter(b -> b instanceof ExplosiveBarrier).count();
        long rewardingBarrierCount = barriers.stream().filter(b -> b instanceof RewardingBarrier).count();

        assertEquals(simpleCount, simpleBarrierCount);
        assertEquals(rewCount, rewardingBarrierCount);
        assertEquals(expCount, explosiveBarrierCount);
        assertEquals(firmCount, reinforcedBarrierCount);
    }
}
