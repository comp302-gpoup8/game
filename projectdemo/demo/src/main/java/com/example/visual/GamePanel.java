package com.example.visual;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;

import com.example.domain.barriers.Barrier;
import com.example.domain.elements.Staff;
import com.example.domain.levels.Level;

public class GamePanel extends JFrame{
    
    BackgroundLabel bLabel;
    Controller controller;
    Staff staff;

    public GamePanel(Level level){
        initGameFrame();
        controller = new Controller();
        addKeyListener(controller.keyListener);
        addLevel(level);
        displayGamePanel();
    }

    private void initGameFrame(){
        setSize(1204, 678);
        setTitle("Single Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


    }

    private void displayGamePanel(){
        revalidate();
        repaint();
        setVisible(true);
        setFocusable(true);
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
            x += barrier.getWidth() + 5; // To put barriers next to each other
            maxHeightInRow = Math.max(maxHeightInRow, barrier.getHeight()); 
        }
        level.barriers.forEach(e -> add(e));

    }

    private boolean rowOverflow(int x, int barrierWidth) {
        return (x + barrierWidth > this.getWidth());
    }

    public void addLevel(Level level){
        getContentPane().removeAll();

        Point staffPosition = new Point((getWidth() / 2) - 85, getHeight() - 100);
        Dimension staffSize = new Dimension(170, 32);
        staff = new Staff(staffPosition, staffSize);
        add(staff);
        bLabel = new BackgroundLabel();
        bLabel.setBounds(0, 0, 1204, 678);
        add(bLabel);
        placeBarriers(level);
    }



}
