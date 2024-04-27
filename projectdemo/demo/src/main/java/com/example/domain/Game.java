package com.example.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

// import javax.swing.JLabel;

// import com.example.SaveManager;
// import com.example.domain.elements.StaffMovement;
// import com.example.domain.levels.Level;
// import com.example.domain.levels.LevelInterface;
// import com.example.visual.Controller;
// import com.example.visual.GamePanel;

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
            panel = new GamePanel();
            player = new Player(pName);
            loadGameManager();
        }
    }
}


//     public void loadGameManager(){
//         ArrayList<JLabel> elements = new ArrayList<>();
//         elements.add(panel.getBall());
//         elements.add(panel.getStaff());
//         level.barriers.forEach(e -> elements.add(e));
//         gManager = new GameManager(this, elements);
//     }


//     public void run() {
//         while (continuePlaying()) {
//             panel.displayGamePanel();

//             gManager.updateElements(); 
//             gManager.checkCollisions();
//             if (panel.getBall().speed > 0) {
//                 gManager.move(panel.getBall());
//             }
//             int command = controller.getDirection();
//             switch (command) {
//                 case -1: 
//                     StaffMovement.moveLeft(panel.getStaff());
//                     break;
//                 case 1: 
//                     StaffMovement.moveRight(panel.getStaff());
//                     break;
//                 case 2: 
//                     gManager.launch(panel.getBall());
//                     break;
//                 case 9:
//                     saveAndExit();
//                 default:
//                     break;
//             }
//             panel.refreshLevel(); 
//             try {
//                 Thread.sleep(16);
//             } catch (InterruptedException e) {
//                 Thread.currentThread().interrupt();
//             }
//         }
//     }

//     private boolean continuePlaying(){
//         return remainingLives > 0 && !LevelInterface.cleared(level);
//     }

//     public boolean load(){
//         SaveManager sv = new SaveManager();
//         Game savedGame = sv.loadSave("projectdemo/demo/src/main/java/com/example/game.sr");
//         if (savedGame != null){
//             panel = savedGame.panel;
//             panel.resetController();
//             panel.getBall().speed = 0;
//             gManager = savedGame.gManager;
//             controller = panel.getController();
//             level = savedGame.level;
//             remainingLives = savedGame.remainingLives;
//             score = savedGame.score;
//             return true;
//         } else {
//             return false;
//         }
//     }

//     public void save(){
//         SaveManager sv = new SaveManager();
//         sv.saveGame(this, "projectdemo/demo/src/main/java/com/example/game.sr");
//     }

//     public void saveAndExit(){
//         save();
//         System.out.println("Saved! Exitting!");
//         System.exit(0);
//     }
// }
