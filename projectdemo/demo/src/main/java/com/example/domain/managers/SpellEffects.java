///////////////////////////////////////////////////////////////////////////////////////
//The spell effects and implementations are under this class with needed merges to the other classes in further steps,
//it will be fully implemented within other classes when it and reward barrier is required
/*
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class SpellEffects implements KeyListener {

    private GameContext context;

    public SpellEffects(GameContext context) {
        this.context = context;
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
        context.incrementChances();
    }

    public void activateMagicalStaffExpansion() {
        int originalLength = context.getMagicalStaffLength();
        context.setMagicalStaffLength(originalLength * 2);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                context.setMagicalStaffLength(originalLength);
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
        context.setFireBallPowerUp(true);
        Timer timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                context.setFireBallPowerUp(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}

// For Game Environment
private int chances = 3;
private int magicalStaffLength = 100;
private boolean cannonsActive = false;
private boolean fireBallPowerUp = false;
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



public void incrementChances() {
    chances++;
}
public int getChances() {
    return chances;
}

public void setChances(int chances) {
    this.chances = chances;
}

public int getMagicalStaffLength() {
    return magicalStaffLength;
}

public void setMagicalStaffLength(int length) {
    this.magicalStaffLength = length;
}

public boolean isCannonsActive() {
    return cannonsActive;
}

public void setCannonsActive(boolean active) {
    this.cannonsActive = active;
}

public boolean isFireBallPowerUp() {
    return fireBallPowerUp;
}

public void setFireBallPowerUp(boolean powerUp) {
    this.fireBallPowerUp = powerUp;
}
}
//Acquiring Spell
public void destroyBarrier(Barrier barrier) {
    if (barrier instanceof RewardingBarrier) {
        String spellName = ((RewardingBarrier) barrier).getSpellName();
        context.acquireSpell(spellName);
    }

}


// FOR GAME PANEL
private SpellEffects spellEffects = new SpellEffects(context);

addKeyListener(spellEffects);
*/
