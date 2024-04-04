package domain;

import java.awt.Dimension;
import domain.barriers.Barrier;
import domain.barriers.SimpleBarrier;
import domain.levels.Level;

import java.awt.Point;

import visual.GamePanel;

public class Main {
    public static void main(String[] args) {
        GamePanel gp = new GamePanel();
        // Add 5 simple barriers to gp
        Level level = new Level("Level 1");
        gp.changeLevel(level.randomLevel());

    }
}
