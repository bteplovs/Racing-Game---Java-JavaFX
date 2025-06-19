package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.Races.Race;
import seng201.team0.models.Races.Route;
import seng201.team0.services.GameService;
import seng201.team0.services.GameplayService;
import seng201.team0.util.Scene.PopupManager;

import java.util.Objects;

/**
 * Controller for the Game screen which is where the races occur.
 * This controller handles and manages the players input, UI updates, and communicates them
 * with the GameplayService class. This executed the games logic which includes things like
 * updating fuel, speed, progress, and leaderboard all real time while the player races.
 */
public class GameController {

    @FXML
    private Label labelPlayerMoney;

    @FXML
    private Label labelTimePenalty;

    @FXML
    private ImageView buttonSpeedUp;

    @FXML
    private ImageView buttonSlowDown;

    @FXML
    private ImageView imageViewFuelCan;

    @FXML
    private Button buttonStartCar;

    @FXML
    private ImageView imageViewSpeedDial;

    @FXML
    private Label labelCurrentDistance;

    @FXML
    private Label labelCurrentTime;

    @FXML
    private ProgressBar progressDistance;

    @FXML
    private Button reactionButton;

    private final GameplayService gameplayService = new GameplayService();
    private final GameService gameService = GameService.getInstance();
    /**
     * A predefined car speed multiplier which is used when the player wants to speed up or slow down.
     */
    private final double[] speedLevels = {0.5, 0.8, 1.25};
    /**
     * An index used only to keep track of players current speed level.
     */
    private int currentSpeedIndex = 1;

     /**
     * Image paths for the players speedometer and fuel gauge.
     */
    private final String[] speedImages = {"/Assets/GameFX/SpeedSlow.png", "/Assets/GameFX/SpeedDefault.png", "/Assets/GameFX/SpeedFast.png"};
    private final String[] fuelImages = {"/Assets/GameFX/Fuel0%.png", "/Assets/GameFX/Fuel25%.png", "/Assets/GameFX/Fuel50%.png", "/Assets/GameFX/Fuel75%.png", "/Assets/GameFX/Fuel100%.png"};

    /**
     * Shows the leader board
     */
    @FXML
    private ListView<Label> leaderboardListView;

    /**
     * Called when the FXML screen is loaded to initialise the starting UI components
     * and sets them to their default visuals.
     */
    @FXML
    public void initialize() {
        gameplayService.initializeHud(labelCurrentDistance, labelCurrentTime, progressDistance,labelPlayerMoney,labelTimePenalty);
        updateSpeedImage();
        updateFuelImage(100.0);
        reactionButton.setOnMouseClicked(event -> handleReactionButtonClick());
    }

    /**
     * Handles the main start logic once the "Start Car" button has been pressed.
     * This begins the race just after it initialises the gameplay components.
     */
    @FXML
    private void buttonStartCarPressed() {
        Car selectedCar = gameService.getPlayer().getSelectedCar();
        Route selectedRoute = gameService.getSelectedRoute();
        Race selectedRace = gameService.getSelectedRace();

        gameplayService.initializeHud(labelCurrentDistance, labelCurrentTime, progressDistance,labelPlayerMoney,labelTimePenalty);
        gameplayService.setLeaderboardBox(leaderboardListView);
        gameplayService.startRace(selectedCar, selectedRoute, selectedRace, speedLevels[currentSpeedIndex], this, leaderboardListView);
        buttonStartCar.setVisible(false);
    }

    /**
     * Handles the player's reaction button click.
     * If the button is visible and active and the player clicks it in time,
     * it first marks the button as clicked, hides it, and notifies the GameplayService to avoid a penalty.
     */
    @FXML
    private void handleReactionButtonClick() {
        if (reactionButton.isVisible() && gameplayService.isReactionButtonActive()) {
            reactionButton.setVisible(false);
            gameplayService.setReactionButtonActive(false);
            gameplayService.setButtonWasClicked(true);
        }
    }

    /**
     * Increases the speed level and updates the UI indicator.
     */
    @FXML
    private void buttonSpeedUpPressed() {
        if (currentSpeedIndex < speedLevels.length - 1) {
            currentSpeedIndex++;
            gameplayService.adjustSpeed(speedLevels[currentSpeedIndex]);
            updateSpeedImage();
        }
    }
    /**
     * Decreases the speed level and updates the UI indicator.
     */
    @FXML
    private void buttonSlowDownPressed() {
        if (currentSpeedIndex > 0) {
            currentSpeedIndex--;
            gameplayService.adjustSpeed(speedLevels[currentSpeedIndex]);
            updateSpeedImage();
        }
    }
    /**
     * Updates the speedometer when the speed or break pedal is pressed
     * inorder to match the current speed level.
     */
    private void updateSpeedImage() {
        imageViewSpeedDial.setImage(new Image(Objects.requireNonNull(getClass().getResource(speedImages[currentSpeedIndex])).toExternalForm()));
    }

    /**
     * Updates the fuel image on the GUI as the players fuel decreases
     *
     * @param fuel The fuel level of the players car
     */
    public void updateFuelImage(double fuel) {
        int index = fuel >= 75 ? 4 : fuel >= 50 ? 3 : fuel >= 25 ? 2 : fuel > 10 ? 1 : 0;
        imageViewFuelCan.setImage(new Image(Objects.requireNonNull(getClass().getResource(fuelImages[index])).toExternalForm()));
    }
    /**
     * Visual press feedback for the acceleration pedal.
     */
    public void peddleUpAccelerate() {
        buttonSpeedUp.setImage(new Image(Objects.requireNonNull(getClass().getResource("/Assets/GameFX/AccelerateUp.png")).toExternalForm()));
    }

    /**
     * Updates the visual component of the acceleration pedal being pressed.
     */
    public void peddleDownAccelerate() {
        buttonSpeedUp.setImage(new Image(Objects.requireNonNull(getClass().getResource("/Assets/GameFX/AccelerateDown.png")).toExternalForm()));
    }
    /**
     * Updates the visual component of the break pedal being pressed.
     */
    public void peddleDownBreak() {
        buttonSlowDown.setImage(new Image(Objects.requireNonNull(getClass().getResource("/Assets/GameFX/BreakDown.png")).toExternalForm()));
    }
    /**
     * The visual release feedback for the brake pedal
     */
    public void peddleUpBreak() {
        buttonSlowDown.setImage(new Image(Objects.requireNonNull(getClass().getResource("/Assets/GameFX/BreakUp.png")).toExternalForm()));
    }

    /**
     * Displays the reaction button on the UI.
     * Called by the GameplayService to get a player interaction.
     */
    public void showReactionButton() {
        reactionButton.setVisible(true);
    }

    /**
     * Hides the reaction button from the UI.
     * Called by the GameplayService after time expires or when clicked.
     */
    public void hideReactionButton() {
        reactionButton.setVisible(false);
    }

}