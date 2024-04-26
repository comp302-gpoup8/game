package com.example;

import com.example.domain.levels.Level;
import com.example.visual.GamePanel;

public class Main {
    public static void main(String[] args) {
        Level level = new Level("RandomLevel");
        GamePanel gp = new GamePanel(level);
        
    }
}