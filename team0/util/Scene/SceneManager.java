package seng201.team0.util.Scene;

/**
 * Manages the instance of the SceneController.
 */
public class SceneManager {

    private static SceneController sceneController;

    public static void setSceneController(SceneController controller) {
        sceneController = controller;
    }

    public static SceneController getSceneController() {
        return sceneController;
    }
}