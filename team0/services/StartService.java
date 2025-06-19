package seng201.team0.services;

import javafx.scene.control.ChoiceBox;
import seng201.team0.models.Game.Difficulty;
import seng201.team0.models.NPCs.AllNPCs;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneSwitcher;
import seng201.team0.util.Dialogue.NpcDialogue;

/**
 * This class handles the initialization of Start/player settings, including
 * season length, game difficulty, and validates the users in-game name
 */
public class StartService {

    /**
     * Initializes season length choice box with values from 5-15 inclusive
     *
     * @param choiceBox The onscreen choice box holding season values
     */
    public void initializeSeasonChoiceBox(ChoiceBox<Integer> choiceBox) {
        choiceBox.getItems().addAll(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        choiceBox.setValue(5);
        choiceBox.setOnAction(event -> GameService.getInstance().setSeasonLength(choiceBox.getValue()));
    }

    /**
     * Initializes Difficulty choice box with values in {Easy, Medium, Hard}
     *
     * @param choiceBox The onscreen choice box holding difficulty values
     */
    public void initializeDifficultyChoiceBox(ChoiceBox<String> choiceBox) {
        for (Difficulty diff : Difficulty.values()) {
            choiceBox.getItems().add(diff.name());
        }
        choiceBox.setValue(Difficulty.MEDIUM.name());
        choiceBox.setOnAction(event -> { GameService.getInstance().setDifficulty(Difficulty.valueOf(choiceBox.getValue()));});
    }

    /**
     * Validates the username inputted by the user in the onscreen text field, accepts usernames
     * which are 3-15 characters long inlc. with no special characters (alphanumeric)
     *
     * @param enteredName the inputted username
     */
    public static void validUserName(String enteredName) {
        boolean isValidLength = enteredName.length() >= 3 && enteredName.length() <= 15;
        boolean isAlphanumeric = enteredName.matches("[a-zA-Z0-9]+");

        if (!isAlphanumeric || !isValidLength) {
            PopupManager.showError("Invalid Name", null, "Name must be between 3-15 characters long with no special characters");
        } else {
            GameService.getInstance().setPlayer(enteredName);
            NpcDialogue.showDialogue(AllNPCs.getChampion(), "0");
            SceneSwitcher.moveToPickCar();
        }
    }

    /**
     * Created for testign purposes since the validUserName was hard to test
     * because the other one triggered popups
     * @param enteredName the name parameter given by the test
     * @return returns true if valid or false if not
     */
    public static boolean isUserNameValid(String enteredName) {
        return enteredName.matches("[a-zA-Z0-9]+") && enteredName.length() >= 3 && enteredName.length() <= 15;
    }
}