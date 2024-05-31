package com.example;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;
import com.example.visual.MainMenuPanel;

import javax.swing.JFrame;

import com.example.domain.Game;

// public class Main {

//     public static void main(String[] args) {
//         MainMenuPanel mainMenu = new MainMenuPanel();
//         while (mainMenu.getGame() == null){
//              mainMenu.run();
//             try {
//                 Thread.sleep(32);
//             } catch (InterruptedException e){
//                 e.printStackTrace();
//             }
//         }
//         Game game = mainMenu.getGame();
//         mainMenu = null;
//         game.run();
//     }
// }

public class App {
    private JFrame mainFrame;
    private MainMenu mainMenu;
    private SinglePlayerMenu singlePlayerMenu;
    private BuildModeMenu buildModeMenu;
    private GamePanel gamePanel;
    private Player player;

    public App(){
        buildMainFrame();
        showMainMenu();
    }

    public void showMainMenu(){
        mainMenu = new MainMenu(1200, 680);
        mainMenu.setApp(this);
        displayMenu(mainMenu.getPanel());
    }

    public void showSinglePlayerMenu(){
        singlePlayerMenu = new BuildModeMenu(1200, 680);
        singlePlayerMenu.setApp(this);
        displayMenu(buildModeMenu.getPanel());
    }

    // public void showGamePanel(){
    //     if (game == null){
    //         game = new Game("Single-Player");
    //         game.setApp(this);
    //     }

    //     gamePanel = new GamePanel();
    //     gamePanel.setApp(this);
        
    // }
}