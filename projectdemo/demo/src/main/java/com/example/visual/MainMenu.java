package com.example.visual;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.App;
import com.example.domain.Game;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;

public class MainMenu extends Menu {
    private JPanel panel;
    private JButton singlePlayerButton;
    private JButton multiplayerButton;
    private App app;

    public MainMenu(int width, int height) {
        super(width, height);
        buildPanel();
    }

    public void buildPanel() {
        this.panel = new JPanel();
        panel.setSize(size);
        panel.setName("Main Menu");

        panel.setLayout(new GridLayout(4, 1));

        setupComponents();
        panel.add(singlePlayerButton);
        panel.add(multiplayerButton);
    }

    public void setupComponents() {
        singlePlayerButton = new JButton("Single Player");
        singlePlayerButton.addActionListener(e -> app.showSinglePlayerMenu());

        multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.addActionListener(e -> System.out.println("Didn't have time to implement this feature"));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JButton getSinglePlayerButton() {
        return singlePlayerButton;
    }

    public void setSinglePlayerButton(JButton singlePlayerButton) {
        this.singlePlayerButton = singlePlayerButton;
    }

    public JButton getMultiplayerButton() {
        return multiplayerButton;
    }

    public void setMultiplayerButton(JButton multiplayerButton) {
        this.multiplayerButton = multiplayerButton;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
    
}
