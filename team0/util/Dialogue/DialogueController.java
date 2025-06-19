package seng201.team0.util.Dialogue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import seng201.team0.models.NPCs.NPC;

/**
 * Manages dialogue interactions for NPCs, including typewriter effects and completion callbacks.
 */
public class DialogueController {

    @FXML private ImageView npcPortrait;

    @FXML private TextArea dialogueTextArea;

    @FXML private Button nextButton;

    private String[] dialogueLines;
    private int currentLineIndex = 0;

    private Timeline typeWriter;
    private int charIndex = 0;

    private Runnable onCompleteCallback;


    /**
     * Sets the dialogue lines and NPC portrait for the dialogue interaction.
     *
     * @param npc       The NPC object
     * @param lines     The array of dialogue lines to display.
     * @param onComplete A Runnable to execute once the dialogue is complete.
     */
    public void setDialogue(NPC npc, String[] lines, Runnable onComplete) {
        String npcPicture = npc.getPortraitPath();
        this.onCompleteCallback = onComplete;
        npcPortrait.setImage(new Image((String) npcPicture));
        this.dialogueLines = lines;
        this.currentLineIndex = 0;
        startTypeWriterEffect(lines[0]);
    }


    /**
     * Initializes the DialogueManager by setting up the action listener for the next button.
     */
    @FXML private void initialize() {
        nextButton.setOnAction(event -> handleNext());
    }

    /**
     * Handles the action for the next button, advancing dialogue or completing it.
     */
    private void handleNext() {
        if (typeWriter != null && typeWriter.getStatus() == Timeline.Status.RUNNING) {
            typeWriter.stop();
            dialogueTextArea.setText(dialogueLines[currentLineIndex]);
        } else {
            currentLineIndex++;
            if (currentLineIndex < dialogueLines.length) {
                startTypeWriterEffect(dialogueLines[currentLineIndex]);
            } else {
                onDialogueFinished();
            }
        }
    }

    /**
     * Starts the typewriter effect to display dialogue characters one by one.
     *
     * @param fullText The dialogue text to display.
     */
    private void startTypeWriterEffect(String fullText) {
        dialogueTextArea.clear();
        charIndex = 0;

        typeWriter = new Timeline(new KeyFrame(Duration.millis(35), event -> {
            if (charIndex < fullText.length()) {
                dialogueTextArea.appendText(String.valueOf(fullText.charAt(charIndex++)));
            } else {
                typeWriter.stop();
            }
        }));

        typeWriter.setCycleCount(Timeline.INDEFINITE);
        typeWriter.play();
    }

    /**
     * Handles the completion of the dialogue sequence and executes the callback.
     */
    private void onDialogueFinished()
    {
        nextButton.getScene().getWindow().hide();
        if (onCompleteCallback != null) {
            onCompleteCallback.run();
        }
    }
}