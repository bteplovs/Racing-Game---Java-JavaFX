package seng201.team0.util.Scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng201.team0.models.NPCs.NPC;
import seng201.team0.util.Dialogue.DialogueController;

import java.io.IOException;
import java.util.Objects;

public class SceneController
{
    private final Stage primaryStage;

    /**
     * Constructs a SceneController with the primary game stage.
     *
     * @param primaryStage
     */
    public SceneController(Stage primaryStage) { this.primaryStage = primaryStage; }

    /***
     * Loads a new scene from an FXML file and sets it as the main stage scene
     *
     * @param fxmlFile
     */
    public void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/NPCs/Champion.png")));

            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("BIG MONEY RACER");
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Displays a dialogue popup for an NPC.
     * Executes the provided callback once the dialogue is finished
     *
     * @param npc The NPC initiating the dialogue.
     * @param lines The dialogue lines from an NPC's dialogue map.
     * @param onComplete Optional runnable which runs after function completion
     */
    public void showDialoguePopup(NPC npc, String[] lines, Runnable onComplete) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dialogue.fxml"));
            Parent root = loader.load();

            DialogueController controller = loader.getController();
            controller.setDialogue(npc, lines, onComplete);

            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Icons/DialogueBox.png")));

            Stage dialogStage = new Stage();
            dialogStage.getIcons().add(icon);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(root));
            dialogStage.setTitle(npc.getName());
            dialogStage.setResizable(false);
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
