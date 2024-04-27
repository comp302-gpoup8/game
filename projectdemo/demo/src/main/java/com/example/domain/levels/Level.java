package com.example.domain.levels;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.domain.barriers.Barrier;

public class Level implements Serializable{
    @Serial
    
    private static final long serialVersionIUD = 1L;
    public String levelName;
    public ArrayList<Barrier> barriers;
    
    public Level(String name) {
        levelName = name;
        barriers = new ArrayList<>();
    }
}
