package com.example.domain.levels;

import java.awt.Point;
import java.util.Collections;
import java.util.Scanner;

import com.example.domain.gameObject.barriers.ExplosiveBarrier;
import com.example.domain.gameObject.barriers.ReinforcedBarrier;
import com.example.domain.gameObject.barriers.SimpleBarrier;
import com.example.domain.gameObject.barriers.RewardingBarrier;

/**
 * LevelBuilder Class
 * Creates a level with pre-determined inputs or allows the User to build their custom level.
 */
public class LevelBuilder {
    
    private Integer difficulty, simpleCount, expCount, firmCount, rewCount;

    /**
     * Asks the user input to determine the operation.
     * 1,2,3 are inputs representing pre-determined levels with different difficulties.
     * 0 allows the user to build their custom level.
     * @return the created Level
     */
    public Level prompt(){
        Scanner sc = new Scanner(System.in);
        Level level = new Level("Single-Player Level");
        buildMode(level, sc);
        sc.close();
        return level;
    }

    /**
     * Creates a level based on the difficulty choice.
     * NOTE: It used to randomize the level based on difficulty but that's removed to ensure the proper barrier count.
     * @param level
     * @param difficulty
     */
    public void randomizeLevel(Level level, int difficulty){
        if (difficulty == 1){
            simpleCount = 60;
            rewCount = 10;
            expCount = 10;
            firmCount = 20;
        }
        if (difficulty == 2) {
            simpleCount = 45;
            rewCount = 5;
            expCount = 15;
            firmCount = 35;
        }
        if (difficulty == 3) {
            simpleCount = 35;
            rewCount = 5;
            expCount = 20;
            firmCount = 40;
        }
        addBarriers(level);
    }

    @Deprecated
    /**
     * Prompts the user to choose the difficulty of the level when appropriate.
     * @param s
     */
    private void askDifficulty(Scanner s){
        System.out.println("Please choose a difficulty:");
        System.out.println("0: Custom");
        System.out.println("1: Easy");
        System.out.println("2: Normal");
        System.out.println("3: Hard");
        
        int choice = s.nextInt();
        switch (choice) {
            case 0,1,2,3 -> difficulty = choice;
            default -> difficulty = 2;
        }
    }

    /**
     * Prompts the user to choose the count of each barrier type when appropriate.
     * If there are less than 100 barriers, simple barriers are added to the level to make up for missing barriers.
     * TODO : Probably should add limits for other counts too so it doesn't have more than 100.
     * @param s
     */
    private void askBarrierCounts(Scanner s){
        System.out.println("Please enter the number of Simple Barriers: ");
        simpleCount = s.nextInt();

        System.out.println("Please enter the number of Reinforced Barriers: ");
        firmCount = s.nextInt();

        System.out.println("Please enter the number of Explosive Barriers: ");
        expCount = s.nextInt();

        System.out.println("Please enter the number of Rewarding Barriers: ");
        rewCount = s.nextInt();

        System.out.println("Barrier counts have been received");
    }

    /**
     * Calls the buildmode operations. Just for readability.
     * @param l
     * @param s
     */
    private void buildMode(Level l, Scanner s){
        askBarrierCounts(s);
        addBarriers(l);
    }

    /**
     * Adds the barriers determined by the inputs to the level, then shuffles them
     * So they are placed randomly.
     * @param l
     */
    void addBarriers(Level l){
        for (int i = 0; i < simpleCount; i++) {
            l.addBarrier(new SimpleBarrier(new Point()));
        }

        for (int i = 0; i < firmCount; i++) {
            l.addBarrier(new ReinforcedBarrier(new Point()));
        }

        for (int i = 0; i < expCount; i++) {
            l.addBarrier(new ExplosiveBarrier(new Point()));
        }

        for (int i = 0; i < rewCount; i++) {
            l.addBarrier(new RewardingBarrier(new Point()));
        }
        Collections.shuffle(l.barriers);
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getSimpleCount() {
        return simpleCount;
    }

    public void setSimpleCount(Integer simpleCount) {
        this.simpleCount = simpleCount;
    }

    public Integer getExpCount() {
        return expCount;
    }

    public void setExpCount(Integer expCount) {
        this.expCount = expCount;
    }

    public Integer getFirmCount() {
        return firmCount;
    }

    public void setFirmCount(Integer firmCount) {
        this.firmCount = firmCount;
    }

    public Integer getRewCount() {
        return rewCount;
    }

    public void setRewCount(Integer rewCount) {
        this.rewCount = rewCount;
    }
}
