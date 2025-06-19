package seng201.team0.services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.NPCs.AllNPCs;
import seng201.team0.models.Races.AllRaces;
import seng201.team0.util.Dialogue.NpcDialogue;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneManager;
import seng201.team0.util.Scene.SceneSwitcher;

import java.util.Random;

/**
 * This class handles the user/ui interactions, including changing the colour of buttons when entered and exited and moving between scenes
 */
public class RaceService {

    /**
     * Once entered change the image to convey interactivity to the user
     *
     * @param image The onscreen imageview of the button
     */
    public static void ChangeColourYellow(ImageView image) { image.setImage(new Image("/Assets/GarageFX/PinYellow.png"));}

    /**
     * Once entered change the image to convey interactivity to the user
     *
     * @param image The onscreen imageview of the button
     */
     public static void ChangeColourRed(ImageView image) {
        image.setImage(new Image("/Assets/GarageFX/PinRed.png"));
     }

    /**
     * Handles the menu button being pressed by the user. Transitions the screen to the main menu
     */
     public static void handleButtonToMenuPressed() {
        SceneManager.getSceneController().loadScene("/fxml/Menu.fxml");
     }

     public static void handleButtonToSelectedRacePressed() {
         if (GameService.getInstance().getSelectedRace() == null) {
             PopupManager.showError("Select a Race", null, "Please select a Race");
         } else {
             if (GameService.getInstance().getSelectedRace().equals(AllRaces.getAmerica())) {

                 Random random = new Random();
                 double randomDouble = random.nextDouble(1);
                 if (randomDouble < 0.6) { NpcDialogue.showDialogue(AllNPCs.getEagleNPC(), "race"); }

                 SceneSwitcher.moveToAmerica();

             } else if (GameService.getInstance().getSelectedRace().equals(AllRaces.getEurope())) {

                 Random random = new Random();
                 double randomDouble = random.nextDouble(1);
                 if (randomDouble < 0.6) { NpcDialogue.showDialogue(AllNPCs.getMedvedNPC(), "race"); }

                 SceneSwitcher.moveToEurope();

             } else if (GameService.getInstance().getSelectedRace().equals(AllRaces.getJapan())) {

                 Random random = new Random();
                 double randomDouble = random.nextDouble(1);
                 if (randomDouble < 0.6) { NpcDialogue.showDialogue(AllNPCs.getNekoNPC(), "race"); }

                 SceneSwitcher.moveToJapan();

             }
         }
     }
}
