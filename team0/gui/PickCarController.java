package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import seng201.team0.models.Game.Player;
import seng201.team0.services.GameService;
import seng201.team0.services.PickCarService;

/**
 * Controller for the Pick Car Screen.
 * This class is responsible for connecting the user interface actions to the
 * PickCarService which handles all the main logic behind teh scenes.
 */
public class PickCarController {

    /**
     * Label displaying the player's current money. *
     */
    @FXML
    private Label labelPlayerMoney;

    /**
     * ImageView displaying the player's currently selected car.
     */
    @FXML
    private ImageView imageDefaultCar;

    /**
     * TextArea showing the currently selected cars stats and description.
     */
    @FXML
    private TextArea carStats;

    /**
     * Service handling the main logic for browsing and purchasing cars.
     */
    private final PickCarService pickCarService = new PickCarService();
    Player player = GameService.getInstance().getPlayer();

    /**
     * Initialises the screen by showing the player's currently selected car and the player's current money.
     * It is loaded automatically when the scene starts.
     */
    @FXML
    public void initialize() {
        GameService.getInstance().showMoney(labelPlayerMoney);
        pickCarService.setCarOnDisplay(imageDefaultCar, carStats);
    }
    /**
     * Responsible for the buying logic once the button to buy the currently selected car is pressed. "
     */
    public void buyCar(MouseEvent mouseEvent) { pickCarService.handleBuyCar(labelPlayerMoney); }

    /**
     * Handles the car switching logic when the player scrolls inorder to view other cars in the shop.
     */
    public void changeCar(ScrollEvent scrollEvent) { pickCarService.handleChangeCar(imageDefaultCar, carStats); }

    /**
     * responsible for the logic once "Join" button is pressed.
     */
    public void onJoinPressed() {pickCarService.handleOnJoinedPressed();}

}
