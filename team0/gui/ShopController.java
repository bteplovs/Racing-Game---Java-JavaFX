package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import seng201.team0.models.Game.Player;
import seng201.team0.services.GameService;
import seng201.team0.services.ShopService;

/** Controller for the Shop Screen.
 * Responsible for the interactions between the player and the shop.
 * Players interactions can include buying parts, cars, and browsing available cars for purchase.
 */
public class ShopController {

    @FXML
    private ImageView carImage;

    @FXML
    private TextArea carStats;

    @FXML
    private Label playerMoney;

    private ShopService shopService;

    /**
     * The current player instance.
     */
    Player player = GameService.getInstance().getPlayer();

    /**
     * Initialises the shop interface by displaying the car and showing the player's money.
     */
    @FXML
    public void initialize() {
        shopService = new ShopService();
        shopService.setCarOnDisplay(carImage, carStats);
        GameService.getInstance().showMoney(playerMoney);
    }

    /**
     * Handles the purchase of the speed part.
     */
    public void buySpeed(MouseEvent mouseEvent) { shopService.handleBuySpeed(playerMoney); }

    /**
     * Handles the purchase of the handling part.
     */
    public void buyHandling(MouseEvent mouseEvent) { shopService.handleBuyHandling(playerMoney); }

    /**
     * Handles the purchase of the reliability part.
     */
    public void buyReliability(MouseEvent mouseEvent) { shopService.handleBuyReliability(playerMoney); }

    /**
     * Handles the purchase of the fuel economy part.
     */
    public void buyFuelEconomy(MouseEvent mouseEvent) { shopService.handleBuyFuelEconomy(playerMoney); }

    /**
     * Navigates back to the menu.
     */
    public void buttonToMenuPressed(ActionEvent actionEvent) { shopService.handleButtonToMenuPressed(); }

    /**
     * Handles the purchasing of the currently displayed car event.
     */
    public void buyCar(MouseEvent mouseEvent) { shopService.handleBuyCar(playerMoney);}

    /**
     * Handles the scroll event which switches to the next car in teh shop.
     */
    public void changeCar(ScrollEvent scrollEvent) {  shopService.handleChangeCar(carImage, carStats);  }
}

