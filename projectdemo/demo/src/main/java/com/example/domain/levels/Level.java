package com.example.domain.levels;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.domain.gameObject.barriers.Barrier;

public class Level implements Serializable{

    @Serial
    private static final long serialVersionIUD = 1L;
    public String levelName;
    public ArrayList<Barrier> barriers;
    
    public Level(String name) {
        levelName = name;
        barriers = new ArrayList<>();
    }

    public void addBarrier(Barrier bar){
        barriers.add(bar);
    }

    public void removeBarrier(Barrier bar){
        barriers.remove(bar);
    }

    public boolean isCleared(){
        return barriers.isEmpty();
    }
}
