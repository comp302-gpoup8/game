package com.example;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;
import com.example.domain.Game;

public class Main {

    public static void main(String[] args) {
        LevelBuilder builder = new LevelBuilder();
        Level level = builder.prompt();
        Game game = new Game("Single-Player Mode", level);
        game.run();
    }


}