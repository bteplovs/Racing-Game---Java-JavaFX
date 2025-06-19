package seng201.team0.services;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.Parts.Part;
import seng201.team0.models.Game.Player;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneManager;

import java.util.ArrayList;

/**
 * This class handles the initialization and management of Garage settings, including
 * car selection, part management, and garage ui updates
 */
public class GarageService {

    private final Player player = GameService.getInstance().getPlayer();

    /**
     * Sets the default car for the player if no car has been selected.
     */
    public void selectDefaultCar() {
        if (player.getSelectedCar() == null) {
            player.setSelectedCar(player.getPlayerCars().getFirst());
        }
    }

    /**
     * Updates the list of parts displayed for the given car.
     *
     * @param parts The ListView to display the car's parts.
     * @param car   The car whose parts are being displayed.
     */
    public void updateCarParts(ListView<Part> parts, Car car) {
        parts.getItems().clear();
        parts.getItems().addAll(car.getAllParts());
    }

    /**
     * Updates the car stats and image.
     *
     * @param stats The onscreen text-area to display the selected cars stats.
     * @param car   The car selected car
     * @param image The onscreen ImageView to display the car.
     */
    public void updateCarStats(TextArea stats, Car car, ImageView image) {
        stats.setText(car.carFormat());
        image.setImage(new Image(car.getPath()));
    }

    /**
     * Handles the menu button being pressed by the user. Transitions the screen to the main menu
     */
    public static void handleButtonToMenuPressed() {
        SceneManager.getSceneController().loadScene("/fxml/Menu.fxml");
    }


    /**
     * Handles the car selection process, including repair or selling
     *
     *
     * @param cars The on-screen listview holding the player cars
     * @param selectedParts The on-screen listview holding the cars equipped parts
     * @param parts The onscreen listview holding the players parts
     * @param image The onscreen imageview of the players selected car
     * @param stats The onscreen textarea displaying the selected cars stats
     * @param label The onscreen label displaying the players money
     * @param labelSelectedCar The onscreen label displaying the selected cars name
     */
    public void handleCarSelection(ListView<Car> cars, ListView<Part> selectedParts, ListView<Part> parts, ImageView image, TextArea stats, Label label, Label labelSelectedCar) {
        if (cars.getItems().isEmpty()) {
            return;
        }

        Car selectedCarFromList = cars.getSelectionModel().getSelectedItem();

        if (selectedCarFromList == null) {
            return;
        }

        if (selectedCarFromList != null && selectedCarFromList.getCondition()) {
            boolean accept = PopupManager.showConfirmation(selectedCarFromList.getName(),
                    "Repair or Sell " + selectedCarFromList.getName() + "for $200", "repair", "cancel");

            if (accept) {
                if (player.getMoney() < 200) {
                    PopupManager.showWarning("Insufficient Funds", "You don't have enough money.");
                } else {
                    player.setMoney(player.getMoney() - 200);
                    PopupManager.showInfo("Car Repaired", "You have repaired " + selectedCarFromList.getName() + ".");
                    GameService.getInstance().showMoney(label);
                    selectedCarFromList.setCondition(false);
                    System.out.println("Car repaired, condition set to: " + selectedCarFromList.getCondition());
                }
            } else {
                GameService.getInstance().endGameCheck();
            }
        } else {
            boolean accept = PopupManager.showConfirmation(selectedCarFromList.getName(),
                    "Select or Sell " + selectedCarFromList.getName() + " with equipped upgrades for " +
                            selectedCarFromList.getTotalCarValue(), "Select", "Sell");

            if (accept) {

                // Player
                player.setSelectedCar(selectedCarFromList);
                updateCarParts(selectedParts, player.getSelectedCar());
                updateCarStats(stats, player.getSelectedCar(), image);
                updateLabel(labelSelectedCar);

            } else {
                if (player.getPlayerCars().size() == 1) {
                    PopupManager.showWarning("You cannot have less than 1 car", "Player must have at least one car");
                } else {

                    //Player
                    player.setMoney(player.getMoney() + selectedCarFromList.getTotalCarValue());
                    GameService.getInstance().showMoney(label);
                    selectedParts.getItems().clear();

                    // Remove all parts from car
                    for (Part part : new ArrayList<>(selectedCarFromList.getAllParts())) {
                        selectedCarFromList.getAllParts().remove(part);
                        player.getPlayerParts().remove(part);
                        parts.getItems().remove(part);
                    }

                    // GUI
                    cars.getItems().remove(selectedCarFromList);

                    // Player
                    player.getPlayerCars().remove(selectedCarFromList);
                    image.setImage(null);
                    stats.clear();
                    player.setSelectedCar(null);
                    selectDefaultCar();
                }
            }
        }
    }

    /**
     * Handles the selection or selling of car parts
     *
     * @param selectedParts The on-screen listview holding the cars equipped parts
     * @param playerParts The on-screen listview holding the players parts
     * @param label The onscreen label displaying the players money
     * @param image The onscreen imageview of the players selected car
     * @param stats The onscreen textarea displaying the selected cars stats
     */
    public void handleCarPartSelection(ListView<Part> selectedParts,ListView<Part> playerParts, Label label, ImageView image, TextArea stats) {
        if (selectedParts.getItems().isEmpty()) {
            return;
        }

        Part selectedPart = selectedParts.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            return;
        }

        boolean accept = PopupManager.showConfirmation(selectedPart.getName(),
                "Remove or sell " + selectedPart.getName() + " for " +
                        selectedPart.getSellPrice(),"Remove", "Sell" );

        if (accept) {

            // GUI
            selectedParts.getItems().remove(selectedPart);

            // Player
            playerParts.getItems().add(selectedPart);
            player.addPart(selectedPart);


            // Car
            player.getSelectedCar().removeUpgrade(selectedPart);
            updateCarStats(stats, player.getSelectedCar(), image);


        } else {

            // Player
            player.setMoney(player.getMoney() + selectedPart.getSellPrice());
            GameService.getInstance().showMoney(label);

            //GUI
            selectedParts.getItems().remove(selectedPart);
            playerParts.getItems().remove(selectedPart);


            player.getSelectedCar().removeUpgrade(selectedPart);
        }
        updateCarStats(stats, player.getSelectedCar(), image);
    }


    /**
     * Handles the selection or selling of player parts
     *
     * @param selectedParts The on-screen listview holding the cars equipped parts
     * @param playerParts The on-screen listview holding the players parts
     * @param label The onscreen label displaying the players money
     * @param image The onscreen imageview of the players selected car
     * @param stats The onscreen textarea displaying the selected cars stats
     */
    public void handlePlayerPartSelection(ListView<Part> selectedParts,ListView<Part> playerParts, Label label, ImageView image, TextArea stats) {
        if (playerParts.getItems().isEmpty()) {
            return;
        }

        Part playerPart = playerParts.getSelectionModel().getSelectedItem();

        if (playerPart == null) {
            return;
        }

        boolean accept = PopupManager.showConfirmation(playerPart.getName(),
                "Equip or sell " + playerPart.getName() + " for " +
                        playerPart.getSellPrice(), "Equip", "Sell" );

        if (accept) {

            // Player
            playerParts.getItems().remove(playerPart);
            player.getPlayerParts().remove(playerPart);

            // Car
            selectedParts.getItems().add(playerPart);
            player.getSelectedCar().addUpgrade(playerPart);
            updateCarStats(stats, player.getSelectedCar(), image);


        } else {

            // Player
            player.setMoney(player.getMoney() + playerPart.getSellPrice());
            GameService.getInstance().showMoney(label);

            // GUI
            playerParts.getItems().remove(playerPart);
            player.getPlayerParts().remove(playerPart);
        }
    }

    /**
     * Updates the label with the name of the selected car or a default message if no car is selected.
     *
     * @param label The onscreen label to update.
     */
    public void updateLabel(Label label) {
        if (player.getSelectedCar() != null) {
            label.setText(player.getSelectedCar().getName() + "'s" +" equipped parts");
        } else {
            label.setText("No Car Selected");
        }
    }

}
