/**
 * PlayerResult class represents the result of a player's dice roll in a game.
 * It stores the player's name, the values of two dice, and the player's score.
 */
public class PlayerResult {
    private String playerName; // Player's name
    private int dice1; // Value of the first dice
    private int dice2; // Value of the second dice
    private int score; // Player's score

    /**
     * Constructor for PlayerResult class.
     *
     * @param playerName The name of the player.
     * @param dice1      The value of the first dice.
     * @param dice2      The value of the second dice.
     * @param score      The score of the player.
     */
    public PlayerResult(String playerName, int dice1, int dice2, int score) {
        this.playerName = playerName;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.score = score;
    }

    /**
     * Retrieves the player's name.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Retrieves the value of the first dice.
     *
     * @return The value of the first dice.
     */
    public int getDice1() {
        return dice1;
    }

    /**
     * Retrieves the value of the second dice.
     *
     * @return The value of the second dice.
     */
    public int getDice2() {
        return dice2;
    }

    /**
     * Retrieves the player's score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Generates a string representation of the player's result based on the dice roll.
     *
     * @return A string representing the player's result.
     */
    @Override
    public String toString() {
        if (dice1 == 1 && dice2 == 1) {
            return playerName + " threw 1-1. Game over " + playerName + "!";
        } else if (dice1 == 1 || dice2 == 1) {
            return playerName + " threw " + dice1 + "-" + dice2 + " and " + playerName + "’s score is " + score + ".";
        } else if (dice1 == 0 && dice2 == 0) {
            return playerName + " skipped the turn and " + playerName + "’s score is " + score + ".";
        } else {
            return playerName + " threw " + dice1 + "-" + dice2 + " and " + playerName + "’s score is " + score + ".";
        }
    }

    /**
     * Generates a string representation indicating the winner of the game.
     *
     * @return A string representing the winner of the game.
     */
    public String Winner() {
        return playerName + " is the winner of the game with the score of " + score + ". Congratulations " + playerName + "!";
    }
}
