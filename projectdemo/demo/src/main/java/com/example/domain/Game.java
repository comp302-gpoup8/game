package com.example.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.GameObject;
import com.example.domain.levels.Level;
import com.example.domain.managers.GameManager;
import com.example.domain.managers.Player;
import com.example.domain.managers.SaveManager;
import com.example.visual.GamePanel;
import com.example.domain.managers.SpellEffects;
import lombok.Getter;
import lombok.Setter;


/**
 * Game Class
 * Contains the main game logic
 * Allows for the domain and the visual to interact with eachother.
 * Serializable so it can be saved and restored.
 */
@Getter @Setter
public class Game implements Serializable {
    @Serial
    private static final long serialVersionIUD = 1L;
    private Player player;
    private GamePanel panel;
    private GameManager gm;
    private Level level;




    public Game (String pName){
        load();

    }

    public Game (String pName, Level lv){
        level = lv;
        panel = new GamePanel(lv);
        player = new Player(pName);
        panel.player = player;
        loadGameManager();

    }

    private void initializeSpells() {
        spells.put("MagicalStaffExpansion", false);
        spells.put("Hex", false);
        spells.put("OverwhelmingFireBall", false);
    }

    public void acquireSpell(String spellName) {
        if (spells.containsKey(spellName)) {
            spells.put(spellName, true);
        }
    }

    public boolean canUseSpell(String spellName) {
        return spells.getOrDefault(spellName, false);
    }



    /**
     * Main game logic
     * TODO: The thread sleep here may be the cause of the multiple barrier breaking bug.
     * TODO: So it may need to be adjusted and I tried a lot of different things but I am out of my depth it seems.
     */
    public void run() {
        long lastTime = System.currentTimeMillis();
        final int TARGET_FPS = 30;
        final long OPTIMAL_TIME = 1000 / TARGET_FPS;

        while (continuePlaying()) {
            long now = System.currentTimeMillis();
            lastTime = now;

            panel.displayGamePanel();
            processInput();
            gm.checkCollisions();
            gm.updateElements();
            keepMotion();
            panel.refreshLevel();

            try {
                long sleep = (lastTime - System.currentTimeMillis() + OPTIMAL_TIME);
                if (sleep > 0) {
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        scoreDisplay();
        System.exit(0);
    }

    /**
     * Reads the input from the GamePanel to be used in the main game logic.
     * //TODO: Can be extended for spells and pause function.
     */
    private void processInput() {
        int command = panel.getController().getDirection();
        switch (command) {
            case -1, 1 -> gm.moveStaff(panel.getStaff(), command);
            case 2 -> gm.launchBall();
            case 3 -> pauseUnpause();
            case 9 -> saveAndExit();
        }

        int rotCommand = panel.getController().getRotDirection();
        gm.rotateStaff(panel.getStaff(), rotCommand);
    }

    public void pauseUnpause(){
        if (panel.getBall().getSpeed() > 0){
            panel.getBall().setSpeed(0);
        } else if (panel.getBall().getSpeed() == 0){
            panel.getBall().setSpeed(4);
        }
    }

    /**
     * Reduces the player's lives by one when the posession is lost.
     */
    public void lostBall(){
        player.remainingLives -= 1;
    }

    /**
     * Sets up the GameManager.
     */
    public void loadGameManager(){
        ArrayList<GameObject> elements = new ArrayList<>();
        elements.add(panel.getBall());
        elements.add(panel.getStaff());
        level.barriers.forEach(e -> elements.add(e));
        gm = new GameManager(this, elements);
    }

    /**
     * Loads the previously saved game when relaunched.
     * @return
     */
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

    /**
     * Determines if the level is cleared or not.
     * @return
     */
    private boolean continuePlaying() {
        return player.remainingLives > 0 && !level.isCleared();
    }

    /**
     * Keeps the ball in motion while it's speed is > 0
     */
    private void keepMotion(){

        FireBall ball = panel.getBall();
        if (ball.getSpeed() > 0){
            gm.moveBall();
        }
        panel.refreshLevel();
    }

    /**
     * Saves the game state.
     */
    public void save() {
        SaveManager sv = new SaveManager();
        sv.saveGame(this, "projectdemo/demo/src/main/java/com/example/game.sr");
    }

    /**
     * Saves the game state and exits the program.
     */
    public void saveAndExit() {
        //UNCOMMENT THE LINE BELOW TO TEST IT.
        //Works fine when uncommented. Use x to save.
        save();
        System.out.println("Saved! Exitting!");
        System.exit(0);
    }

    /**
     * Displays the score of the player upon game completion.
     * TODO: Should be way more detailed.
     */
    public void scoreDisplay(){
        System.out.printf("Final score : %d\n", player.score);
        if (player.remainingLives > 0) {
            System.out.println("You've won! Yay!");
        } else {
            System.out.println("Better luck next time!");
        }
    }


    //Get methods
    public GamePanel getPanel(){
        return panel;
    }

    public Player getPlayer(){
        return player;
    }
}
