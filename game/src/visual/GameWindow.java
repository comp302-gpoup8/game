package visual;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import system.Controller;

public class GameWindow extends JFrame {
    private final Dimension size = new Dimension(800, 600);
    private String backgroundImagePath = "Graphical-Assets/Background.png";
    private JLabel bgImage;
    private Controller controller;
    
    public GameWindow(String s){
        setTitle(s);
        setSize(size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initBackgroundImage();
        initAdapter();
    }

    private void initBackgroundImage(){
        bgImage = new JLabel(new ImageIcon(backgroundImagePath));
        add(bgImage);
        bgImage.setVisible(true);
    }

    private void initAdapter(){
        controller = new Controller();
        addKeyListener(controller);
        setFocusable(true);
    }
}
