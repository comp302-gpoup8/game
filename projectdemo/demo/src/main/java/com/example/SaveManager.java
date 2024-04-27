package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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

    public Game saveGame(String filePath) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
            Object saveFile = in.readObject();
            in.close();
            if (saveFile instanceof Game) {
                return (Game) saveFile;
            } else {
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Save wasn't found or corrupt");
            return null;
        }
    }
}
