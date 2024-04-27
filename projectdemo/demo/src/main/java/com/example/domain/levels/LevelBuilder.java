package com.example.domain.levels;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import com.example.domain.gameObject.barriers.ExplosiveBarrier;
import com.example.domain.gameObject.barriers.ReinforcedBarrier;
import com.example.domain.gameObject.barriers.SimpleBarrier;
import com.example.domain.gameObject.barriers.RewardingBarrier;

public class LevelBuilder {
    
    private Dimension BARRIER_SIZE = new Dimension(51, 20);
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
        Random random = new Random();
        if (difficulty == 1){
            simpleCount = random.nextInt(40, 45);
            rewCount = random.nextInt(8, 11);
            expCount = random.nextInt(15, 20);
            firmCount = random.nextInt(15, 20);
        }
        if (difficulty == 2) {
            simpleCount = random.nextInt(30, 35);
            rewCount = random.nextInt(5, 9);
            expCount = random.nextInt(25, 30);
            firmCount = random.nextInt(25, 35);
        }
        if (difficulty == 3) {
            simpleCount = random.nextInt(10, 20);
            rewCount = random.nextInt(3, 7);
            expCount = random.nextInt(30, 35);
            firmCount = random.nextInt(35, 40);
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
    }

    private void buildMode(Level l, Scanner s){
        askBarrierCounts(s);
        addBarriers(l);
    }

    private void addBarriers(Level l){
        for (int i = 0; i < simpleCount; i++) {
            l.addBarrier(new SimpleBarrier(new Point(), BARRIER_SIZE));
        }

        for (int i = 0; i < firmCount; i++) {
            l.addBarrier(new ReinforcedBarrier(new Point(), BARRIER_SIZE));
        }

        for (int i = 0; i < expCount; i++) {
            l.addBarrier(new ExplosiveBarrier(new Point(), BARRIER_SIZE));
        }

        for (int i = 0; i < rewCount; i++) {
            l.addBarrier(new RewardingBarrier(new Point(), BARRIER_SIZE));
        }
        Collections.shuffle(l.barriers);
    }
}
