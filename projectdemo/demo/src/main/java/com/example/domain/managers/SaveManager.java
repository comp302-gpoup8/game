package com.example.domain.managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.domain.Game;

public class SaveManager {
    
    public Game loadSave(String filePath){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
            Object saveFile = in.readObject();
            in.close();
            if (saveFile instanceof Game){
                return (Game) saveFile;
            } else {
                return null;
            }
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Save wasn't found or corrupt");
            return null;
        }
    }

    public void saveGame(Game game, String filePath) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
            out.writeObject(game);
            out.close();
        } catch (IOException e) {
            System.out.println("Could not save progress.");
        }
    }
}
