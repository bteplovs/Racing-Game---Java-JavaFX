package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import seng201.team0.models.Game.Player;
import seng201.team0.models.NPCs.AllNPCs;
import seng201.team0.models.Races.AllRaces;
import seng201.team0.services.GameService;
import seng201.team0.services.RaceService;
import seng201.team0.util.Dialogue.NpcDialogue;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneSwitcher;

import java.util.Random;

/**
 * Controller for the Race Selection Screen.
 * Responsible for the interactions between player interactions of the race selection,
 * viewing the races details, and navigating to race screen while
 * delegating the main logic to the RaceService class.
 */
public class RaceController {


    @FXML
    private TextArea raceInfo;

    @FXML
    private ImageView buttonToAmerica;

    @FXML
    private ImageView buttonToEurope;

    @FXML
    private ImageView buttonToJapan;

    @FXML
    private Button buttonToMenu;

    @FXML
    private Button buttonToSelectedRace;

    @FXML
    private Label labelSeasonProgress;

    @FXML
    private Label labelPlayerMoney;

    /** Gets the instance of the current player. */
    Player player = GameService.getInstance().getPlayer();

    private final RaceService raceService = new RaceService();

    /**
     * Initialises the race selection screen by updating the season progress and players current money
     */
    public void initialize() {
        labelSeasonProgress.setText("Season Progress: " + player.getRacesPlayed() + "/" + GameService.getInstance().getSeasonLength());
        GameService.getInstance().showMoney(labelPlayerMoney);
    }


    /**
     * Handles the events when the America race is selected.
     */
    public void buttonToAmericaPressed(MouseEvent mouseEvent) {
        GameService.getInstance().setSelectedRace(AllRaces.getAmerica());
        raceInfo.setText(AllRaces.getAmerica().getRaceInfo());
    }

    /**
     * Handles the events when the Europe race is selected.
     */
    public void buttonToEuropePressed(MouseEvent mouseEvent) {
        GameService.getInstance().setSelectedRace(AllRaces.getEurope());
        raceInfo.setText(AllRaces.getEurope().getRaceInfo());
    }

    /**
     * Handles the events when the Japan race is selected.
     */
    public void buttonToJapanPressed(MouseEvent mouseEvent) {
        GameService.getInstance().setSelectedRace(AllRaces.getJapan());
        raceInfo.setText(AllRaces.getJapan().getRaceInfo());
    }

    /**
     * Handles the event when the mouse hovers over the races and turns them from white to yellow.
     */
    public void changeColourYellow(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        raceService.ChangeColourYellow(image);
    }

    /**
     * Changes the clicked images color to red to visually show the selection.
     */
    public void changeColourRed(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        raceService.ChangeColourRed(image);
    }

    /**
     * Once the "Menu" button is pressed it returns back to the Main Menu Screen.
     */
    public void buttonToMenuPressed(ActionEvent actionEvent) {
        raceService.handleButtonToMenuPressed();
    }

    /**
     * Moves the user to the selected race screen.
     * If no races have been selected it shows an error.
     */
    public void buttonToSelectedRacePressed(ActionEvent actionEvent) {
        raceService.handleButtonToSelectedRacePressed();
    }
}


