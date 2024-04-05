package system;

public class ScoreManager {
    private int personalBest;

    public ScoreManager() {
        this.personalBest = 0; 
    }

    public void setNewRecord(Player player) {
        int currentScore = player.getScore();
        if (currentScore > personalBest) {
            personalBest = currentScore;
            System.out.println("Congratulations! New personal best: " + personalBest);
        }
    }

    public int getPersonalBest() {
        return personalBest;
    }
}
