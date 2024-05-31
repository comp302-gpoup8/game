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

    //TODO: BUILD MODE PANEL SHOULD SEND THE INPUTS TO THE APP AND THE APP SHOULD CREATE THE LEVEL FROM THAT
    //TODO: TEST SPELLS
    //TODO: IMPLEMENT AND TEST THE NEW HITBOX SYSTEM (TOYGAR)
    //TODO: IMPLEMENT THE SPELLS
    //TODO: ADD RANDOM CHANCE OF MOVING TO THE BARRIERS
    //TODO: EXPLOSIVE BARRIERS SHOULD CREATE JLABELS UPON DESTRUCTION THAT CAN HIT THE PLAYER
}