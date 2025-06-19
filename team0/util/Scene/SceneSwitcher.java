package seng201.team0.util.Scene;

import javafx.application.Platform;
import seng201.team0.models.NPCs.AllNPCs;
import seng201.team0.services.GameService;
import seng201.team0.util.Dialogue.NpcDialogue;

/**
 * Provides static methods to switch between different game scenes using the SceneController.
 */
public class SceneSwitcher {

    /**
     * Switches to the Start scene.
     */
    public static void movetoStart() { SceneManager.getSceneController().loadScene("/fxml/Start.fxml");}

    /**
     * Switches to the Pick car scene.
     */
    public static void moveToPickCar() {
        SceneManager.getSceneController().loadScene("/fxml/PickCar.fxml");
    }

    /**
     * Switches to the Menu scene.
     */
    public static void moveToMenu() {
        SceneManager.getSceneController().loadScene("/fxml/Menu.fxml"); }

    /**
     * Switches to the Shop scene.
     */
    public static void moveToStore() { SceneManager.getSceneController().loadScene("/fxml/Shop.fxml"); }

    /**
     * Switches to the Garage scene.
     */
    public static void moveToGarage() { SceneManager.getSceneController().loadScene("/fxml/Garage.fxml"); }

    /**
     * Switches to the Race scene.
     */
    public static void moveToRace() {
        SceneManager.getSceneController().loadScene("/fxml/Race.fxml"); }

    /**
     * Switches to the Europe route scene.
     */
    public static void moveToEurope() {
        SceneManager.getSceneController().loadScene("/fxml/Europe.fxml");
    }

    /**
     * Switches to the Japan route scene.
     */
    public static void moveToJapan() {
        SceneManager.getSceneController().loadScene("/fxml/Japan.fxml");
    }

    /**
     * Switches to the America route scene.
     */
    public static void moveToAmerica() {
        SceneManager.getSceneController().loadScene("/fxml/America.fxml");
    }

    /**
     * Switches to the Game scene.
     */
    public static void moveToGame() {
        SceneManager.getSceneController().loadScene("/fxml/Game.fxml");
    }

    /**
     * Switches to the End scene if the season is over, else to menu. And plays goodbye from champion!
     */
    public static void moveToEnd() {
        if (GameService.getInstance().getPlayer().getRacesPlayed() >= GameService.getInstance().getSeasonLength()) {
            SceneManager.getSceneController().loadScene("/fxml/End.fxml");
            NpcDialogue.showDialogue(AllNPCs.getChampion(), "2");
        } else {
            SceneManager.getSceneController().loadScene("/fxml/Menu.fxml");
        }
    }

    /**
     * Closes the application
     */
    public static void quit() { Platform.exit(); }

}
