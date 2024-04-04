package domain.levels;

import java.util.ArrayList;

import domain.barriers.Barrier;

public class Level implements LvInterface {

    String levelName;
    public ArrayList<Barrier> barriers;

    public Level(String levelName) {
        this.levelName = levelName;
        barriers = new ArrayList<Barrier>();
    }

    public Level randomLevel() {
        LvInterface.randomizeLevel(this);
        return this;
    }
}
