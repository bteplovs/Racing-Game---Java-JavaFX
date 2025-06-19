package seng201.team0.util.Dialogue;

import seng201.team0.models.NPCs.NPC;
import seng201.team0.util.Scene.SceneManager;


/**
 * Manages NPC interactions that provide rewards to the player.
 */
public class NpcDialogue {

    /**
     * Displays dialogue for an NPC without any reward.
     *
     * @param npc        The NPC initiating the dialogue.
     * @param dialogueKey The key to retrieve the dialogue lines from an NPC's dialogue map.
     */
    public static void showDialogue(NPC npc, String dialogueKey) {
        SceneManager.getSceneController().showDialoguePopup( npc, npc.getDialogue(dialogueKey), null);
    }
}
