package com.example;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;
import com.example.domain.managers.Player;
import com.example.visual.BuildModeMenu;
import com.example.visual.GamePanel;
import com.example.visual.LoginMenu;
import com.example.visual.MainMenu;
import com.example.visual.SinglePlayerMenu;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
    private LoginMenu loginMenu;

    private GamePanel gamePanel;
    private Game game;
    private Player player; 

    public App() {
        buildMainFrame();
        showLoginMenu();
    }

    public void buildMainFrame(){
        mainFrame = new JFrame("Game Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 680);
        mainFrame.setResizable(false);
    }

    public void displayMenu(JPanel menuPanel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(menuPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    public void showMainMenu(){
        mainMenu = new MainMenu(1200, 680);
        mainMenu.setApp(this);
        displayMenu(mainMenu.getPanel());
    }

    public void showSinglePlayerMenu() {
        singlePlayerMenu = new SinglePlayerMenu(1200, 680);
        singlePlayerMenu.setApp(this); 
        displayMenu(singlePlayerMenu.getPanel());
    }

    public void showBuildModeMenu(){
        buildModeMenu = new BuildModeMenu(1200, 680);
        buildModeMenu.setApp(this);
        displayMenu(buildModeMenu.panel);
    }

    public void showLoginMenu() {
        loginMenu = new LoginMenu(1200, 680);
        loginMenu.setApp(this);
        displayMenu(loginMenu.panel);
    }

    public void showGamePanel(){
        Level level = new Level("@");
        LevelBuilder levelBuilder = new LevelBuilder();
        levelBuilder.randomizeLevel(level, 2);
        game = new Game("single", level);
        gamePanel = game.getPanel();
        gamePanel.setApp(this);
        displayMenu(gamePanel);
        // gamePanel.requestFocus();
        // game.run();
        
    }
}