package com.example.visual;


import javax.swing.*;

import com.example.domain.Game;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;

import java.awt.*;
import java.awt.event.*;

public class BuildModeMenu extends Menu {
    private JTextField[] barrierCounts = new JTextField[4];
    private JLabel totalBarrierCountLabel;
    private JButton launchGameButton;

    public BuildModeMenu(int width, int height) {
        super(width, height);
        buildPanel();
        setupComponents();

    }

    private void updateTotalCount() {
        int totalBarrierCount = 0;
        for (JTextField field : barrierCounts) {
            try {
                int value = Integer.parseInt(field.getText());
                if (value >= 1 && value <= 99) {
                    totalBarrierCount += value;
                } else {
                    field.setText("");
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                // If the number is not valid or not in range, ignore it
            }
        }
        totalBarrierCountLabel.setText("Total Barrier Count: " + totalBarrierCount);
        launchGameButton.setEnabled(totalBarrierCount >= 100);
        launchGameButton.addActionListener(e -> {
            Level level = new Level("level");
            LevelBuilder levelBuilder = new LevelBuilder();
            levelBuilder.setSimpleCount(Integer.parseInt(barrierCounts[0].getText()));
            levelBuilder.setRewCount(Integer.parseInt(barrierCounts[1].getText()));
            levelBuilder.setExpCount(Integer.parseInt(barrierCounts[2].getText()));
            levelBuilder.setFirmCount(Integer.parseInt(barrierCounts[3].getText()));
            levelBuilder.addBarriers(level);
            Game game = new Game("Custom",level);
            app.setGame(game);
            System.out.println("Success! Use quick game to play the level");
            app.showSinglePlayerMenu();
        });
    }

    public void buildPanel() {
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4)); // 4 columns

        for (int i = 0; i < barrierCounts.length; i++) {
            JPanel innerPanel = new JPanel(new BorderLayout());
            String[] IMG_PATHS = {
                    "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Bluegem.png",
                    "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Redgem.png",
                    "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Greengem.png",
                    "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Firm.png" };
            JLabel label = new JLabel(new ImageIcon(IMG_PATHS[i]));
            barrierCounts[i] = new JTextField(3);
            barrierCounts[i].setHorizontalAlignment(JTextField.CENTER);
            barrierCounts[i].addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    updateTotalCount();
                }
            });

            innerPanel.add(label, BorderLayout.NORTH);
            innerPanel.add(barrierCounts[i], BorderLayout.SOUTH);
            topPanel.add(innerPanel);
        }
        panel.add(topPanel, BorderLayout.NORTH);

    }

    public void setupComponents() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalBarrierCountLabel = new JLabel("Total Barrier Count: 0");
        launchGameButton = new JButton("Launch Game");
        launchGameButton.setEnabled(false);

        bottomPanel.add(totalBarrierCountLabel);
        bottomPanel.add(launchGameButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

    }

    // @Override
    // public void publish() {
    //     app.displayMenu(panel);
    // }
}
