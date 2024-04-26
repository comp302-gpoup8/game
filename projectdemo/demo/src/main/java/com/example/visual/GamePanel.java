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
        add(bLabel);

    }

    private void displayGamePanel(){
        revalidate();
        repaint();
        setVisible(true);
        setFocusable(true);
    }

    public void addLevel(Level level){
        getContentPane().removeAll();
        LevelInterface.placeBarriers(level);
        level.barriers.forEach(e -> add(e));
        level.barriers.forEach(e -> e.setVisible(true));
        Point staffPosition = new Point((getWidth() / 2) - 85, getHeight() - 100);
        Dimension staffSize = new Dimension(170, 32);
        staff = new Staff(staffPosition, staffSize);
        add(staff);
    }



}
