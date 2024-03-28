package visual;

import system.LevelRandomizer;
import elements.barriers.Barrier;
import elements.barriers.SimpleBarrier;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

// import Game.elements.barriers.*;
// import Game.elements.projectiles.FireBall;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelPanel extends GameWindow{

    public LevelRandomizer randomizer;

    public LevelPanel(String s) {
        super(s);
        Random random = new Random();
        randomizer = new LevelRandomizer(random.nextInt(60, 101));
        for (Barrier barrier : randomizer.barriers){
            this.add(barrier, barrier.getPoint());
            barrier.setVisible(true);
        }
        this.setVisible(true);
    }

}