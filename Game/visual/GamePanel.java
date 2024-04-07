package visual;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import domain.barriers.Barrier;
import domain.levels.Level;
import domain.elements.FireBall;
import domain.elements.Staff;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class GamePanel extends JFrame{

    protected ImageIcon backgroundImage;
    protected JLabel backgroundLabel;
    protected String backgroundPath = "Game/Graphical-Assets/200Background.png";
    protected Level level;
    protected Staff staff;
    protected FireBall fireBall;
    protected KeyListener keyListener;
    protected volatile int direction = 0;
    
    public GamePanel() {
        setSize(1210, 680);
        setTitle("Game Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addBackgroundImage();
        initKeyListener();
        setVisible(true);
        setFocusable(true);
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
        staff = new Staff(new Point(getWidth() / 2, getHeight() - 100), new Dimension(120, 20));
        add(staff);
        staff.setVisible(true);
        initFireBall();
        addBackgroundImage();
        initKeyListener();
        initGameLoop();
        revalidate();
        repaint();
    }

    private void placeBarriers(Level level){
        int x = 10; 
        int y = 0;
        int maxHeightInRow = 0; 
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

    private boolean rowOverflow(int x, int barrierWidth){
        return (x + barrierWidth > this.getWidth());
    }

    private void initKeyListener(){
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    direction = -1;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    direction = 1;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    launchFireBall();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                direction = 0;
            }
        };
        addKeyListener(keyListener);
    }

    public void moveStaff() {
        int staffWidth = staff.getSize().width;

        if (direction == -1 && staff.getPosition().getX() > 0) {
            staff.moveLeft();
        } else if (direction == 1 && staff.getPosition().getX() < this.getWidth() - staffWidth) {
            staff.moveRight();
        }

        staff.repaint(); 
    }

    public int getDirection(){
        while (direction == 0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int temp = direction;
        direction = 0;
        return temp;
    }

    private void initGameLoop() {
        int delay = 10; // milliseconds
        new Timer(delay, e -> {
            moveStaff();
            moveFireBall();
            repaint();
        }).start();
    }

    private void moveFireBall() {
        if (fireBall != null) {
            fireBall.move();

            // Check collision with the staff
            Rectangle staffRect = new Rectangle(staff.getLocation(), staff.getSize());
            Rectangle fireBallRect = new Rectangle(fireBall.getX(), fireBall.getY(), fireBall.getRadius() * 2,
                    fireBall.getRadius() * 2);

            if (fireBallRect.intersects(staffRect)) {
                fireBall.setDy(-fireBall.getDy());
            }

            // Check collision with barriers
            for (Barrier barrier : level.barriers) {
                Rectangle barrierRect = new Rectangle(barrier.getLocation(), barrier.getSize());
                if (fireBallRect.intersects(barrierRect)) {
                    fireBall.setDy(-fireBall.getDy());
                }
                //todo : break the barrier.
            }

            fireBall.repaint();
        }
    }

    private void initFireBall() {
        Point staffPosition = staff.getLocation();
        int fireBallX = staffPosition.x - 30 + staff.getWidth() / 2;
        int fireBallY = staffPosition.y - 30+ staff.getHeight() / 2;
        fireBall = new FireBall(fireBallX, fireBallY, getWidth(), getHeight());
        add(fireBall);
        fireBall.setVisible(true); 
    }

    private void launchFireBall() {
        if (fireBall != null) {
            Point staffPosition = staff.getLocation();
            int fireBallX = staffPosition.x + staff.getWidth() / 2 - fireBall.getRadius();
            int fireBallY = staffPosition.y + staff.getHeight()/ 2 - fireBall.getRadius();
            fireBall.setLocation(fireBallX, fireBallY);
            fireBall.repaint();
        }
    }

}
