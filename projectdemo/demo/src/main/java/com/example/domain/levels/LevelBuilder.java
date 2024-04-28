package com.example.domain.levels;

import java.awt.Point;
import java.util.Collections;
import java.util.Scanner;

import com.example.domain.gameObject.barriers.ExplosiveBarrier;
import com.example.domain.gameObject.barriers.ReinforcedBarrier;
import com.example.domain.gameObject.barriers.SimpleBarrier;
import com.example.domain.gameObject.barriers.RewardingBarrier;

public class LevelBuilder {
    
    private Integer difficulty, simpleCount, expCount, firmCount, rewCount;

    public Level prompt(){
        Scanner sc = new Scanner(System.in);
        Level level = new Level("Single-Player Level");
        askDifficulty(sc);
        switch (difficulty) {
            case 1,2,3 -> randomizeLevel(level, difficulty);
            case 0 -> buildMode(level, sc);
        }
        sc.close();
        return level;
    }

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

    private void askBarrierCounts(Scanner s){
        System.out.println("Please enter the number of Simple Barriers: ");
        simpleCount = s.nextInt();

        System.out.println("Please enter the number of Reinforced Barriers: ");
        firmCount = s.nextInt();

        System.out.println("Please enter the number of Explosive Barriers: ");
        expCount = s.nextInt();

        System.out.println("Please enter the number of Rewarding Barriers: ");
        rewCount = s.nextInt();

        int barrierCount = simpleCount + firmCount + expCount + rewCount;
        while (barrierCount < 100) {
         simpleCount++;   
        }
    }

    private void buildMode(Level l, Scanner s){
        askBarrierCounts(s);
        addBarriers(l);
    }

    private void addBarriers(Level l){
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
}
