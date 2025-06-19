package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import seng201.team0.models.Game.Player;
import seng201.team0.models.Races.Race;
import seng201.team0.models.Races.Route;
import seng201.team0.services.GameService;
import seng201.team0.services.RouteService;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneSwitcher;

/**
 * Controller for the Route Selection Screen.
 * This class is responsible for the UI interactions between
 * choosing and displaying route options within a selected race.
 */
public class RouteController {

    @FXML
    private TextArea routeInfo;

    @FXML
    private ImageView buttonRouteOne;

    @FXML
    private ImageView buttonRouteTwo;

    @FXML
    private ImageView buttonRouteThree;

    @FXML
    private Button buttonToMenu;

    @FXML
    private Button buttonRace;

    @FXML
    private Label labelSeasonProgress;

    @FXML
    private Label labelPlayerMoney;

    /**
     * The current player.
     */
    Player player = GameService.getInstance().getPlayer();

    /**
     * Initialises the route screen by updating the UI labels with the players stats and race progress.
     */
    public void initialize() {
        labelSeasonProgress.setText("Season Progress: " + player.getRacesPlayed() + "/" + GameService.getInstance().getSeasonLength());
        GameService.getInstance().showMoney(labelPlayerMoney);
    }

    /**
     * Currently selected race.
     */
    Race selectedRace = GameService.getInstance().getSelectedRace();
    private RouteService routeService = new RouteService();

    /**
     * Handles the selection of route one.
     */
    public void buttonRouteOnePressed(MouseEvent mouseEvent) {
        Route routeone = selectedRace.getRoutes().get(0);
        GameService.getInstance().setSelectedRoute(routeone);
        routeInfo.setText(routeone.toString());
    }

    /**
     * Handles the selection of route two.
     */
    public void buttonRouteTwoPressed(MouseEvent mouseEvent) {
        Route routeTwo = selectedRace.getRoutes().get(1);
        GameService.getInstance().setSelectedRoute(routeTwo);
        routeInfo.setText(routeTwo.toString());
    }

    /**
     * Handles the selection of router three.
     */
    public void buttonRouteThreePressed(MouseEvent mouseEvent) {
        Route routeThree = selectedRace.getRoutes().get(2);
        GameService.getInstance().setSelectedRoute(routeThree);
        routeInfo.setText(routeThree.toString());
    }

    /**
     * Changes the colour from white to yellow when the mouse hovers over the route.
     */
    public void changeColourYellow(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        routeService.ChangeColourYellow(image);
    }

    /**
     * Changes the colour to red when the mouse hovers away from the selected route.
     */
    public void changeColourRed(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        routeService.ChangeColourRed(image);
    }

    /**
     * Handles the events when the "Race" button is pressed.
     */
    public void buttonToRacePressed(ActionEvent actionEvent) {
        routeService.handleButtonToRacePressed();
    }

    /**
     * Begins the race on the selected route. If no route has been selected an error will appear instead.
     */
    public void beginRace(ActionEvent actionEvent) {
        if (GameService.getInstance().getSelectedRoute() == null) {
            PopupManager.showError("Select a Route", null, "Please select a Route");
        } else {
                SceneSwitcher.moveToGame();
            }
        }
}
