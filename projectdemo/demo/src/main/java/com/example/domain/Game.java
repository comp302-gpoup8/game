package com.example.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.SaveManager;
import com.example.domain.gameObject.GameObject;
import com.example.domain.levels.Level;
import com.example.visual.GamePanel;



public class Game implements Serializable {
    @Serial
    private static final long serialVersionIUD = 1L;
    private Player player;
    private GamePanel panel;
    private GameManager gm;
    private Level level;

    public Game (String pName, Level lv){
        if (!load()){
            level = lv;
            panel = new GamePanel(lv);
            player = new Player(pName);
            loadGameManager();
        }
    }

    public void run() {
        while (continuePlaying()) {
            panel.displayGamePanel();

            gm.updateElements();
            gm.checkCollisions();
            if (panel.getBall().getSpeed() > 0) {
                gm.moveBall(panel.getBall());
            }
            int command = panel.getController().getDirection();
            switch (command) {
                case -1:
                    gm.moveStaff(panel.getStaff(), -1);
                    break;
                case 1:
                    gm.moveStaff(panel.getStaff(), 1);
                    break;
                case 2:
                    gm.launchBall(panel.getBall());
                    break;
                case 9:
                    saveAndExit();
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

    public void lostBall(){
        player.remainingLives -= 1;
    }

    public void loadGameManager(){
        ArrayList<GameObject> elements = new ArrayList<>();
        elements.add(panel.getBall());
        elements.add(panel.getStaff());
        level.barriers.forEach(e -> elements.add(e));
        gm = new GameManager(this, elements);
    }

    public boolean load() {
        SaveManager sv = new SaveManager();
        Game savedGame = sv.loadSave("projectdemo/demo/src/main/java/com/example/game.sr");
        if (savedGame != null) {
            panel = savedGame.panel;
            panel.resetController();
            panel.getBall().setSpeed(0);
            gm = savedGame.gm;
            level = savedGame.level;
            player = savedGame.player;
            return true;
        } else {
            return false;
        }
    }

    private boolean continuePlaying() {
        return player.remainingLives > 0 && !level.isCleared();
    }

    public GamePanel getPanel(){
        return panel;
    }

    public void save() {
        SaveManager sv = new SaveManager();
        sv.saveGame(this, "projectdemo/demo/src/main/java/com/example/game.sr");
    }

    public void saveAndExit() {
        // save();
        System.out.println("Saved! Exitting!");
        System.exit(0);
    }
}






// }
