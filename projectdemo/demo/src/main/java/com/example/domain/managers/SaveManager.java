package com.example.domain.managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.domain.Game;

/**
 * SaveManager Class
 * Writes the GameState into a .sr file to save it,
 * Also enables the GameState to be loaded from that save.
 * TODO: Appearently this only runs on my computer and everyone else gets an error.
 * TODO: So someone who is getting an error should try to figure it out I couldn't do anything.
 * TODO: Or we should come up with a different method altogether for the upcoming parts.
 */
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
