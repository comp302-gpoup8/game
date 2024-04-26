package com.example.visual;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;

import com.example.domain.elements.Staff;
import com.example.domain.levels.Level;
import com.example.domain.levels.LevelInterface;

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
        setSize(1210, 680);
        setTitle("Single Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        bLabel = new BackgroundLabel();
        bLabel.setBounds(0, 0, getWidth(), getHeight());
        add(bLabel);
        bLabel.setVisible(true);

    }

    private void displayGamePanel(){
        staff.setVisible(true);
        // revalidate();
        // repaint();
        setVisible(true);
        setFocusable(true);
    }

    public void addLevel(Level level){
        // getContentPane().removeAll();
        LevelInterface.placeBarriers(level);
        level.barriers.forEach(e -> add(e));
        Point staffPosition = new Point(getWidth() / 2, getHeight() - 100);
        Dimension staffSize = new Dimension(120, 20);
        staff = new Staff(staffPosition, staffSize);
    }



}
