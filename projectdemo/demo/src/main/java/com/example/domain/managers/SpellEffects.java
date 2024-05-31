package com.example.domain.managers;

import com.example.App;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Getter @Setter
public class SpellEffects implements KeyListener {

    public App app;
    private boolean FireBallPowerUp;
    private boolean cannonsActive;
    private boolean fireBallPowerUp;

    public SpellEffects() {
        fireBallPowerUp = false;
        cannonsActive = false;
        FireBallPowerUp = false;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_M:
                if (context.canUseSpell("MagicalStaffExpansion")) {
                    activateMagicalStaffExpansion();
                    context.useSpell("MagicalStaffExpansion");
                }
                break;
            case KeyEvent.VK_H:
                if (context.canUseSpell("Hex")) {
                    activateHex();
                    context.useSpell("Hex");
                }
                break;
            case KeyEvent.VK_O:
                if (context.canUseSpell("OverwhelmingFireBall")) {
                    activateOverwhelmingFireBall();
                    context.useSpell("OverwhelmingFireBall");
                }
                break;
        }
    }


    public void keyReleased(KeyEvent e) {

    }


    public void keyTyped(KeyEvent e) {

    }

    public void activateFelixFelicis() {
        this.app.getGame().getPlayer().setRemainingLives(this.app.getGame().getPlayer().getRemainingLives() + 1);
    }


    public void activateMagicalStaffExpansion() {
        App app = this.getApp();
        this.getApp().getGamePanel().setinitStaffWidth(170);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                app.getGamePanel().setinitStaffWidth(85);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
/*
    public void activateHex() {
        setCannonsActive(true);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                this.setCannonsActive(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
*/
    public void activateOverwhelmingFireBall() {
        FireBallPowerUp = true;
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FireBallPowerUp = false;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}



// For Game Environment
/*
private Map<String, Boolean> spells = new HashMap<>();

    	spells.put("MagicalStaffExpansion", false);
    	spells.put("Hex", false);
    	spells.put("OverwhelmingFireBall", false);
    }
public void acquireSpell(String spellName) {
    if (spells.containsKey(spellName)) {
        spells.put(spellName, true);
    }
}

public boolean canUseSpell(String spellName) {
    return spells.getOrDefault(spellName, false);
}

public void useSpell(String spellName) {
    if (canUseSpell(spellName)) {
        spells.put(spellName, false);
    }
}
*/
