package com.example.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.example.domain.elements.StaffMovement;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelInterface;
import com.example.visual.Controller;
import com.example.visual.GamePanel;

public class Game implements Serializable {
    @Serial
    private static final long serialVersionIUD = 1L;
    public GamePanel panel;
    public GameManager gManager;
    public Controller controller;
    public Level level;
    public Integer remainingLives;
    public Integer score;

    public Game(Level lv){
        level = lv;
        panel = new GamePanel(level);
        remainingLives = 3;
        score = 0;
        controller = panel.getController();
        loadGameManager();
    }

    public void loadGameManager(){
        ArrayList<JLabel> elements = new ArrayList<>();
        elements.add(panel.getBall());
        elements.add(panel.getStaff());
        level.barriers.forEach(e -> elements.add(e));
        gManager = new GameManager(this, elements);
    }

    public void run() {
        while (continuePlaying()) {
            panel.displayGamePanel();

            gManager.updateElements(); 
            gManager.checkCollisions();
            if (panel.getBall().speed > 0) {
                gManager.move(panel.getBall());
            }
            int command = controller.getDirection();
            switch (command) {
                case -1: 
                    StaffMovement.moveLeft(panel.getStaff());
                    break;
                case 1: 
                    StaffMovement.moveRight(panel.getStaff());
                    break;
                case 2: 
                    gManager.launch(panel.getBall());
                    break;
                default:
                    break;
            }
            panel.refreshLevel(); 
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private boolean continuePlaying(){
        return remainingLives > 0 && !LevelInterface.cleared(level);
    }
}
