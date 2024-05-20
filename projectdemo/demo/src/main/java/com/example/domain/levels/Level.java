package com.example.domain.levels;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.domain.gameObject.barriers.Barrier;

/**
 * Level class
 * Contains the Barriers in the level and handles common operations.
 */
public class Level implements Serializable{

    @Serial
    private static final long serialVersionIUD = 1L;
    public String levelName;
    public ArrayList<Barrier> barriers;
    
    public Level(String name) {
        levelName = name;
        barriers = new ArrayList<>();
    }

    /**
     * Adds a barrier to the level.
     * @param bar
     */
    public void addBarrier(Barrier bar){
        barriers.add(bar);
    }

    /**
     * Removes a barrier from the level.
     * @param bar
     */
    public void removeBarrier(Barrier bar){
        barriers.remove(bar);
    }

    /**
     * Determines whether a level is cleared or not.
     * @return true if there are no more barriers on the level.
     */
    public boolean isCleared(){
        return barriers.isEmpty();
    }

    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }

    public void setBarriers(ArrayList<Barrier> barriers) {
        this.barriers = barriers;
    }
}
