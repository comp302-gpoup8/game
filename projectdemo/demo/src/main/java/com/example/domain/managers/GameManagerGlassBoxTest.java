import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.example.domain.Game;
import com.example.domain.gameObject.*;
import com.example.domain.gameObject.barriers.*;

import java.util.ArrayList;
import java.util.List;

public class GameManagerGlassBoxTest {
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
    public void testCheckCollisionsInitialization() {
        elements.add(new SimpleBarrier(null));
        elements.add(new ReinforcedBarrier(null));
        gameManager.checkCollisions();
        assertTrue("Collisions not checked as expected", gameManager.isCollisionsChecked());
    }

    @Test
    public void testCheckCollisionsWithSimpleBarrier() {
        SimpleBarrier barrier = new SimpleBarrier(null);
        elements.add(barrier);
        elements.add(new FireBall(null, null));
        gameManager.checkCollisions();
        assertEquals("SimpleBarrier was not removed after collision", 1, elements.size());
    }

    @Test
    public void testCheckCollisionsWithScoreUpdate() {
        SimpleBarrier barrier = new SimpleBarrier(null);
        elements.add(barrier);
        elements.add(new FireBall(null, null));
        int initialScore = game.getPlayer().score;
        gameManager.checkCollisions();
        assertTrue("Score was not updated correctly", game.getPlayer().score > initialScore);
    }
}
