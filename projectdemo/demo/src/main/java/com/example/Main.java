package com.example;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.example.domain.Game;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }

}