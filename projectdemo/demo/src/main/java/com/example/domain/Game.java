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
    public Level level;
    public FireBall fBall;
    public Integer remainingLives;
    public Integer score;

    public Game(Level lv){
        level = lv;
        panel = new GamePanel(level);
        remainingLives = 3;
        score = 0;
    }

    public void run(){
        Controller ct = panel.getController();
        createFireBall();
        while (continuePlaying()){
            panel.displayGamePanel();
            while (ct.direction == 0){
                int command = ct.getDirection();
                switch (command) {
                    case -1 -> StaffMovement.moveLeft(panel.getStaff());
                    case 1 -> StaffMovement.moveRight(panel.getStaff());
                    case 2 -> BallMovement.launch(fBall);
                }
            }
        }
    }

    private boolean continuePlaying(){
        return remainingLives > 0 && !LevelInterface.cleared(level);
    }

    private void createFireBall(){
        FireBall fBall = new FireBall(panel.getStaff().getLocation());
        panel.add(fBall);
    }
}
