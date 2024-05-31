package com.example;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;

import javax.swing.JFrame;

import com.example.domain.Game;

public class Main {

    public static void main(String[] args) {
        // App app = new App();
        JFrame mainFrame = new JFrame("Game Application");
        Level level = new Level("@");
        LevelBuilder levelBuilder = new LevelBuilder();
        levelBuilder.randomizeLevel(level, 2);
        Game game = new Game("single", level);
        mainFrame.add(game.getPanel());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 680);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        game.run();

    }
}