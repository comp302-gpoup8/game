import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GameWindow extends JFrame {
    private final Dimension size = new Dimension(800, 600);
    private String backgroundImagePath = "Graphical-Assets/Background.png";
    private JLabel bgImage;
    private volatile int signal = -1;
    
    public GameWindow(String s, LevelPanel p){
        setTitle(s);
        setSize(size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initBackgroundImage();
        initAdapter();
        add(p);
        p.setVisible(true);

    }

    private void initBackgroundImage(){
        bgImage = new JLabel(new ImageIcon(backgroundImagePath));
        add(bgImage);
        bgImage.setVisible(true);
    }

    private void initAdapter(){
        KeyAdapter adapter = new KeyAdapter() {
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
        };
        this.addKeyListener(adapter);
        this.setFocusable(true);
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow g = new GameWindow("abd", new LevelPanel());
            g.setVisible(true);
    });
    }
}
