package com.example.domain;

import java.io.Serializable;

import com.example.domain.elements.BallMovement;
import com.example.domain.elements.FireBall;
import com.example.domain.elements.StaffMovement;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelInterface;
import com.example.visual.Controller;
import com.example.visual.GamePanel;

public class Game implements Serializable {

    private static final long serialVersionIUD = 1L;
    public GamePanel panel;
    public Controller controller;
    public Level level;
    public FireBall fBall;
    public Integer remainingLives;
    public Integer score;

    public Game(Level lv){
        level = lv;
        panel = new GamePanel(level);
        remainingLives = 3;
        score = 0;
        controller = panel.getController();
    }

    public void run(){
        while (continuePlaying()){
            panel.displayGamePanel();
            while (controller.direction == 0){
                int command = controller.getDirection();
                switch (command) {
                    case -1 -> StaffMovement.moveLeft(panel.getStaff());
                    case 1 -> StaffMovement.moveRight(panel.getStaff());
                    case 2 -> initFireBall();
                }
            }
            panel.refreshLevel();
        }
    }

    private boolean continuePlaying(){
        return remainingLives > 0 && !LevelInterface.cleared(level);
    }

    public void initFireBall(){
        fBall = new FireBall(panel.getStaff().getLocation());
        panel.add(fBall);
        panel.refreshLevel();
        BallMovement.launch(fBall);
    }
}
