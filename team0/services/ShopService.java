package seng201.team0.services;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.Cars.AllCars;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.Parts.*;
import seng201.team0.models.Game.Player;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneSwitcher;

import java.util.Random;

/**
 * This class handles the initialization of Shop settings, including ImageViews of
 * displayed cars, parts, and their respective stats as well as navigation buttons,
 * and logic to handle purchasing Cars and Parts.
 */
public class ShopService {

    private final Player player = GameService.getInstance().getPlayer();

    static Random random = new Random();
    private static Car carOnDisplay;
    private int currentIndex;

    /**
     * Initializes the current car on display using a randomly generated index
     */
    public ShopService() {
        currentIndex = random.nextInt(AllCars.getCars().size());
        carOnDisplay = AllCars.getCars().get(currentIndex);
    }

    /**
     * Sets the ImageView and text area with the Image and stats of the carOnDisplay
     *
     * @param carImage The onscreen imageview
     * @param stats The onscreen text area
     */
    public static void setCarOnDisplay(ImageView carImage, TextArea stats) {
        Image image = new Image(carOnDisplay.getPath());
        carImage.setImage(image);
        stats.setText(carOnDisplay.carFormat());
    }

    /**
     * Calls handleBuyPart to perform purchasing logic on behalf of the NitroPart
     *
     * @param moneyLabel The onscreen label displaying the users money
     */
    public void handleBuySpeed(Label moneyLabel) {
        handleBuyPart(moneyLabel, AllParts.getNitroPart());
    }

    /**
     * Calls handleBuyPart to perform purchasing logic on behalf of the SteeringWheelPart
     *
     * @param moneyLabel The onscreen label displaying the users money
     */
    public void handleBuyHandling(Label moneyLabel) {
        handleBuyPart(moneyLabel, AllParts.getSteeringWheelPart());
    }

    /**
     * Calls handleBuyPart to perform purchasing logic on behalf of the CoilSpringPart
     *
     * @param moneyLabel The onscreen label displaying the users money
     */
    public void handleBuyReliability(Label moneyLabel) {
        handleBuyPart(moneyLabel, AllParts.getCoilSpringPart());
    }

    /**
     * Calls handleBuyPart to perform purchasing logic on behalf of the GasCanPart
     *
     * @param moneyLabel The onscreen label displaying the users money
     */
    public void handleBuyFuelEconomy(Label moneyLabel) {
        handleBuyPart(moneyLabel, AllParts.getGasCanPart());
    }

    /**
     * Handles purchasing of car part for the player
     * Displays a confirmation pop-up with the parts information and processes the purchase given the player has
     * sufficient funds. Updates the player's money and part availability status.
     *
     * If the player does not have enough money, a warning popup is displayed.
     * If the part is sold out, an error popup is shown
     *
     * @param moneyLabel
     * @param part
     */
    public void handleBuyPart(Label moneyLabel, Part part) {
        Boolean accepted = PopupManager.showConfirmation("Buy " + part.getName(), part.partFormat(), "Buy", "Cancel");

        if (accepted) {
            if (player.getMoney() < part.getBuyPrice()) {
                PopupManager.showWarning("Insufficient Funds", "You don't have enough money.");
            } else if (part.partIsAvailable()) {
                player.addPart(part);
                player.setMoney(player.getMoney() - part.getBuyPrice());
                GameService.getInstance().showMoney(moneyLabel);
                part.setPartIsAvailable(false);
            } else {
                PopupManager.showError("Part is Sold out",null,"Sorry this part is sold out, come back after a race");
            }
        }
    }

    /**
     * Handles changing the currently displayed car upon a scroll action
     * uses modular arithmetic to ensure index is always in range and the shop loops
     * Changes the image and stats to the currently displayed car
     *
     * @param carImage The onscreen imageview
     * @param stats The onscreen text area
     */
    public void handleChangeCar(ImageView carImage, TextArea stats) {
        currentIndex = (currentIndex + 1) % AllCars.getCars().size();
        carOnDisplay = AllCars.getCars().get(currentIndex);
        setCarOnDisplay(carImage, stats);
    }

    /**
     * Handles the menu button being pressed by the user
     * Transitions the screen to the main menu
     *
     */
    public void handleButtonToMenuPressed() {
        SceneSwitcher.moveToMenu();
    }

    /**
     * Handles the purchasing of the currently displayed car for the player
     * Displays a confirmation pop-up prompting the player to purchase the car or cancel the transaction.
     * upon confirmation, if the player owns the car, returns a warning pop-up
     *
     * if the player has too many cars, returns a warning pop-up
     *
     * if the player has insuffecient funds, returns a warning pop-up
     *
     * Otherwise, the car is added to the players inventory and the players money is updated
     *
     * @param moneyLabel The onscreen label displaying the players money
     */
    public void handleBuyCar(Label moneyLabel) {
        Boolean accepted = PopupManager.showConfirmation("Buy " + carOnDisplay.getName(), "Confirm purchase?", "Buy", "Cancel");

        if (accepted) {
            if (!player.ownsCar(carOnDisplay)) {
                if (player.tooManyCars()) {
                    PopupManager.showWarning("Too many cars", "You have too many cars, sell them from the garage for more space");
                    return;
                }

                if (player.getMoney() < carOnDisplay.getCost()) {
                    PopupManager.showWarning("Insufficient Funds", "You don't have enough money.");
                    return;
                }

                player.addCar(carOnDisplay);
                player.setMoney(player.getMoney() - carOnDisplay.getCost());
                GameService.getInstance().showMoney(moneyLabel);
            } else {
                PopupManager.showWarning("Car Already Owned", "You already own this car!");
            }
        }
    }
}
