package seng201.team0.services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.util.Scene.SceneManager;
import seng201.team0.util.Scene.SceneSwitcher;

import java.util.Objects;

/**
 * This class handles the initialization of Main screen features.
 */
public class MainService {
    private final Image greenLight;
    private final Image redLight;
    private final Image playYellow;
    private final Image playBlue;

    private final ImageView backgroundImage;
    private final ImageView playImage;

    /**
     * Initializes the background screen and main button for applications entry
     * @param backgroundImage The background imageview of the scene
     * @param playImage The 'play button' Image view
     */
    public MainService(ImageView backgroundImage, ImageView playImage) {
        this.backgroundImage = backgroundImage;
        this.playImage = playImage;

        greenLight = load("/Assets/MainFX/GreenLight.gif");
        redLight = load("/Assets/MainFX/RedLight.gif");
        playYellow = load("/Assets/MainFX/PlayYellow.png");
        playBlue = load("/Assets/MainFX/PlayBlue.png");
    }

    /**
     * Loads and returns a String url path into an image
     * @param path A path url to an image
     */
    private Image load(String path) {
        return new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
    }

    /**
     * Once entered change the image to convey interactivity to the user
     */
    public void handleMouseEnter() {
        backgroundImage.setImage(greenLight);
        playImage.setImage(playYellow);
    }

    /**
     * Once entered change the image to convey interactivity to the user
     */
    public void handleMouseExit() {
        backgroundImage.setImage(redLight);
        playImage.setImage(playBlue);
    }

    public void handleMousePress() {}

    /**
     * Handles the 'play' button being pressed by the user. Transitions the screen to the start screen
     */
    public void handleMouseRelease() {
        SceneSwitcher.movetoStart();
    }
}