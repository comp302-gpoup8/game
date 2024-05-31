package group8.domain.interactables;

import java.io.Serializable;

import group8.domain.engine.GameManager;
import group8.domain.managers.Level;
import group8.domain.managers.Player;
import group8.domain.objects.Barrier;
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
        ball.reset(staff);
    }
    @Override
    public void run() {
        panel = app.getGamePanel();
        /*
         * START
             * WHILE player has lives
                * IF ball is launched
                    * move ball
                    * check for collisions
                    * if collision (barrier, sides, staff or roof)
                        * bounce ball
                        * if collision was with barrier
                            * reduce barrier hitpoint
                            * if barrier hitpoint <= 0
                                * destroy barrier
                    * if collision (bottom)
                        * reduce player life.
                        * if player still has lives reset ball
                    * get direction input
                    * move staff (direction input)
                        * control staff boundaries.
                * Else
                    * get direction input
                    * move staff(direction input) with ball
         */
    }

    public boolean playerHasLives(){
        return player.getRemainingLives() > 0;
    }

    public boolean ballInMotion(){
        return ball.getSpeed() > 0;
    }

    public void gameLoop(){
        if (ballInMotion()) {
            boolean inGame = manager.moveBall();
            if (!inGame) {
                player.remainingLives--;
                //RESETBALL
            } else {
                for (Barrier barrier : level.getBarriers()){
                    if (barrier.getHitbox().intersects(ball.getHitbox())){
                        
                    }
                }
            }
        }
    }
}
