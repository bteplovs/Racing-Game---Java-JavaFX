package seng201.team0.services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.util.Scene.SceneManager;
import seng201.team0.util.Scene.SceneSwitcher;

/**
 * This class handles the user/ui interactions, including changing the colour of buttons when entered and exited and moving between scenes
 */
public class RouteService {

    /**
     * Once entered change the image to convey interactivity to the user
     *
     * @param image The onscreen imageview of the button
     */
     public static void ChangeColourYellow(ImageView image) { image.setImage(new Image("/Assets/GarageFX/PinYellow.png")); }


    /**
     * Once entered change the image to convey interactivity to the user
     *
     * @param image The onscreen imageview of the button
     */
     public static void ChangeColourRed(ImageView image) { image.setImage(new Image("/Assets/GarageFX/PinRed.png")); }


      /**
      * Handles the menu button being pressed by the user. Transitions the screen to the Race menu
      */
      public static void handleButtonToRacePressed() {
            SceneSwitcher.moveToRace();
        }

}
