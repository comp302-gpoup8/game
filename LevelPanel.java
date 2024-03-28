import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class LevelPanel extends JPanel {
    private ArrayList<Barrier> barriers;
    private FireBall fireBall;
    private int windowWidth = 800;
    private int windowHeight = 600;
    private final int barrierWidth = 50;
    private final int barrierHeight = 20; 

    public LevelPanel() {
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.barriers = new ArrayList<>();
        this.fireBall = new FireBall(new Point(100, 100), 20, 5, 0, 0);
        placeBarriers();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void placeBarriers() {
        Random rand = new Random();
        int minBarriers = 60, maxBarriers = 80;
        int barrierCount = rand.nextInt(maxBarriers - minBarriers + 1) + minBarriers;
        int barrierTypes = 4; // Simple, Reinforced, Explosive, Rewarding

        for (int i = 0; i < barrierCount; i++) {
            int type = i % barrierTypes;
            int x = (i % (windowWidth / barrierWidth)) * (barrierWidth + 10); // +10 for some spacing
            int y = (i / (windowWidth / barrierWidth)) * (barrierHeight + 10); // Adjust placement logic as needed

            Barrier barrier = null;
            switch (type) {
                case 0:
                    barrier = new SimpleBarrier(new Point(x, y));
                    break;
                case 1:
                    barrier = new ReinforcedBarrier(new Point(x, y));
                    break;
                case 2:
                    barrier = new ExplosiveBarrier(new Point(x, y));
                    break;
                case 3:
                    barrier = new RewardingBarrier(new Point(x, y));
                    break;
            }
            barriers.add(barrier);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Barrier barrier : barriers) {
            ImageIcon imageIcon = barrier.imageIcon;
            g2d.drawImage(imageIcon.getImage(), barrier.getPoint().x, barrier.getPoint().y, barrier.getSize().width,
                    barrier.getSize().height, this);
        }
    }

    public void updateGame() {
        fireBall.moveBall();
        repaint();
    }

}
