package com.example.domain.managers;

import com.example.domain.gameObject.barriers.Barrier;
import com.example.domain.gameObject.barriers.HollowBarrier;
import com.example.domain.levels.Level;
import com.example.visual.GamePanel;

import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

@Getter @Setter
public class Ymir {
    private static final int INTERVAL = 30000; // 30 seconds
    private static final int ABILITY_DURATION = 15000; // 15 seconds
    private static final int MAX_BARRIERS = 8;
    private final GamePanel gamePanel;
    private final Level level;
    private final Random random;
    private final List<String> lastTwoAbilities;

    public Ymir(GamePanel gamePanel, Level level) {
        this.gamePanel = gamePanel;
        this.level = level;
        this.random = new Random();
        this.lastTwoAbilities = new ArrayList<>();
        this.lastTwoAbilities.add("InfiniteVoid"); // Initial random abilities
        this.lastTwoAbilities.add("DoubleAccel");
        startAbilityTimer();
    }

    private void startAbilityTimer() {
        Timer timer = new Timer(INTERVAL, e -> {
            if (random.nextBoolean()) {
                activateRandomAbility();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    private void activateRandomAbility() {
        List<String> abilities = List.of("InfiniteVoid", "DoubleAccel", "HollowPurple");
        String ability;
        do {
            ability = abilities.get(random.nextInt(abilities.size()));
        } while (isRepeatedAbility(ability));

        lastTwoAbilities.remove(0);
        lastTwoAbilities.add(ability);

        switch (ability) {
            case "InfiniteVoid":
                activateInfiniteVoid();
                break;
            case "DoubleAccel":
                activateDoubleAccel();
                break;
            case "HollowPurple":
                activateHollowPurple();
                break;
        }
    }

    private boolean isRepeatedAbility(String ability) {
        return ability.equals(lastTwoAbilities.get(0)) && ability.equals(lastTwoAbilities.get(1));
    }

    private void activateInfiniteVoid() {
        List<Barrier> barriers = level.barriers;
        int freezeCount = Math.min(barriers.size(), MAX_BARRIERS);
        for (int i = 0; i < freezeCount; i++) {
            Barrier barrier = barriers.get(random.nextInt(barriers.size()));
            barrier.setFrozen(true);
        }

        Timer timer = new Timer(ABILITY_DURATION, e -> {
            for (Barrier barrier : barriers) {
                barrier.setFrozen(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void activateDoubleAccel() {
        gamePanel.getBall().setSpeed(gamePanel.getBall().getSpeed() / 2);

        Timer timer = new Timer(ABILITY_DURATION, e -> {
            gamePanel.getBall().setSpeed(gamePanel.getBall().getSpeed() * 2);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void activateHollowPurple() {
        List<Barrier> barriers = level.barriers;
        Color purple = new Color(128, 0, 128);
        for (int i = 0; i < MAX_BARRIERS; i++) {
            Barrier hollowPurpleBarrier = new HollowBarrier(new Point(random.nextInt(gamePanel.getWidth()), random.nextInt((gamePanel.getHeight()))));
            barriers.add(hollowPurpleBarrier);
            gamePanel.add(hollowPurpleBarrier);
        }
        gamePanel.repaint();
    }
}
