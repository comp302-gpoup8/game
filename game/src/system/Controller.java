package system;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

    private volatile int signal = -1;

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A){
            signal = 1;
        }
        else if (key == KeyEvent.VK_D){
            signal = 2;
        }
        else if (key == KeyEvent.VK_SPACE){
            signal = 3;
        }
        else if (key == KeyEvent.VK_ESCAPE){
            signal = 0;
        }
    }

    public int getSignal(){
        while (signal == -1){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        int temp = signal;
        signal = -1;
        return temp;  
    }
}
