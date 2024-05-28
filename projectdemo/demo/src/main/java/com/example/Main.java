package com.example;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;
import com.example.visual.LoginPanel;
import com.example.visual.MainMenuPanel;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.domain.Game;
import com.example.visual.RegisterPanel;

public class Main {

    static boolean loginSuccessful; 
    static JFrame mainFrame;
    static JPanel activePanel;
    static App app;

    public static void main(String[] args) {
        
        launchApp();
        initLoginProcess();

        if (loginSuccessful){
            MainMenuPanel mainMenu = new MainMenuPanel();
            swapPanel(mainMenu);
            while (mainMenu.getGame() == null) {
                mainMenu.run();
            }
            Game game = mainMenu.getGame();
            mainMenu = null;
            game.run();
        }


    //    if (loginPanel.LoginSuccessful){

    //        MainMenuPanel mainMenu = new MainMenuPanel();
    //        while (mainMenu.getGame() == null){
    //            mainMenu.run();
    //            try {
    //                Thread.sleep(1000);
    //            } catch (InterruptedException e){
    //                e.printStackTrace();
    //            }
    //        }
    //        Game game = mainMenu.getGame();
    //        mainMenu = null;
    //        game.run();
    //    }
    }

    public static void launchApp(){
        app = new App();
        mainFrame = app.getFrame();
        loginSuccessful = false;
    }

    public static void initLoginPanel(){
        LoginPanel loginPanel = new LoginPanel();
        activePanel = loginPanel;
        mainFrame.add(loginPanel);
        loginPanel.run();
    }

    public static void getLoginState(){
        LoginPanel loginPanel = (LoginPanel) activePanel;
        loginSuccessful = loginPanel.LoginSuccessful;
    }

    public static void initLoginProcess(){
        initLoginPanel();
        getLoginState();
    }

    public static void swapPanel(JPanel nextPanel){
        boolean dismissed = dismissActivePanel();
        if (dismissed){
            placeNextPanel(nextPanel);
        } else {
            System.out.println("Swap function was called in an unintended way");
        }
    }

    private static boolean dismissActivePanel(){
        if (activePanel == null){
            return false;
        }

        mainFrame.remove(activePanel);
        activePanel = null;
        return true;
    }

    private static void placeNextPanel(JPanel nextPanel){
        mainFrame.add(nextPanel);
        if(nextPanel instanceof Runnable){
            ((Runnable) nextPanel).run();
        }
    }
       


}
