package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.models.Game.Player;

import java.util.List;

/**
 * This class handles the initialization of the End / Summary screen
 * This class gathers, calculates, and displays players performance data like name, average placing, season length,
 * races won, races lost, and total prize money won by the player during the game.
 */
public class EndService {

    private Player player;
    private String playerName;
    private int seasonLength;
    private int racesCompetedIn;
    private float totalPrizeMoney;
    private double averagePlacing;
    private int buttonPressed;

    /**
     * Constructs an EndService instance by retrieving players data gathered during the game and computing the players average placing
     *
     */
    public EndService() {
        player = GameService.getInstance().getPlayer();
        seasonLength = GameService.getInstance().getSeasonLength();
        racesCompetedIn = player.getRacesPlayed();
        totalPrizeMoney = player.getEarnedMoney();
        playerName = player.getName();

        List<Float> placings = player.getPlayerPlacings();

        double sum = 0.0;
        int counter = 0;
        for (Float placing : placings) {
            sum += placing;
            if (placing != 0) {
                counter++;
            }
        }

        if (placings.isEmpty() || counter == 0) {
            averagePlacing = 0;
        } else {
            averagePlacing = sum / counter;
        }
    }

    /**
     * Initializes the final game summary and populates the JavaFX Labels with players performance data gathered during the game.
     *
     * @param labelPlayerName label to show player's name
     * @param labelSeasonLength label to show season length
     * @param labelRacesCompleted label to show number of races completed
     * @param labelAveragePlacing label to show average placing
     * @param labelTotalPressed label to show total reaction button pressed
     * @param labelTotalWinnings label to show total prize money earned
     */
    public void init(Label labelPlayerName, Label labelSeasonLength, Label labelRacesCompleted, Label labelAveragePlacing, Label labelTotalPressed, Label labelTotalWinnings) {
        labelPlayerName.setText("Player Name: " + playerName);
        labelSeasonLength.setText("Season Length: " + seasonLength);
        labelRacesCompleted.setText("Races Completed: " + racesCompetedIn);
        labelAveragePlacing.setText("Average Placing: " + averagePlacing);
        labelTotalPressed.setText("Total Reaction Button Pressed: " + player.getButtonPressed());
        labelTotalWinnings.setText("Total Winnings: " + totalPrizeMoney);
    }
}