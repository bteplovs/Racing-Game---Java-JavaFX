package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import seng201.team0.services.StartService;

/**
 * Controller for the Start Screen where the player sets their name,
 * choose the season length, and selects the game difficulty.
 */
public class StartController {

    /**
     * TextField where the player sets their name.
     */
    @FXML
    private TextField userName;

    /**
     * ChoiceBox allowing the user to select the number of
     * racers they want to play in the season.
     */
    @FXML
    private ChoiceBox<Integer> choiceBxSeasons;

    /**
     * ChoiceBox allowing the user to select their preferred difficulty level.
     */
    @FXML
    private ChoiceBox<String> choiceBxDifficulty;

    /** Service that handles the main logic and initialisation for the start screen. */
    private final StartService startService = new StartService();

    /**
     * Initialises all the ChoiceBoxes for the season length and difficulty options.
     */
    @FXML
    public void initialize() {
        startService.initializeSeasonChoiceBox(choiceBxSeasons);
        startService.initializeDifficultyChoiceBox(choiceBxDifficulty);
    }

    /**
     * Called once the player clicks on the "Join" button.
     * This function checks the username validity.
     */
    public void onSigned() {
        String enteredName = userName.getText();
        startService.validUserName(enteredName);
    }
}