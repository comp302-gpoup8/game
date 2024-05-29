package group8.domain.interactables;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import lombok.Getter;
import lombok.Setter;

/**
 * The Controller class for game interactions.
 * It handles keyboard input and processes the corresponding signals.
 * @author Bedran Yilmaz Bakay
 */
@Getter @Setter
public class Controller {
    
    /**
     * The direction signal for movement.
     */
    private volatile int directionSignal = 0;
    /**
     * The action signal for special actions (pause, save, use spell etc.).
     */
    private volatile int actionSignal = 0;
    /**
     * The KeyListener for keyboard input.
     */
    private KeyListener keyListener;

    public Controller() {
        keyListener = new KeyListener() {
            @OverrideOnly
            public void keyTyped(KeyEvent e) {
                // Not used.
            }

            @Override
            public void keyPressed(KeyEvent e){
                processAction(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e){
                directionSignal = 0;
                actionSignal = 0;
            }
        };
    }

    public void processAction(int keyCode){
        switch(keyCode){
            // Movement signals
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> directionSignal = -1;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> directionSignal = 1;
            // Action signals
            case KeyEvent.VK_SPACE -> actionSignal = 1; // Launch
            case KeyEvent.VK_P -> actionSignal = 2; // Pause / Unpause
            case KeyEvent.VK_X -> actionSignal = 3; // Save and Exit (Slot 1)
            case KeyEvent.VK_C -> actionSignal = 4; // Save and Exit (Slot 2)
            case KeyEvent.VK_V -> actionSignal = 5; // Save and Exit (Slot 3)
            case KeyEvent.VK_E -> actionSignal = 6; // Use spell
        }
    }

    //Get methods below are manually handled because of the use of volatile variables.
    /**
     * Gets the direction signal, then resets it.
     * @return the direction signal
     */
    public int getDirectionSignal(){
        int temp = directionSignal;
        directionSignal = 0;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Gets the action signal, then resets it.
     * @return The action signal.
     */
    public int getActionSignal(){
        int temp = actionSignal;
        actionSignal = 0;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
