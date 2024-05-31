package group8.domain.interactables;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicInteger;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;

public class Controller {
    /** Signal that'll  be shared across multiple threads so we use Atomic Integer */
    private AtomicInteger signal = new AtomicInteger(0);
    private KeyListener keyListener;

    public Controller(){
        keyListener = new KeyListener() {
            @OverrideOnly
            public void keyTyped(KeyEvent e){}; // Not used.

            @Override
            public void keyPressed(KeyEvent e){
                /* Not going to add a separate function for clarity */
                /* Because updates here are pretty crucial so lets not add +steps */
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_A -> signal.set(-1);
                    case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> signal.set(1);
                    case KeyEvent.VK_SPACE -> signal.set(2);
                    case KeyEvent.VK_X -> signal.set(9);
                }
            }

            @Override
            public void keyReleased(KeyEvent e){
                // signal.set(0);
            }
        };
    }

    /* Manual getters because lombok might have caused issues (lazy). */
    public KeyListener getKeyListener(){
        return keyListener;
    }

    public int getSignal(){
        return signal.get();
    }
}
