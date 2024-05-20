import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.example.domain.Game;
import com.example.domain.gameObject.*;
import com.example.domain.gameObject.barriers.*;
import com.example.domain.managers.GameManager;

import java.util.ArrayList;
import java.util.List;

public class GameManagerBlackBoxTest {
    private GameManager gameManager;
    private Game game;
    private List<GameObject> elements;

    @Before
    public void setUp() {
        game = new Game("testPlayer");
        elements = new ArrayList<>();
        gameManager = new GameManager(game, elements);
    }

    @Test
    public void testCheckCollisionsWithNoCollisions() {
        elements.add(new SimpleBarrier(null));
        elements.add(new ReinforcedBarrier(null));
        gameManager.checkCollisions();
        assertEquals("Elements list size changed when no collisions should have occurred", 2, elements.size());
    }

    @Test
    public void testCheckCollisionsWithExplosiveBarrier() {
        ExplosiveBarrier barrier = new ExplosiveBarrier(null);
        elements.add(barrier);
        elements.add(new FireBall(null, null));
        int initialScore = game.getPlayer().score;
        gameManager.checkCollisions();
        assertTrue("Score was not updated correctly", game.getPlayer().score > initialScore);
        assertEquals("ExplosiveBarrier was not removed after collision", 1, elements.size());
    }
}
