package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.EndService;
import seng201.team0.util.Scene.SceneSwitcher;

/**
 * Controller for the End Screen.
 * This class is responsible for the final end screen of the game which is shown after either
 * all the seasons have been completed, or the player can't continue on racing.
 * It initialises UI elements with end game statistics.
 */
public class EndController {

    @FXML
    private Label labelPlayerName;

    @FXML
    private Label labelSeasonLength;

    @FXML
    private Label labelRacesCompleted;

    @FXML
    private Label labelAveragePlacing;

    @FXML
    private Label labelTotalWinnings;

    @FXML
    private Label labelTotalPressed;

    EndService endService = new EndService();

    /**
     *  Initializes the final game summary and populates the JavaFX Labels with player performance data.
     */
    @FXML
    public void initialize() {
        endService.init(labelPlayerName, labelSeasonLength, labelRacesCompleted, labelAveragePlacing, labelTotalPressed ,labelTotalWinnings);
    }

    /**
     * Quits the application
     */
    public void buttonQuitPressed() {
        SceneSwitcher.quit();
    }
}