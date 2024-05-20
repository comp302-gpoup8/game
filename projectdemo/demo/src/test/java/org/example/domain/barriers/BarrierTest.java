package org.example.domain.barriers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BarrierTest {

    Barrier barrier;

    @BeforeEach
    void setUp() {
        //CREATE - A barrier object. Simple Barrier icon is used because image is arbitrary.
        barrier = new Barrier(new Point(100, 100), "src/main/java/org/example/Graphical-Assets/BlueGem.png", 3) {};
    }

    @Test
    void reduceHp() {
        //Verifies whether the barrier hp is being reduced correctly.
        barrier.reduceHp(2);
        assertEquals(1, barrier.getHitpoints());

        barrier.reduceHp(0);
        assertEquals(1, barrier.getHitpoints());

        barrier.reduceHp(1);
        assertEquals(0, barrier.getHitpoints());
    }

    @Test
    void isDestroyed() {
        //Verifies whether destroyed barriers are correctly recognized or not.
        assertFalse(barrier.isDestroyed());

        barrier.reduceHp(2);
        assertFalse(barrier.isDestroyed());

        barrier.reduceHp(5);
        assertTrue(barrier.isDestroyed());
    }

    @Test
    void getHitpoints() {
        //Verifies that the barrier hitpoints are accessible with the get method.
        assertEquals(3, barrier.getHitpoints());
    }
}