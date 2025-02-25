/**
 * Player class represents a player in the game.
 * It stores the player's name, score, and status whether the player is in the list or not.
 */
public class Player {
    private String name; // Player's name
    private int score; // Player's score
    private boolean playerInList; // Flag indicating whether the player is in the list

    /**
     * Constructor for the Player class.
     *
     * @param name  The name of the player.
     * @param score The initial score of the player.
     */
    Player(String name, int score) {
        this.setScore(score);
        this.setName(name);
        this.setPlayerInList(true); // By default, new players are in the list
    }

    /**
     * Retrieves the player's name.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     *
     * @param name The player's name to set.
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the player's score.
     *
     * @param score The player's score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Checks if the player is in the list.
     *
     * @return True if the player is in the list, otherwise false.
     */
    public boolean isPlayerInList() {
        return playerInList;
    }

    /**
     * Sets the player's status whether the player is in the list or not.
     *
     * @param playerInList The flag indicating whether the player is in the list.
     */
    public void setPlayerInList(boolean playerInList) {
        this.playerInList = playerInList;
    }
}
