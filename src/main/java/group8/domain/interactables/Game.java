package group8.domain.interactables;

import java.io.Serializable;

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
    private GamePanel gp;
    private final Dimension bounds  = new Dimension(1200,  680);
    
    public Game(Level lv, Player p){
        level = lv;
        player = p;
        staff = new Staff(new Point(100, 500), new Dimension(120,16));
        ball = new Fireball(new Point (100, 500), new Dimension(16, 16));
        ball.reset(staff);
    }
    @Override
    public void run() {
        gp = app.getGamePanel();
    }
}
