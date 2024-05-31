package group8.domain.interactables;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import group8.domain.engine.GameManager;
import group8.domain.managers.Level;

import group8.visual.GamePanel;
import group8.visual.App;
import java.awt.Point;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Game implements Serializable, Runnable {
    private App app;
    private Level level;
    private GamePanel panel;
    private GameManager manager;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    
    public Game(Level lv){
        level = lv;
        manager = new GameManager(this);
    }


    public boolean playerHasLives(){
        return app.getPlayer().getRemainingLives() > 0;
    }



    public void playAttempt(){

    }

    //GAME
   @Override
    public void run() {
        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                int directionSignal = panel.getDirectionSignal();
                System.out.printf("Direction signal %d\n", directionSignal);
                if (directionSignal == 2) {
                    manager.launchBall();
                } else {
                    manager.moveStaff(directionSignal);
                }
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void updateGameState() {
        
        app.getGamePanel().getPanel().revalidate();
        app.getGamePanel().getPanel().repaint();
    }
}

