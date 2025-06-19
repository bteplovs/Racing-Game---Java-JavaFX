package seng201.team0.services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.NPCs.AllNPCs;
import seng201.team0.models.Game.Player;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneSwitcher;

/**
 * This class handles the initialization of Menu settings, and is the hub of the application handling navigation between most scenes
 * Also shows the player their selected cars
 */
public class MenuService {

    private Car carOnDisplay;
    private int noSelectedCarIndex = 0;
    private static final Player player = GameService.getInstance().getPlayer();

    /**
     * Handles the menu button being pressed by the user
     * Transitions the screen to the shop
     */
    public void handleButtonToRacePressed() {
        if (player.getSelectedCar() == null) {
            PopupManager.showInfo("Select a vehicle", "Select a vehicle in the garage before proceeding");
            return;
        }

        if (player.getSelectedCar().getCondition()) {
            PopupManager.showInfo("Repair your vehicle", "Repair your vehicle before proceeding");
            return;
        }

        GameService.getInstance().endGameCheck();

        SceneSwitcher.moveToRace();
        GameService.getInstance().setSelectedRace(null);
        GameService.getInstance().setSelectedRoute(null);
        AllNPCs.getRandomEncounter("Race");
    }

    /**
     * Handles the Shop button being pressed by the user
     * Transitions the screen to the shop
     */
    public void handleButtonToShopPressed() { SceneSwitcher.moveToStore(); AllNPCs.getRandomEncounter("Shop"); GameService.getInstance().bankruptCheck();}

    /**
     * Handles the Garage button being pressed by the user
     * Transitions the screen to the Garage
     */
    public void handleButtonToGaragePressed() { SceneSwitcher.moveToGarage(); AllNPCs.getRandomEncounter("Garage"); GameService.getInstance().bankruptCheck();}


    /**
     * Sets the ImageView with the Image the players selected car
     *
     * @param carImage The onscreen imageview
     */
    public void setCarOnDisplay(ImageView carImage) {

        if (player.getSelectedCar() != null) {
            Image image = new Image(player.getSelectedCar().getPath());
            carImage.setImage(image);
        } else {
            carOnDisplay = player.getPlayerCars().get(noSelectedCarIndex);
            Image image = new Image(carOnDisplay.getPath());
            carImage.setImage(image);
        }
    }
}
