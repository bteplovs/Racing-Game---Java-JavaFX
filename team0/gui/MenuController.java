package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import seng201.team0.services.MenuService;

/**
 * Controller for the Main Menu Screen.
 * This class is responsible for the interactions between the main screen
 * which are Race, Shop, and Garage.
 */
public class MenuController {

    /** ImageView displaying the currently selected vehicle in the menu. */
    @FXML
    private ImageView imageSelectedVehicle;

    /** Service class which handles the main logic for the Menu Screen. */
    private final MenuService menuService = new MenuService();

    /**
     * Initialises the Main Menu screen by displaying the currently selected player's car
     * and is called when the FXML scene is loaded.
     */
    public void initialize() { menuService.setCarOnDisplay(imageSelectedVehicle); }

    /**
     * Handles the mouse event if the "Race" button is pressed.
     * This transitions to the Race selection screen.
     */
    public void buttonToRacePressed() { menuService.handleButtonToRacePressed(); }

    /**
     * Handles the mouse event if the "Shop" button is pressed.
     * This transitions to the in game Shop screen.
     */
    public void buttonToShopPressed() { menuService.handleButtonToShopPressed(); }

    /**
     * Handles the mouse event if the "Garage" button is pressed.
     * This transitions to the Player's Garage screen.
     */
    public void buttonToGaragePressed() { menuService.handleButtonToGaragePressed(); }
}
