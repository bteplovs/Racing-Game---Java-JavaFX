package seng201.team0.services;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.Cars.AllCars;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.NPCs.AllNPCs;
import seng201.team0.models.NPCs.NPC;
import seng201.team0.models.Game.Player;
import seng201.team0.util.Dialogue.NpcDialogue;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneSwitcher;

import java.util.List;

/**
 * This class handles the initialization of Shop settings, including ImageViews of
 * displayed cars and their respective stats as well as navigation buttons,
 * and logic to handle purchasing the players first cars.
 */
public class PickCarService {

    private static final Player player = GameService.getInstance().getPlayer();
    private final List<Car> starterCars = AllCars.getDefaultStarterCars();
    private Car carOnDisplay;
    private int currentCarIndex = 0;

    /**
     * Sets the ImageView and text area with the Image and stats of the carOnDisplay
     *
     * @param carImage The onscreen imageview
     * @param stats The onscreen text area
     */
    public void setCarOnDisplay(ImageView carImage, TextArea stats) {
        carOnDisplay = starterCars.get(currentCarIndex);
        Image image = new Image(carOnDisplay.getPath());
        carImage.setImage(image);
        stats.setText(carOnDisplay.carFormat());
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
        currentCarIndex = (currentCarIndex + 1) % starterCars.size();
        carOnDisplay = starterCars.get(currentCarIndex);
        setCarOnDisplay(carImage, stats);
    }

    /**
     * Handles the purchasing of the currently displayed car for the player
     * Displays a confirmation pop-up prompting the player to purchase the car or cancel the transaction.
     * upon confirmation, if the player owns the car, returns a warning pop-up
     *
     * if the player has too many cars, returns a warning pop-up
     *
     * if the player has insufficient funds, returns a warning pop-up
     *
     * Otherwise, the car is added to the players inventory and the players money is updated
     *
     * @param moneyLabel The onscreen label that displays the players money
     */
    public void handleBuyCar(Label moneyLabel) {
        if (carOnDisplay == null) return;

        boolean accepted = PopupManager.showConfirmation("Buy " + carOnDisplay.getName(), "Buy car for " + carOnDisplay.getCost() + "?", "Buy", "Cancel");

        if (accepted) {
            if (!player.ownsCar(carOnDisplay)) {
                player.addCar(carOnDisplay);
                player.setMoney(player.getMoney() - carOnDisplay.getCost());
                //moneyLabel.setText(String.valueOf(player.getMoney()));
                GameService.getInstance().showMoney(moneyLabel);
            } else {
                PopupManager.showWarning("Car Already Owned", "You already bought this car!");
            }
        }
    }

    /**
     * Handles the join button pressed. shows warning pop-up if no cars were purchased, otherwise sets the players
     * selected car to the first car in the players cars and plays Champion dialogue
     *
     */
    public void handleOnJoinedPressed() {
        if (player.getPlayerCars().isEmpty()) {
            PopupManager.showWarning("No Cars Purchased", "You must have at least one car!");
        } else {

            player.setSelectedCar(player.getPlayerCars().getFirst());

            NPC champion = AllNPCs.getChampion();

            NpcDialogue.showDialogue(champion, "1");
            SceneSwitcher.moveToMenu();
        }
    }
}
