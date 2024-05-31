package group8.domain.interactables;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    private int directionSignal = 0;
    private final Object signalLock = new Object();
    private KeyListener keyListener;

    public Controller() {
        keyListener = new KeyListener() {
            @OverrideOnly
            public void keyTyped(KeyEvent e) {
                // Not used.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                processAction(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                synchronized (signalLock) {
                    directionSignal = 0;
                }
            }
        };
    }

    private void processAction(int keyCode) {
        synchronized (signalLock) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT -> directionSignal = -1;
                case KeyEvent.VK_RIGHT -> directionSignal = 1;
                case KeyEvent.VK_SPACE -> directionSignal = 2;
            }
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public int getDirectionSignal() {
        synchronized (signalLock) {
            return directionSignal;
        }
    }
}
