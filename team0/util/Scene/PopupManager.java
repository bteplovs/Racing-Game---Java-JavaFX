package seng201.team0.util.Scene;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Manages the display of different types of pop-up alerts for user interactions in the game.
 * Includes information, warning, error, confirmation, and reward dialogs.
 */
public class PopupManager {

    /**
     * Displays an information pop-up with the given title and message.
     *
     * @param title   The title of the alert.
     * @param message The text message of the alert.
     */
    public static void showInfo(String title, String message) {
        showAlert(AlertType.INFORMATION, title, null, message);
    }

    /**
     * Displays a warning pop-up with the given title and message.
     *
     * @param title   The title of the alert.
     * @param message The text message of the alert.
     */
    public static void showWarning(String title, String message) {
        showAlert(AlertType.WARNING, title, null, message);
    }

    /**
     * Displays an error pop-up with the given title, header, and message.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param message The text message of the alert.
     */
    public static void showError(String title, String header, String message) {
        showAlert(AlertType.ERROR, title, header, message);
    }

    /**
     * Displays a generic alert with the given parameters.
     * This is used internally for all alert types.
     *
     * @param type    The type of the alert (information, warning, error).
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param message The text message of the alert.
     */
    private static void showAlert(AlertType type, String title, String header, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(message);

            alert.showAndWait();
        });
    }

    /**
     * Displays a confirmation pop-up with the given title, message, and button labels.
     * The method returns true if the user clicks the accept button, false if the decline button is clicked.
     *
     * @param title   The title of the alert.
     * @param message The content message of the alert.
     * @param accept  The text for the accept button.
     * @param decline The text for the decline button.
     * @return True if the accept button is clicked, false otherwise.
     */
    public static boolean showConfirmation(String title, String message, String accept, String decline) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType acceptButton = new ButtonType(accept);
        ButtonType declineButton = new ButtonType(decline);
        alert.getButtonTypes().setAll(acceptButton, declineButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == acceptButton;
    }

    /**
     * Displays a confirmation pop-up for a reward, including an image representing the reward.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param message The message of the alert.
     * @param accept  The text for the accept button.
     * @param decline The text for the decline button.
     * @param path    The file path for the reward image to display in the pop-up.
     * @return True if the accept button is clicked, false otherwise.
     */
    public static boolean showConfirmationReward(String title, String header, String message, String accept, String decline, String path) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Image customIcon = new Image(path);
        ImageView imageView = new ImageView(customIcon);
        imageView.setFitHeight(48);
        imageView.setFitWidth(48);

        alert.setGraphic(imageView);

        ButtonType acceptButton = new ButtonType(accept);
        ButtonType declineButton = new ButtonType(decline);
        alert.getButtonTypes().setAll(acceptButton, declineButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == acceptButton;
    }

    /**
     * Displays an information pop-up in the game with an optional action to perform after the alert is closed.
     *
     * @param title   The title of the alert.
     * @param message The message of the alert.
     * @param onClose The action to perform when the alert is closed.
     */
    public static void inGameInfo(String title, String message, Runnable onClose) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);

            alert.setOnHidden(e -> {
                if (onClose != null) {
                    onClose.run();
                }
            });

            alert.show();
        });
    }

    /**
     * Displays a confirmation pop-up in the game and performs an action based on the user's response.
     *
     * @param title   The title of the alert.
     * @param message The message of the alert.
     * @param accept  The text for the accept button.
     * @param decline The text for the decline button.
     * @param onResult The action to perform based on the result of the confirmation.
     */
    public static void showConfirmationInGame(String title, String message, String accept, String decline, Consumer<Boolean> onResult) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);

            ButtonType acceptButton = new ButtonType(accept);
            ButtonType declineButton = new ButtonType(decline);
            alert.getButtonTypes().setAll(acceptButton, declineButton);

            Optional<ButtonType> result = alert.showAndWait();
            boolean isAccepted = result.isPresent() && result.get() == acceptButton;

            if (onResult != null) {
                onResult.accept(isAccepted);
            }
        });
    }
}