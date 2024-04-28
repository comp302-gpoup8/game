package com.example.visual;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.example.domain.Game;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;

public class MainMenuPanel extends JPanel {
    private JButton quickPlayButton;
    private JButton buildModeButton;
    private Game game;

    public MainMenuPanel() {
        setLayout(new GridLayout(2, 1)); 

        quickPlayButton = new JButton("Quick Play");
        buildModeButton = new JButton("Build Mode");

        quickPlayButton.addActionListener(e -> openQuickPlayOptions());
        add(quickPlayButton);
        add(buildModeButton);
    }

    public void openQuickPlayOptions() {
        JFrame optionsFrame = new JFrame("Select Difficulty");
        optionsFrame.setLayout(new GridLayout(3, 1)); 

        JButton easyButton = createDifficultyButton("Easy", 1);
        JButton mediumButton = createDifficultyButton("Medium", 2);
        JButton hardButton = createDifficultyButton("Hard", 3);

        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.add(easyButton);
        optionsFrame.add(mediumButton);
        optionsFrame.add(hardButton);

        optionsFrame.pack();
        optionsFrame.setVisible(true);
        optionsFrame.setLocationRelativeTo(null);
    }

    public JButton createDifficultyButton(String label, int difficultyLevel) {
        JButton button = new JButton(label);
        button.addActionListener(e -> makeGame(difficultyLevel));
        return button;
    }

    public void makeGame(int difficultyLevel) {

        LevelBuilder builder = new LevelBuilder();
        Level level = new Level("CurrentLevel");
        builder.randomizeLevel(level, difficultyLevel);
        game = new Game("Single-Player Mode", level);
    }


    public void run() {
        JFrame frame = new JFrame("Main Menu");
        // MainMenuPanel mainMenuPanel = new MainMenuPanel();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public Game getGame(){
        return game;
    }
}
