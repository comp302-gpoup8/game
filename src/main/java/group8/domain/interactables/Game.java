package group8.domain.interactables;

import java.io.Serializable;

import group8.domain.engine.GameManager;
import group8.domain.managers.Level;
import group8.domain.managers.Player;
import group8.domain.objects.Fireball;
import group8.domain.objects.Staff;
import group8.visual.GamePanel;
import group8.visual.App;

import lombok.Getter;
import lombok.Setter;

import java.awt.Dimension;
import java.awt.Point;

@Getter @Setter
public class Game implements Serializable, Runnable {
    private App app;
    private Level level;
    private Player player;
    private Fireball ball;
    private Staff staff;
    private GamePanel panel;
    private GameManager manager;
    private final Dimension bounds  = new Dimension(1200,  680);
    
    public Game(Level lv, Player p){
        level = lv;
        player = p;
        staff = new Staff(new Point(100, 500), new Dimension(120,16));
        ball = new Fireball(new Point (100, 500), new Dimension(16, 16));
        // ball.reset(staff);

    }
    @Override
    public void run() {
        panel = app.getGamePanel();
        

    }

    public boolean playerHasLives(){
        return player.getRemainingLives() > 0;
    }

    public boolean ballInMotion(){
        return ball.getSpeed() > 0;
    }

    public void playAttempt(){
        int action = panel.getCont().getActionSignal();
        int direction = panel.getCont().getDirectionSignal();

        switch (action) {
            case 1 -> manager.launchBall();
            case 2 -> ball.setSpeed(0);
            default -> System.out.printf("Current Signal : %d\n", action);
        }
    }
}

