package com.example;

import com.example.domain.Game;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelInterface;
import com.example.domain.levels.LevelRandomizer;
import com.example.visual.GamePanel;

public class Main {

    public static void main(String[] args) {
        Level level = new Level("RandomLevel");
        LevelRandomizer.randomizeLevel(level, 75);
        Game gp = new Game(level);
        gp.run();
        
    }


}