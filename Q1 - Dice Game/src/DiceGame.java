import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the main entry point of the program.
 */
public class DiceGame {

    /**
     * The main method of the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initialize a list to store player results
        List<PlayerResult> playerResults = new ArrayList<>();

        // Read input file and parse player data
        String[] list = FileInput.readFile(args[0], false, false);
        String[] playersData = list[1].split(","); // Split player names separated by commas

        // Initialize an array to store player objects
        Player[] playersList = new Player[playersData.length];
        for (int i = 0; i < playersData.length; i++) {
            // Trim player name and create a Player object with initial score 0
            String playerName = playersData[i].trim();
            playersList[i] = new Player(playerName, 0);
        }

        int numberOfPlayers = playersData.length;
        int indexOfDice = 0; // Index where player dice data starts
        int currentPlayerIndex = 0;

        // Process player dice rolls
        while (numberOfPlayers > 1) {
            currentPlayerIndex = indexOfDice % numberOfPlayers;

            if (playersList[currentPlayerIndex].isPlayerInList() == false) {
                continue;
            }

            String[] playersDice = list[indexOfDice + 2].split("-"); // Split dice rolls by '-'

            int dice1 = Integer.parseInt(playersDice[0]);
            int dice2 = Integer.parseInt(playersDice[1]);

            if (dice1 == 1 && dice2 == 1) {
                // If both dice rolls are 1, remove the player from the list
                playerResults.add(new PlayerResult(playersList[currentPlayerIndex].getName(), dice1, dice2, playersList[currentPlayerIndex].getScore()));
                playersList[currentPlayerIndex].setPlayerInList(false);
                numberOfPlayers--;
                indexOfDice++;

            } else if (dice1 == 1 || dice2 == 1) {
                // If at least one dice roll is 1, add the player result without changing their score
                playerResults.add(new PlayerResult(playersList[currentPlayerIndex].getName(), dice1, dice2, playersList[currentPlayerIndex].getScore()));
                indexOfDice++;
            } else {
                // If both dice rolls are not 1, update player score and add the result
                playersList[currentPlayerIndex].setScore(playersList[currentPlayerIndex].getScore() + dice1 + dice2);
                playerResults.add(new PlayerResult(playersList[currentPlayerIndex].getName(), dice1, dice2, playersList[currentPlayerIndex].getScore()));
                indexOfDice++;
            }
        }

        // Write player results to the output file
        for (int i = 0; i < playerResults.size(); i++) {
            PlayerResult result = playerResults.get(i);
            String resultInfo = result.toString();
            FileOutput.writeToFile(args[1], resultInfo, true, true);
        }

        // Find the winner
        int maxScore = Integer.MIN_VALUE;
        Player winner = null;
        for (Player player : playersList) {
            if (player.isPlayerInList() && player.getScore() > maxScore) {
                maxScore = player.getScore();
                winner = player;
            }
        }

        // Write the winner information to the output file
        if (winner != null) {
            String winnerInfo = (winner.getName() + " is the winner of the game with the score of " + winner.getScore() + ". Congratulations " + winner.getName() + "!");
            FileOutput.writeToFile(args[1], String.valueOf(winnerInfo), true, false);
        }
    }
}
