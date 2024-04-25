package com.example.visual;

import com.example.domain.barriers.*;
import com.example.domain.elements.*;
import com.example.domain.levels.*;
import javax.swing.JFrame;

public class GamePanel extends JFrame{
    
    Level level;
    FireBall fireBall;
    Staff staff;
    BackgroundLabel bLabel;
    Controller controller;

    public GamePanel(Level l, FireBall f, Staff s){
        level = l;
        fireBall = f;
        staff = s;
        bLabel = new BackgroundLabel();
        controller = new Controller();
    }
}
