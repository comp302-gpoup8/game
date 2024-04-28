package com.example.visual;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;
import com.example.domain.interfaces.BallManager;
import com.example.domain.levels.Level;



public class GamePanel extends JFrame{
    private BackgroundLabel backgroundLabel;
    private transient Controller controller;
    private Staff staff;
    private FireBall ball;
    private Level cLevel;



    public GamePanel(Level level){
        initGameFrame();
        controller = new Controller();
        cLevel = level;
        addKeyListener(controller.keyListener);
        addLevel(level);
        displayGamePanel();
    }

    private void initGameFrame(){
        setSize(1200, 680);
        setTitle("Single Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    public void displayGamePanel(){
        revalidate();
        repaint();
        setVisible(true);
        setFocusable(true);
        setResizable(false);
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
            barrier.getHitBox().setLocation(x, y);
            x += barrier.getWidth() + 10; // To put barriers next to each other
            maxHeightInRow = Math.max(maxHeightInRow, barrier.getHeight() + 10); 
        }
        level.barriers.forEach(e -> add(e));

    }

    private boolean rowOverflow(int x, int barrierWidth) {
        return (x + barrierWidth > this.getWidth());
    }
    public void addLevel(Level level){
        getContentPane().removeAll();
 
        addFireBall();
        addStaff();
        BallManager.placeBallAtStaff(ball, staff);
        placeBarriers(level);
        addBackgroundImage();
    }

    public void addFireBall() {
        Point ballStart = new Point((getWidth() / 2) - 20, getHeight() - 130);
        Dimension ballSize = new Dimension(22, 22);
        ball = new FireBall(ballStart, ballSize);
        add(ball);
    }

    public void addStaff(){
        Point staffPosition = new Point((getWidth() / 2) - 85, getHeight() - 100);
        Dimension staffSize = new Dimension(85, 15);
        staff = new Staff(staffPosition, staffSize);
        add(staff);
    }

    public void addBackgroundImage(){
        backgroundLabel = new BackgroundLabel();
        backgroundLabel.setBounds(0, 0, 1204, 678);
        add(backgroundLabel);
    }


    public void refreshLevel() {
        for (Barrier bar : cLevel.barriers) {
            if (bar.isDestroyed()) {
                remove(bar);
            }
        }
        revalidate();
        repaint();
    }

    public Controller getController(){
        return controller;
    }

    public void resetController() {
        controller = new Controller();
        addKeyListener(controller.keyListener);
    }

    public Staff getStaff(){
        return staff;
    }

    public FireBall getBall() {
        return ball;
    }
}
