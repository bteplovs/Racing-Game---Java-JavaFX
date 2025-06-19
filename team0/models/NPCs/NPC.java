package seng201.team0.models.NPCs;
import seng201.team0.models.Game.Purchasable;

import java.util.Map;

/**
 * Represents a Non-Player Character (NPC) in the game.
 * NPCs can have multiple dialogue lines, a portrait, and a reward item.
 */
public class NPC {
    private final String name;
    private final Map<String, String[]> dialogueLines;
    private final String portraitPath;
    private final Purchasable reward;

    /**
     * Constructs a new NPC instance
     *
     * @param name The name of the NPC
     * @param dialogueLines The dialogue lines on the NPC
     * @param portraitPath The file path to the NPC's portrait image
     * @param reward The reward the NPC can provide the player
     */
    public NPC(String name, Map<String, String[]> dialogueLines, String portraitPath, Purchasable reward) {
        this.name = name;
        this.dialogueLines = dialogueLines;
        this.portraitPath = portraitPath;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }
    public Map<String, String[]> getDialogueLines() {
        return dialogueLines;
    }
    public String getPortraitPath() { return portraitPath; }
    public Purchasable getReward() { return reward; }
    public String[] getDialogue(String key) { return dialogueLines.get(key.toLowerCase()); }
}
