package com.example;
import javax.swing.JFrame;

import com.example.visual.GamePanel;;

/**
 * App class
 * This class will manage all active threads and thus the whole game.
 * We are probably not going to have to use main but I won't delete it for now.
 * 
 * TODO: Ensure it's working as intended for single-player game
 * Later we'll extend it to support multiplayer.
 */
public class App implements Runnable{
    
    /**
     * The thread that is responsible for the game.
     */
    private Thread thread;
    /**
     * The Game GUI
     */
    private GamePanel panel;
    /**
     * App state - Running or otherwise
     */
    private boolean isRunning;
    /**
     * FPS -- Not finalized, we'll need to adjust it.
     */
    private final int frameRate = 60;
    /**
     * For updates. Again, we'll need to adjust it.
     * Suggested number I found is 1000 / framerate; Since it's int we floor it to 16.
     */
    private final int targetTime = 16; 

    private JFrame frame;

    public App(){
        panel = new GamePanel(null);
        frame = initializeAppFrame();
        start();
    }

    /**
     * Builds a JFrame for the app and adds the GamePanel to it.
     * We may have to change GamePanel to a JPanel or something, the fact that it is a frame,
     * might be the source of a lot of our issues.
     * @return
     */
    private JFrame initializeAppFrame(){
        JFrame frame = new JFrame("Lance of Destiny");
        frame.setDefaultCloseOperation(3); //Exit on close.
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    public synchronized void start(){
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!isRunning){
            return;
        }
        isRunning = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            System.out.println("Stop method caused an exception.");
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        long startTime, runningTime = 0l, waitTime = 0l;

        while (isRunning){
            startTime = System.nanoTime();
            panel.refreshLevel();
            updateTimes(startTime, runningTime, waitTime);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                System.out.println("Run method caused an exception");
                e.printStackTrace();
            }
        }
    }

    /**
     * This is for the refreshment rate of the game and GUI threads.
     * We may have to adjust the wait time but I don't know how yet.
     * @param startTime
     * @param runningTime
     * @param waitTime
     */
    private void updateTimes(long startTime, long runningTime, long waitTime){
        runningTime = System.nanoTime() - startTime;
        waitTime = targetTime - runningTime / 1000000;
        if (waitTime < 0){
            waitTime = 5;
        }
    }

    public JFrame getFrame(){
        return frame;
    }
}
