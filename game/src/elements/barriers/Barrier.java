package elements.barriers;
import elements.GameElement;
import java.awt.Point;
import java.awt.Rectangle;


import java.awt.Dimension;

public abstract class Barrier extends GameElement {
    /** Hitpoints of the barrier */
    protected Integer hitPoints;
    /** The score that the player will gain upon destroying the barrier */
    protected Integer value;
    /** The rectangle that the image is placed in. */
    protected Rectangle rectangle;

    public Barrier(Point p, Dimension s, String iPath, int hp, int v){
        super(p, s, iPath);
        hitPoints = hp;
        value = v;
        rectangle = new Rectangle(p, s);
    }
    
    // GAME RELEVANT METHODS
    public void hit(){
        hitPoints--;
    }

    public boolean isDestroyed(){
        return hitPoints <= 0;
    }

    // GET METHODS
    public int getHp(){
        return hitPoints;
    }

    public int getValue(){
        return value;
    }

    // SET METHODS
    public void setHp(int hp){
        hitPoints = hp;
    }

    public void setVal(int v){
        value = v;
    }

}
