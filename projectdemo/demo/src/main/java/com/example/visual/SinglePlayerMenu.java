package com.example.visual;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.example.domain.Game;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;

import java.awt.GridLayout;

public class SinglePlayerMenu extends Menu {

    private JPanel panel;
    private JButton quickPlayButton;
    private JButton buildModeButton;
    private JButton loadGameButton;

    public SinglePlayerMenu(int width, int height) {
        super(width, height);
        buildPanel();
    }

    public void buildPanel() {
        panel = new JPanel();
        panel.setSize(size);
        panel.setName("Singleplayer Menu");
        panel.setLayout(new GridLayout(3, 1));
        setupComponents();

        panel.add(quickPlayButton);
        panel.add(buildModeButton);
        panel.add(loadGameButton);
    }

    public void setupComponents(){
        quickPlayButton = new JButton("Quick Play");
        buildModeButton = new JButton("Build Mode");
        loadGameButton = new JButton("Load Game");

        quickPlayButton.addActionListener(e->app.showGamePanel());
        buildModeButton.addActionListener(e->app.showBuildModeMenu());
        loadGameButton.addActionListener(e-> {
            app.setGame(new Game());
            System.out.println("Game Loaded. Use quick play to play the game.");
            app.showSinglePlayerMenu();
        });
    }


    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * @return the quickPlayButton
     */
    public JButton getQuickPlayButton() {
        return quickPlayButton;
    }

    /**
     * @param quickPlayButton the quickPlayButton to set
     */
    public void setQuickPlayButton(JButton quickPlayButton) {
        this.quickPlayButton = quickPlayButton;
    }

    /**
     * @return the buildModeButton
     */
    public JButton getBuildModeButton() {
        return buildModeButton;
    }

    /**
     * @param buildModeButton the buildModeButton to set
     */
    public void setBuildModeButton(JButton buildModeButton) {
        this.buildModeButton = buildModeButton;
    }

    /**
     * @return the loadGameButton
     */
    public JButton getLoadGameButton() {
        return loadGameButton;
    }

    /**
     * @param loadGameButton the loadGameButton to set
     */
    public void setLoadGameButton(JButton loadGameButton) {
        this.loadGameButton = loadGameButton;
    }

    
}