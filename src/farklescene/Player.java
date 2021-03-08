package farklescene;

/*
 * @author Wilson Krueger
 */

public class Player {
    private String name;
    private int playerScore;
    private int turnScore;
    
    Player(String inName) {
        name = inName;
        playerScore = 0;
        turnScore = 0;
    }
    
    public int getPlayerScore() {
        return playerScore;
    }
    
    public void setPlayerScore(int inPlayerScore) {
        playerScore = inPlayerScore;
    }
    
    public int getTurnScore() {
        return turnScore;
    }
    
    public void setTurnScore(int inTurnScore) {
        turnScore = inTurnScore;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public void addToPlayerScore(int inScore) {
        playerScore += inScore;
    }
    
    
}
