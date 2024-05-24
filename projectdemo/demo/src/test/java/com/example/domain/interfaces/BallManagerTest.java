package com.example.domain.interfaces;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Dimension;
import java.awt.Point;
import com.example.domain.gameObject.FireBall;
import com.example.domain.gameObject.Staff;


public class BallManagerTest {

    private FireBall ball;
    private Staff staff;
    private Point initialPoint;
    private Dimension dimension;

    @BeforeEach
    public void setUp() {
        initialPoint = new Point(50, 50);
        dimension = new Dimension(10, 10);
        ball = new FireBall(initialPoint, dimension);
        staff = new Staff(new Point(100, 100), new Dimension(20, 20));
    }

    @Test
    public void testPlaceBallAtStaff() {
        BallManager.placeBallAtStaff(ball, staff);
        assertEquals(new Point(100, 80), ball.getLocation()); // Adjusted expected value
    }

    @Test
    public void testResetBall() {
        Point resetPoint = new Point(70, 70);
        BallManager.resetBall(ball, resetPoint);
        assertEquals(0, ball.getSpeed());
        assertEquals(resetPoint, ball.getLocation());
        assertEquals(resetPoint, ball.getHitBox().getLocation());
        assertEquals(new Point(-1, -1), ball.getDirection());
    }

    @Test
    public void testLaunchFireBall() {
        Point direction = new Point(1, 1);
        BallManager.launchFireBall(ball, direction);
        assertEquals(direction, ball.getDirection());
        assertEquals(4, ball.getSpeed());
        Point nextPoint = BallManager.nextPoint(ball);
        assertNotNull(nextPoint);
    }

    @Test
    public void testNextPoint() {
        ball.setSpeed(2);
        ball.setDirection(new Point(1, 1));
        Point next = BallManager.nextPoint(ball);
        assertEquals(new Point(52, 52), next);
    }

    @Test
    public void testValidateBallDirection() {
        ball.setDirection(null);
        Point direction = BallManager.validateBallDirection(ball);
        assertEquals(new Point(-1, -1), direction);
    }
}