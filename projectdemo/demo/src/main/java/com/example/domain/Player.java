package com.example.domain;

import java.io.Serializable;

public class Player implements Serializable {

    public Integer remainingLives;
    public Integer score;
    public String name;

    public Player(String n){
        name = n;
        score = 0;
        remainingLives = 3;
    }

}
