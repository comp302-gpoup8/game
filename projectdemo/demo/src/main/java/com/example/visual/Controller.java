package com.example.visual;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    
    public volatile int direction = 0;
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
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> direction = -1;
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> direction = 1;
                case KeyEvent.VK_SPACE -> direction = 2;
                case KeyEvent.VK_X -> direction = 9;
               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                direction = 0;
            }
        };
    }

    public int getDirection(){
        // while (direction == 0) {
        //     try {
        //         Thread.sleep(10);
        //     } catch (InterruptedException e){
        //         e.printStackTrace();
        //     }
        // }
        int temp = direction;
        direction = 0;
        return temp;
    }
}
