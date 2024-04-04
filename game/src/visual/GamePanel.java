package visual;

import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.barriers.Barrier;
import domain.levels.Level;

import javax.swing.ImageIcon;

public class GamePanel extends JFrame {

    protected ImageIcon backgroundImage;
    protected JLabel backgroundLabel;
    protected String backgroundPath = "Game/Graphical-Assets/200Background.png";
    protected Level level;

    public GamePanel() {
        setSize(1210, 680);
        setTitle("Game Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addBackgroundImage();
        setVisible(true);
    }

    public void addBackgroundImage() {
        backgroundImage = new ImageIcon(backgroundPath);
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
    }

    public void changeLevel(Level level) {
        this.level = level;

        getContentPane().removeAll();
        placeBarriers(level);
        addBackgroundImage();

        revalidate();
        repaint();
    }

    private void placeBarriers(Level level){
        int x = 10; 
        int y = 0;
        int maxHeightInRow = 0; w

        for (Barrier barrier : level.barriers) {
            if (rowOverflow(x, barrier.getWidth())) { 
                x = 10; 
                y += maxHeightInRow; 
                maxHeightInRow = 0; 
            }

            barrier.setLocation(x, y);
            add(barrier);

            x += barrier.getWidth() + 5; // To put barriers next to each other
            maxHeightInRow = Math.max(maxHeightInRow, barrier.getHeight()); 
        }
    }

    private boolean rowOverflow(int x, int barrierWidth) {
        return (x + barrierWidth > this.getWidth());
    }
}
