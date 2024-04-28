package com.example.domain.managers;

import java.io.Serializable;

/**
 * Player class
 * Stores the Player's current score and remaining lives.
 * TODO Add account info
 * TODO Add spells in possesion
 * TODO Store previous scores and the amount of times played in the database.
 */
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
