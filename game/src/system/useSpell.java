package system;

import java.util.ArrayList;

import domain.barriers.Barrier;

class Player {
	
 GameInterface gameInterface;

 public Player(GameInterface gameInterface) {
     this.gameInterface = gameInterface;
 }

 public void useSpell() {
     String spellId = gameInterface.selectSpell();
     gameInterface.castSpell(spellId);
 }
}

interface GameInterface {
 String selectSpell();
 void castSpell(String spellId);
 void displaySpells(String spellList);
 void confirmSpellActivation();
}


class GameInterfaceImpl implements GameInterface {
 SpellManager spellManager;
 GameEnvironment gameEnvironment;

 public GameInterfaceImpl(SpellManager spellManager, GameEnvironment gameEnvironment) {
     this.spellManager = spellManager;
     this.gameEnvironment = gameEnvironment;
 }

 @Override
 public String selectSpell() {
     String availableSpells = spellManager.getAvailableSpells();
     displaySpells(availableSpells);

     return "FelixFelicis";
 }

 @Override
 public void castSpell(String spellId) {
     spellManager.activateSpell(spellId);
 }

 @Override
 public void displaySpells(String spellList) {
   
 }

 @Override
 public void confirmSpellActivation() {
    
 }
}


class SpellManager {
 GameEnvironment gameEnvironment;

 public SpellManager(GameEnvironment gameEnvironment) {
     this.gameEnvironment = gameEnvironment;
 }

 public String getAvailableSpells() {
  
     return "SpellList";
 }

 public void activateSpell(String spellId) {
     boolean isSpellActivated = gameEnvironment.applySpellEffect(spellId);
     if (isSpellActivated) {
         gameEnvironment.updateGameState();
     }
 }
}


class GameEnvironment {
 public boolean applySpellEffect(String spellId) {

     return true; 
 }

 public void updateGameState() {
     
 }
}


public class Main {
 public static void main(String[] args) {
     GameEnvironment gameEnvironment = new GameEnvironment();
     SpellManager spellManager = new SpellManager(gameEnvironment);
     GameInterfaceImpl gameInterface = new GameInterfaceImpl(spellManager, gameEnvironment);
     Player player = new Player(gameInterface);

     player.useSpell();
 }
}
