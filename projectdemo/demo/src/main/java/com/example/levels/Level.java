package com.example.levels;

import java.util.ArrayList;

import com.example.barriers.Barrier;

public class Level {
    public String levelName;
    public ArrayList<Barrier> barriers;
    
    public Level(String name) {
        levelName = name;
        barriers = new ArrayList<>();
    }
}