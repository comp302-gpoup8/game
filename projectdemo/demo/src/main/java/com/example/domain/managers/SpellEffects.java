
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
    private boolean FireBallPowerUp = false;
    private boolean cannonsActive = false;
    private boolean fireBallPowerUp = false;


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
        this.getApp().getGamePanel().setinitStaffWidth(170);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                this.getApp().getGamePanel().setinitStaffWidth(85);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void activateHex() {
        context.setCannonsActive(true);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                context.setCannonsActive(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void activateOverwhelmingFireBall() {
        this.setFireBallPowerUp(true);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                this.game.setFireBallPowerUp(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}



// For Game Environment
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

