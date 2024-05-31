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

    private App app;
    private boolean fireBallPowerUp;
    private boolean cannonsActive;
    private String equippedSpell; // To track the equipped spell

    public SpellEffects(App app) {
        this.app = app;
        this.fireBallPowerUp = false;
        this.cannonsActive = false;
        this.equippedSpell = null;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_M:
                equipSpell("MagicalStaffExpansion");
                break;
            case KeyEvent.VK_H:
                equipSpell("Hex");
                break;
            case KeyEvent.VK_O:
                equipSpell("OverwhelmingFireBall");
                break;
            case KeyEvent.VK_E:
                activateEquippedSpell();
                break;
        }
    }


    private void equipSpell(String spellName) {
        if (app.getGame().canUseSpell(spellName)) {
            equippedSpell = spellName;
        }
    }

    private void activateEquippedSpell() {
        if (equippedSpell == null) {
            return; // No spell equipped
        }
        switch (equippedSpell) {
            case "MagicalStaffExpansion":
                activateMagicalStaffExpansion();
                break;
            case "Hex":
                activateHex();
                break;
            case "OverwhelmingFireBall":
                activateOverwhelmingFireBall();
                break;
        }
        app.getGame().useSpell(equippedSpell);
        equippedSpell = null; // Reset equipped spell after use
    }

    public void activateFelixFelicis() {
        app.getGame().getPlayer().setRemainingLives(app.getGame().getPlayer().getRemainingLives() + 1);
    }

    public void activateMagicalStaffExpansion() {
        app.getGamePanel().setinitStaffWidth(170);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                app.getGamePanel().setinitStaffWidth(85);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void activateHex() {
        cannonsActive = true;
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cannonsActive = false;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void activateOverwhelmingFireBall() {
        fireBallPowerUp = true;
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireBallPowerUp = false;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
