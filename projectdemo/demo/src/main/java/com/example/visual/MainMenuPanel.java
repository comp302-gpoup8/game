package com.example.visual;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.domain.Game;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;

public class MainMenuPanel extends JPanel {
    private JButton quickPlayButton;
    private JButton buildModeButton;
    private JButton loadGameButton;
    private Game game;

    public MainMenuPanel() {
        setLayout(new GridLayout(3, 1));

        loadGameButton = new JButton("Load Game");
        quickPlayButton = new JButton("Quick Play");
        buildModeButton = new JButton("Build Mode");

        quickPlayButton.addActionListener(e -> makeGameWithRandomDifficulty());
        buildModeButton.addActionListener(e -> makeCustomGame());
        loadGameButton.addActionListener(e -> loadGame());
        add(quickPlayButton);
        add(buildModeButton);
        add(loadGameButton);
    }

    private void makeGameWithRandomDifficulty() {
        int difficultyLevel = (int) (Math.random() * 3) + 1; // Generates a random number between 1 and 3
        makeGame(difficultyLevel);
    }

    public void makeGame(int difficultyLevel) {
        LevelBuilder builder = new LevelBuilder();
        Level level = new Level("CurrentLevel");
        builder.randomizeLevel(level, difficultyLevel);
        game = new Game("Single-Player Mode", level);
    }

    public void loadGame(){
        game = new Game("Single-player Mode");
    }

    public void makeCustomGame() {
        LevelBuilder builder = new LevelBuilder();
        Level level = builder.prompt();
        game = new Game("Single-Player Mode", level);
    }

    public void run() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public Game getGame() {
        return game;
    }
}
