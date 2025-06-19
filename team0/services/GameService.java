package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.models.Cars.AllCars;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.Game.Difficulty;
import seng201.team0.models.Game.Player;
import seng201.team0.models.Races.Race;
import seng201.team0.models.Races.Route;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneManager;
import seng201.team0.util.Scene.SceneSwitcher;

/**
 * This class handles the initialization of Game settings, including setting and getting the game instance, player instance
 * season length, difficulty, selected races and routes and checking end game conditions.
 * When getInstance is called a singleton game service is created and every call returns the same game service connecting the games logic.
 *
 */
public class GameService {

    private static GameService instance;
    private Player player;
    private int seasonLength;
    private Difficulty difficulty;

    private Race selectedRace;
    private Route selectedRoute;

    private GameService() {
        if (seasonLength == 0) {
            seasonLength = 5;
        }

        if (difficulty == null) {
            difficulty = Difficulty.MEDIUM;
        }

    }

    /**
     * Returns a single instance of game service
     *
     * @return Returns a single instance of game service
     */
    public static GameService getInstance() {
        if (instance == null) { instance = new GameService(); } return instance;}
    public Player getPlayer() { return player; }
    public void setPlayer(String name) { this.player = new Player(name); }
    public int getSeasonLength() { return seasonLength; }
    public void setSeasonLength(int seasonLength) { this.seasonLength = seasonLength; }
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
    public void setSelectedRace(Race race) { this.selectedRace = race; }
    public Race getSelectedRace() { return selectedRace; }
    public void setSelectedRoute(Route route) { this.selectedRoute = route; }
    public Route getSelectedRoute() { return selectedRoute; }


    /**
     * Sets the player
     *
     * @param player The player object
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Updates money labels across scenes
     *
     * @param label The on-screen Label
     */
    public void showMoney(Label label) { label.setText(player.getName() + "'s" + " Money: " + "$" + player.getMoney()); }

    /**
     * Checks end-game conditions, if the player has no more money to play, the game ends
     *
     */
    public void bankruptCheck() {
        double netWorth = player.getNetWorth();
        double money = player.getMoney();
        double availableFunds = netWorth + money;
        double minCarPrice = AllCars.getHorseAndWagon().getBuyPrice();
        boolean hasOneCar = player.getPlayerCars().size() == 1;
        double repairCost = 200;

        boolean allCarsBroken = player.getPlayerCars().stream().allMatch(Car::getCondition);

        if (allCarsBroken) {
            boolean canAffordRepair = player.getPlayerCars().stream()
                    .anyMatch(car -> availableFunds >= repairCost);

            if (!canAffordRepair) {
                PopupManager.showInfo("Season over", "All your cars are broken and you can't afford to repair any of them.");
                SceneManager.getSceneController().loadScene("/fxml/End.fxml");
            }

            if (availableFunds < minCarPrice) {
                PopupManager.showInfo("Season over", "All your cars are broken and you can't afford to buy a new one.");
                SceneManager.getSceneController().loadScene("/fxml/End.fxml");
            }
        }

        if (hasOneCar && player.getPlayerCars().getFirst().getCondition() && money < repairCost) {
            PopupManager.showInfo("Season over", "Your only car is broken and you can't afford to repair it.");
            SceneManager.getSceneController().loadScene("/fxml/End.fxml");
        }
    }

    /**
     * Checks end-game conditions, if the player has played through the
     * season length, the game ends
     *
     */
    public void endGameCheck() {

        if (player.getRacesPlayed() >= seasonLength) {
            PopupManager.inGameInfo("Season over", "The season is finished", SceneSwitcher::moveToEnd);
        }
    }
}