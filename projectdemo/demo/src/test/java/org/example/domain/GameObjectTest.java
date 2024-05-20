package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

class GameObjectTest {

    GameObject gameObject;
    Point initialPoint;
    Dimension initialDimension;
    String imagePath;

    @BeforeEach
    void setUp() {
        //CREATE - new game object with arbitrary values (standard barrier size is used)
        //Creates object for the unit tests.
        initialPoint = new Point(10, 10);
        initialDimension = new Dimension(51, 15);
        imagePath = "src/main/java/org/example/Graphical-Assets/BlueGem.png";
        gameObject = new GameObject(initialPoint, initialDimension, imagePath) {};
    }


    @Test
    void createHitbox() {
        //CREATE - Hitbox for the game object.
        //Verifies if the hitbox is a rectangle with the specified size, at the specified point.
        gameObject.createHitbox(initialPoint, initialDimension);
        assertEquals(new Rectangle(initialPoint, initialDimension), gameObject.getHitBox());
    }

    @Test
    void putImageIcon() {
        //FILL - Puts the associated image icon on the game object within appropriate boundaries.
        //Verifies that the game icon is not null.
        //Verifies that it is placed on the correct point.
        //Verifies that it has the right size.
        gameObject.createHitbox(initialPoint, initialDimension);
        gameObject.putImageIcon(imagePath);
        assertNotNull(gameObject.getIcon());
        assertEquals(initialPoint, gameObject.getLocation());
        assertEquals(initialDimension, gameObject.getSize());
    }

    @Test
    void defaultMovement() {
        //INIT - Initializes the default speed and direction values of the game object.
        //Verifies the assigned values are the same as the default ones.
        gameObject.defaultMovement();
        assertEquals(Integer.valueOf(0), gameObject.getSpeed());
        assertEquals(new Point(-1, -1), gameObject.getDirection());
    }

    @Test
    void getHitBox() {
        //Verifies that the get method for the hitbox is working as intended.
        //Mostly the same as the createHitbox test.
        gameObject.createHitbox(initialPoint, initialDimension);
        Rectangle expectedHitBox = new Rectangle(initialPoint, initialDimension);
        assertEquals(expectedHitBox, gameObject.getHitBox());
    }

    @Test
    void setHitBox() {
        //Verifies that the set method for the hitbox is working as intended.
        //Mostly the same as the createHitbox test.
        Rectangle newHitBox = new Rectangle(new Point(20, 20), new Dimension(200, 200));
        gameObject.setHitBox(newHitBox);
        assertEquals(newHitBox, gameObject.getHitBox());
    }

    @Test
    void setIcon() {
        //Verifies that the set icon method is working as intended.
        //It is essentially the same as the putImageIcon method, but used to replace an icon.
        ImageIcon newIcon = new ImageIcon("src/main/java/org/example/Graphical-Assets/Firm.png");
        gameObject.setIcon(newIcon);
        assertEquals(newIcon, gameObject.getIcon());
    }

    @Test
    void getSpeed() {
        //Verifies the get and set methods for speed are working.
        gameObject.setSpeed(10);
        assertEquals(Integer.valueOf(10), gameObject.getSpeed());
    }

    @Test
    void setSpeed() {
        //Verifies the get and set methods for speed are working.
        gameObject.setSpeed(20);
        assertEquals(Integer.valueOf(20), gameObject.getSpeed());
    }

    @Test
    void getDirection() {
        //Verifies the get and set methods for direction are working.
        Point newDirection = new Point(1, 1);
        gameObject.setDirection(newDirection);
        assertEquals(newDirection, gameObject.getDirection());
    }

    @Test
    void setDirection() {
        //Verifies the get and set methods for direction are working.
        Point newDirection = new Point(2, 2);
        gameObject.setDirection(newDirection);
        assertEquals(newDirection, gameObject.getDirection());
    }
}