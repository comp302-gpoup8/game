package system;
import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {
    @Serial
    private static final Long serialVersionIUD = 1L;
    private Integer hitpoints;
    private Integer score;

    public Player() {
        this.hitpoints = 3; 
        this.score = 0;
    }
    public void decreaseHitpoints(int amount) {
        hitpoints -= amount;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getScore() {
        return score;
    }
}
