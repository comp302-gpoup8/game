package com.example.visual;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller logic for the GamePanel
 * TODO: Extend it for additional keyboard and mouse inputs!
 */
public class Controller {
    
    public volatile int direction = 0;
    public volatile int rotDirection = 0;
    public KeyListener keyListener;

    public Controller(){
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Required override, unused.
            }
            @Override
            public void keyPressed(KeyEvent e){
               int keyCode = e.getKeyCode();
               switch (keyCode){
                case KeyEvent.VK_LEFT -> direction = -1;
                case KeyEvent.VK_RIGHT -> direction = 1;
                case KeyEvent.VK_SPACE -> direction = 2;
                case KeyEvent.VK_P -> direction = 3;
                case KeyEvent.VK_X -> direction = 9;
                case KeyEvent.VK_A -> rotDirection = 1;
                case KeyEvent.VK_D -> rotDirection = -1;
               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                direction = 0;
            }
        };
    }

    public int getDirection(){
        int temp = direction;
        direction = 0;
        return temp;
    }

    public int getRotDirection(){
        int temp = rotDirection;
        rotDirection = 0;
        return temp;
    }
}
