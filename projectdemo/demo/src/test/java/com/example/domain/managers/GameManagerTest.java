package com.example.domain.managers;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import java.awt.Point;
import java.awt.Dimension;
import com.example.domain.gameObject.*;
import com.example.domain.gameObject.barriers.*;
import com.example.domain.levels.*;
import com.example.domain.Game;



public class GameManagerTest {

    private Level lv = new Level("a");
    private Game g = new Game("a", lv);
    private ArrayList<GameObject> elements = new ArrayList<>();
    private GameManager gm = new GameManager(g, elements);
    
    @Test
    public void testIncreaseScoreSimpleBarrier(){

        int beforeS = g.getPlayer().score;
        List<GameObject> toRemove = new ArrayList<>();
        toRemove.add(new SimpleBarrier(new Point(1,1)));
        gm.increaseScore(toRemove);
        int afterS = g.getPlayer().score;

        assertTrue(afterS-beforeS == 10);
    }

    @Test
    public void testIncreaseScoreReinforcedBarrier(){
        int beforeS = g.getPlayer().score;
        List<GameObject> toRemove = new ArrayList<>();
        toRemove.add(new ReinforcedBarrier(new Point(1,1)));
        gm.increaseScore(toRemove);
        int afterS = g.getPlayer().score;

        assertTrue(afterS-beforeS == 20);
    }

    @Test
    public void testIncreaseScoreExplosiveBarrier(){
        int beforeS = g.getPlayer().score;
        List<GameObject> toRemove = new ArrayList<>();
        toRemove.add(new ExplosiveBarrier(new Point(1,1)));
        gm.increaseScore(toRemove);
        int afterS = g.getPlayer().score;

        assertTrue(afterS-beforeS == 15);
    }

    @Test
    public void testIncreaseScoreRewardingBarrier(){
        int beforeS = g.getPlayer().score;
        List<GameObject> toRemove = new ArrayList<>();
        toRemove.add(new RewardingBarrier(new Point(1,1)));
        gm.increaseScore(toRemove);
        int afterS = g.getPlayer().score;

        assertTrue(afterS-beforeS == 15);
    }

    @Test
    public void testIncreaseScoreNotBarrier(){
        int beforeS = g.getPlayer().score;
        List<GameObject> toRemove = new ArrayList<>();
        toRemove.add(new FireBall(new Point(1,1), new Dimension(1,1)));
        gm.increaseScore(toRemove);
        int afterS = g.getPlayer().score;

        assertTrue(afterS-beforeS == 0);
    }

    
    @Test
    public void testMoveStaffInScreen(){
        Point beforeP = g.getPanel().getStaff().getHitBox().getLocation();
        gm.moveStaff(g.getPanel().getStaff(), 10);
        Point afterP = g.getPanel().getStaff().getHitBox().getLocation();
        assertTrue(afterP.getX()-beforeP.getX() == 10*g.getPanel().getStaff().getSpeed());
    }

    @Test
    public void testMoveStaffOutScreen(){
        Point beforeP = g.getPanel().getStaff().getHitBox().getLocation();
        gm.moveStaff(g.getPanel().getStaff(), 100000);
        Point afterP = g.getPanel().getStaff().getHitBox().getLocation();
        assertTrue(afterP.getX()-beforeP.getX() == g.getPanel().getWidth()/2);
    }

}
