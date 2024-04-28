package com.example;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;
import com.example.visual.LoginPanel;
import com.example.visual.MainMenuPanel;
import com.example.domain.Game;

public class Main {

    public static void main(String[] args) {
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
        LoginPanel mainMenu = new LoginPanel();
        mainMenu.run();
    }


}