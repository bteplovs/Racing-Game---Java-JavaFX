package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import seng201.team0.services.MainService;

/**
 * Controller for the Main Menu Screen.
 * This class manages the interaction between the player and the Main Menu Screen.
 * It also delegates the main logic to the MainService class.
 */
public class MainController
{
    /**
     * ImageView showing the background image for the main menu.
     */
    @FXML
    private ImageView backgroundImage;

    /**
     * ImageView of the play button which reacts to the mouse event. *
     */
    @FXML
    private ImageView playImage;

    /**
    Service class which handles both interaction and visual aspects for the main menu.
     */
    private MainService mainService;

    /**
     * Initialises the main menu by setting up the default background image
     * and play button while also setting up the play buttons interactions.
     */
    public void initialize()
    {
        mainService = new MainService(backgroundImage, playImage);
    }

    /**
     * Trigger and called when the mouse hovers over the play button area.
     */
    @FXML
    public void OnMouseEnteredPlay() { mainService.handleMouseEnter(); }

    /**
     * Triggered when the mouse exits the play button area.
     */
    @FXML
    public void OnMouseExitedPlay() { mainService.handleMouseExit(); }

    /**
     * Called when the play button is pressed.
     */
    @FXML
    public void OnMousePressedPlay() { mainService.handleMousePress(); }

    /**
     * Called when the play button is released.
     */
    @FXML
    public void OnMouseReleasedPlay()
    {
        mainService.handleMouseRelease();
    }
}