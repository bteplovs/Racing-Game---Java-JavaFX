package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.Parts.Part;
import seng201.team0.models.Game.Player;
import seng201.team0.services.GameService;
import seng201.team0.services.GarageService;

/**
 * Controller for the Garage Screen.
 * This class handles the interaction between the player's car and parts inventory
 * and the upgrade and removal process.
 */
public class GarageController {
    /**
     * Listview showing all the players owned cars.
     */
    @FXML
    private ListView<Car> playerCars;
    /**
     * Listview showing all the equipped parts on the selected car.
     */
    @FXML
    private ListView<Part> carParts;
    /**
     * Listview showing all the players parts which are not yet in use.
     */
    @FXML
    private ListView<Part> playerParts;
    /**
     * TextArea displaying the currently selected car's stats.
     */
    @FXML
    private TextArea carStats;

    /**
     ImageView showing the selected cars image.
     */
    @FXML
    private ImageView selectedCarImage;

    /** Label displaying player's current money. */
    @FXML
    private Label playerMoney;
    /** Label indicating player's car inventory. */
    @FXML
    private Label labelPlayerCars;
    /** Label indicating player's parts inventory. */
    @FXML
    private Label labelPlayerParts;
    /** Label displaying the player's currently selected car. */
    @FXML
    private Label labelSelectedCar;

    /**
     * Reference to the current player.
     */
    Player player = GameService.getInstance().getPlayer();
    /**
     * Service class which handles and manages garage related logic.
     */
    private GarageService garageService = new GarageService();
    /**
     * The default car used in the garage if none are selected.
     */
    public Car defaultCar;

    /**
     * Initialises the garage scene, creating and setting up the player's
     * car garage and part inventory while using the default car to start with.
     */
    public void initialize() {

        labelPlayerCars.setText(player.getName()+ "'s" + " Cars:");

        labelPlayerParts.setText(player.getName() + "'s" + " Parts:");

        GameService.getInstance().showMoney(playerMoney);
        playerCars.getItems().addAll(player.getPlayerCars());
        playerParts.getItems().addAll(player.getPlayerParts());

        garageService.selectDefaultCar();

        if (player.getSelectedCar() != null) {
            garageService.updateCarParts(carParts, player.getSelectedCar());
            garageService.updateCarStats(carStats, player.getSelectedCar(), selectedCarImage);
        }
        playerCars.setOnMouseClicked(event -> garageService.handleCarSelection(playerCars,carParts,playerParts,selectedCarImage,carStats,playerMoney,labelSelectedCar));
        carParts.setOnMouseClicked(event -> garageService.handleCarPartSelection(carParts, playerParts, playerMoney,selectedCarImage, carStats));
        playerParts.setOnMouseClicked(event -> garageService.handlePlayerPartSelection(carParts, playerParts, playerMoney,selectedCarImage, carStats));
        garageService.updateLabel(labelSelectedCar);

    }

    /**
     * Handles the UI interaction between user and button
     *
     * @param actionEvent An event that occurs when a user interacts with the component
     */
    public void buttonToMenuPressed(ActionEvent actionEvent) {
        GarageService.handleButtonToMenuPressed();
    }
}
