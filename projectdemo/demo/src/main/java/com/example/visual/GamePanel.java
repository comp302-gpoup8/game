package com.example.visual;

import com.example.domain.Game;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.Staff;
import com.example.domain.gameObject.barriers.Barrier;
import com.example.domain.interfaces.BallManager;
import com.example.domain.levels.Level;
import com.example.domain.managers.Player;


import javax.swing.*;
import java.awt.*;


/**
 * GamePanel Class
 * The Panel for the Single Player Game
 */
public class GamePanel extends JFrame{
    private BackgroundLabel backgroundLabel;
    private transient Controller controller;
    private Staff staff;
    private FireBall ball;
    private Level cLevel;
    public Player player;
    private JLabel[] lifeIcons = new JLabel[3];



    public GamePanel(Level level){

        initGameFrame();
        controller = new Controller();
        cLevel = level;
        addKeyListener(controller.keyListener);
        addLevel(level);
        displayGamePanel();
    }

    /**
     * Initializes the main frame.
     */
    private void initGameFrame(){
        setSize(1200, 680);
        setTitle("Single Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    /**
     * Makes the panel and its components visible.
     */
    public void displayGamePanel(){
        revalidate();
        repaint();
        setVisible(true);
        setFocusable(true);
        setResizable(false);
    }

    /**
     * Places the barriers on the Game Panel.
     * @param level : The level that contains the Barriers.
     */
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

    /**
     * Helper method for placing the barriers to the game panel appropriately.
     * @param x
     * @param barrierWidth
     * @return
     */
    private boolean rowOverflow(int x, int barrierWidth) {
        return (x + barrierWidth > this.getWidth());
    }

    /**
     * Adds the level to the GamePanel and sets up the other required elements.
     * @param level
     */
    public void addLevel(Level level){
        getContentPane().removeAll();

        addFireBall();
        addStaff();
        BallManager.placeBallAtStaff(ball, staff);
        placeBarriers(level);
        initLivesDisplay();
        addBackgroundImage();
    }

    /**
     * Initializes the FireBall on the Panel.
     */
    public void addFireBall() {
        Point ballStart = new Point((getWidth() / 2) - 20, getHeight() - 130);
        Dimension ballSize = new Dimension(22, 22);
        ball = new FireBall(ballStart, ballSize);
        add(ball);
    }

    /**
     * Initializes the staff on the panel.
     */
    public void addStaff(){
        Point staffPosition = new Point((getWidth() / 2) - 85, getHeight() - 100);
        Dimension staffSize = new Dimension(85, 15);
        staff = new Staff(staffPosition, staffSize);
        add(staff);
    }
    /**
     * Initializes the heart images that represents players lives for the panel.
     */

    private void initLivesDisplay() {
        for (int i = 0; i < lifeIcons.length; i++) {
            lifeIcons[i] = new JLabel(new ImageIcon("projectdemo/demo/src/main/java/com/example/Graphical-Assets/Heart.png"));
            lifeIcons[i].setBounds(1100 + (i * 30), 610,28, 28);
            add(lifeIcons[i]);
        }
        updateLivesDisplay();
    }
    /**
     * Updates the panel and number of hearts due to the remaining lives of the player.
     */

    public void updateLivesDisplay() {
        for (int i = 0; i < lifeIcons.length; i++) {
            if (i < getRemainingLives()) {
                lifeIcons[i].setVisible(true);
            } else {
                lifeIcons[i].setVisible(false);
            }
        }
    }

    /**
     * Gets the number of lives a player has in order to use it in methods that display hearts.
     */

    public int getRemainingLives(){
        if (player == null){
            player = new Player("New Player");
        }
        return player.remainingLives;
    }



    private void initLivesDisplay() {
        for (int i = 0; i < lifeIcons.length; i++) {
            lifeIcons[i] = new JLabel(new ImageIcon("projectdemo/demo/src/main/java/com/example/Graphical-Assets/Heart.png"));
            lifeIcons[i].setBounds(1100 + (i * 30), 610,28, 28);
            add(lifeIcons[i]);
        }
        updateLivesDisplay();
    }

    public void updateLivesDisplay() {
        for (int i = 0; i < lifeIcons.length; i++) {
            if (i < getRemainingLives()) {
                lifeIcons[i].setVisible(true);
            } else {
                lifeIcons[i].setVisible(false);
            }
        }
    }

    public int getRemainingLives(){
        if (player == null){
            player = new Player("New Player");
        }
        return player.remainingLives;
    }



    /**
     * Initializes the Background image for the panel.
     */
    public void addBackgroundImage(){
        backgroundLabel = new BackgroundLabel();
        backgroundLabel.setBounds(0, 0, 1204, 678);
        add(backgroundLabel);
    }



    /**
     * Refreshes the panel to remove the barriers that are destroyed.
     */
    public void refreshLevel() {
        for (Barrier bar : cLevel.barriers) {
            if (bar.isDestroyed()) {
                remove(bar);
            }
        }
        updateLivesDisplay();
        revalidate();
        repaint();
    }

    /**
     * Resets the controller
     * Necessary for relaunch.
     */
    public void resetController() {
        controller = new Controller();
        addKeyListener(controller.keyListener);
    }

    // Get Methods
    public Controller getController(){
        return controller;
    }

    public Staff getStaff(){
        return staff;
    }

    public FireBall getBall() {
        return ball;
    }
}