package seng201.team0.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import seng201.team0.util.Scene.SceneController;
import seng201.team0.util.Scene.SceneManager;

import java.io.IOException;

/**
 * The start point for the JavaFX application.
 * It creates and prepares the starting scene.
 */
public class MainWindow extends Application {

    /**
     * Starts the JavaFX application.
     * It loads the initial FXML scene which is the Main Menu scene.
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        SceneController sceneController = new SceneController(primaryStage);
        SceneManager.setSceneController(sceneController);
        sceneController.loadScene("/fxml/Main.fxml");
    }
    /**
     * A Java compatible wrapper which is used when the main method calls the class.
     */
    public static void launchWrapper(String[] args)
    {
        launch(args);
    }
}
