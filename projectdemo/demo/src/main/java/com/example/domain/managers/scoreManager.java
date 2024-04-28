package com.example.domain.managers;

import java.util.HashMap;
import java.util.Map;


//For leaderboard, not implemented yet because working on other things needed.
/*

public class scoreManager {
    private int personalBest;
    private Map<Player, Integer> playerScores;

    public scoreManager() {
        this.personalBest = 0;
        this.playerScores = new HashMap<>();
    }

    public void setNewRecord(Player player) {
        int currentScore = player.getScore();
        playerScores.put(player, currentScore);
        if (currentScore > personalBest) {
            personalBest = currentScore;
            System.out.println("Congratulations! New personal best for " + player.getName() + ": " + personalBest);
        }
    }

    public int getPersonalBest() {
        return personalBest;
    }

    public void displayLeaderboard() {
        System.out.println("Leaderboard:");
        int rank = 1;
        playerScores.entrySet().stream()
                .sorted(Map.Entry.<Player, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.println(rank + ". " + entry.getKey().getName() + ": " + entry.getValue());
                    rank++;
                });
    }
}

*/