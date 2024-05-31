package group8.domain.interactables;

import java.io.Serializable;

import group8.domain.engine.GameManager;
import group8.domain.managers.Level;

import group8.visual.GamePanel;
import group8.visual.App;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Game implements Serializable, Runnable {
    private App app;
    private Level level;
    private GamePanel panel;
    private GameManager manager;
    
    public Game(Level lv){
        level = lv;
        manager = new GameManager(this);
    }


    public boolean playerHasLives(){
        return app.getPlayer().getRemainingLives() > 0;
    }



    public void playAttempt(){

    }

    @Override
    public void run() {
        while (true) {

            manager.moveStaff(1);
            manager.moveBallWithStaff();

            // Update the game state periodically
            updateGameState();

            // Sleep to allow other processes to run (adjust as necessary)
            try {
                Thread.sleep(30); // Adjust the sleep time for desired responsiveness
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateGameState() {
        System.out.printf("Current staff position: %d, %d\n", manager.getStaff().getLocation().x, manager.getStaff().getLocation().y);
        System.out.printf("Current visual position: %d, %d\n\n", app.getGamePanel().getStaff().getLabel().getLocation().x, app.getGamePanel().getStaff().getLabel().getLocation().y);
        app.getGamePanel().getPanel().revalidate();
        app.getGamePanel().getPanel().repaint();
    }
}

