package seng201.team0.services;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import seng201.team0.gui.GameController;
import seng201.team0.models.Cars.AllCars;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.Events.AllEvents;
import seng201.team0.models.Events.Event;
import seng201.team0.models.Parts.AllParts;
import seng201.team0.models.Parts.Part;
import seng201.team0.models.Game.Player;
import seng201.team0.models.Game.Racer;
import seng201.team0.models.Races.Race;
import seng201.team0.models.Races.Route;
import seng201.team0.util.Scene.PopupManager;
import seng201.team0.util.Scene.SceneSwitcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * This class handles the initialization and management of Gameplay settings, including race progression, event handling,
 * updating the UI. It uses a JavaFX timeline to manage ticks for the race handle events
 */
public class GameplayService {

    private Timeline timeline;
    private int currentTick;
    private int durationTicks;
    private double currentSpeed;

    private Label moneyLabel;
    private Label timePenaltyLabel;
    private Label distanceLabel;
    private Label timeLabel;
    private ProgressBar distanceBar;

    private ListView<Label> leaderboardListView;

    private boolean isPaused;
    private boolean eventTriggered;
    private boolean raceEnded = false;
    private boolean playerFinished;
    private String playerStatusString;
    private boolean playerPlaced;
    private GameController controller;

    private boolean buttonWasClicked = false;
    private boolean reactionButtonActive = false;

    Player player = GameService.getInstance().getPlayer();
    private final OpponentService opponentService = new OpponentService(GameService.getInstance().getSelectedRace().getEntries());

    /**
     * initializes the HUD components with the given labels and progress bar.
     * @param distanceLabel The onscreen label displaying distance covered
     * @param timeLabel The onscreen label displaying the time/ticks
     * @param distanceBar The onscreen progress bar displaying player progress
     * @param labelPlayerMoney The onscreen label displaying the player money
     * @param labelTimePenalty The onscreen label displaying the players time penalties
     */
    public void initializeHud(Label distanceLabel, Label timeLabel, ProgressBar distanceBar, Label labelPlayerMoney, Label labelTimePenalty) {
        this.distanceLabel = distanceLabel;
        this.timeLabel = timeLabel;
        this.distanceBar = distanceBar;
        this.moneyLabel = labelPlayerMoney;
        this.timePenaltyLabel = labelTimePenalty;
    }

    /**
     * starts the race by setting initial values and starting the game loop
     *
     * @param car the players selected car
     * @param route the selected route
     * @param race the selected race
     * @param initialSpeed the initial speed of the car
     * @param gameController the gameController for managing UI elements
     * @param leaderboardBox The ListView displaying the leaderboard
     */
    public void startRace(Car car, Route route, Race race, double initialSpeed, GameController gameController, ListView<Label> leaderboardBox) {
        this.leaderboardListView = leaderboardBox;

        this.currentTick = 0;
        this.durationTicks = race.getDurationTicks();
        this.currentSpeed = initialSpeed;

        this.controller = gameController;

        isPaused = false;
        eventTriggered = false;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick(car, route, gameController)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Handles a single tick of the race. Updates the racer's progress, triggers events and updates the UI
     *
     * @param car the players selected car
     * @param route the selected route
     * @param gameController the game controller instance
     */
    private void tick(Car car, Route route, GameController gameController) {

        if (raceEnded) return;

        currentTick++;

        if (currentTick % 5 == 0 && !reactionButtonActive && !eventTriggered && !playerFinished) {
            triggerReactionChallenge();
        }
        // Events
        if (!eventTriggered) { checkEvents(); }
        if (!eventTriggered && route.getFuelStationTicks().contains(currentTick) && !playerFinished) {
            eventTriggered = true;
            timeline.pause();
            handleFuelUpEvent();
        }

        // Check if player has finished or is out of fuel

        if (player.getFuel() <= 0 && !playerFinished) {
            playerFinished = true;
            player.setFinishTime(currentTick);
            player.setFuel(0);
            player.setFinishTime(route.getDistance());
            PopupManager.inGameInfo("Out of Fuel", "You have run out of fuel!", this::resumeRace);
        }
        if (player.getDistanceCovered() >= route.getDistance() && !playerFinished) {
            playerFinished = true;
            playerPlaced = true;
            player.setFinishTime(currentTick);
            player.setDistanceCovered(route.getDistance());
        }

        if (!playerFinished) {
            if (!(player.getSelectedCar().equals(AllCars.getHorseAndWagon()))) {
                double speedMultiplier = ((double) car.getSpeed() / 100) + currentSpeed * GameService.getInstance().getSelectedRoute().getSpeedMultiplier();

                double baseFuelConsumption = ((double) (100 - player.getSelectedCar().getFuelEconomy()) / 100) * GameService.getInstance().getSelectedRoute().getFuelMultiplier();
                double fuelConsumption = (baseFuelConsumption * Math.pow(currentSpeed, 2) + 1);

                player.setDistanceCovered(player.getDistanceCovered() + speedMultiplier);
                player.setFuel(Math.max(0, player.getFuel() - fuelConsumption));
            } else {

                double speedMultiplier = ((double) car.getSpeed() / 100) + currentSpeed * GameService.getInstance().getSelectedRoute().getSpeedMultiplier();

                player.setDistanceCovered(player.getDistanceCovered() + speedMultiplier);
                player.setFuel(Math.max(0, 100));
            }
        }

        // Update HUD
        moneyLabel.setText(player.getName() + "'s Money: $" + player.getMoney());
        timePenaltyLabel.setText("Time Penalty: +" + player.getTimePenalty());
        distanceLabel.setText(String.format("Distance: %d / %d", (int) player.getDistanceCovered(), (int) route.getDistance()));
        timeLabel.setText(String.format("Time: %d / %d", currentTick, durationTicks));
        distanceBar.setProgress(player.getDistanceCovered() / route.getDistance());

        opponentService.updateOpponents(currentTick, route.getDistance(), route.getFuelStationTicks());
        updateLeaderboard();
        gameController.updateFuelImage(player.getFuel());

        // Check if all racers are done
        boolean allRacersDone = playerFinished &&
                opponentService.getOpponents().stream()
                        .allMatch(opponent -> opponent.getFuel() <= 0 || opponent.getDistanceCovered() >= route.getDistance());

        if (allRacersDone || currentTick >= durationTicks) {
            raceResult();
            endRace();
        }
    }

    /**
     * Adjusts the player's current speed.
     *
     * @param newSpeed The new speed value.
     */
    public void adjustSpeed(double newSpeed) {
        this.currentSpeed = newSpeed;
    }

    /**
     * Resumes the race after being paused.
     */
    private void resumeRace() {
        if (timeline != null) {
            isPaused = false;
            eventTriggered = false;
            timeline.play();
        }
    }

    /**
     * Ends the race, and resets necessary parameters
     */
    private void endRace() {
        if (timeline != null) { timeline.stop();}
        raceEnded = true;
        isPaused = false;
        eventTriggered = false;
        currentTick = 0;
        timeline = null;
        player.setFuel(100);
        player.setFinishTime(-1);
        playerFinished = false;
        player.setDistanceCovered(0);
        player.setTimePenalty(0);
        GameService.getInstance().getPlayer().setRacesPlayed();
        player.setPlacing(0);
        playerStatusString = "";

        for (Part part : AllParts.getParts()) {
            part.setPartIsAvailable(true);
        }

    }

    /**
     * Checks for random events during the race
     *
     */
    private void checkEvents() {
        if (!isPaused && !raceEnded && !playerFinished) {
            Random rand = new Random();
            double chance = rand.nextDouble();

            if (chance < 0.2) {
                Event event = AllEvents.getEvents().get(rand.nextInt(AllEvents.getEvents().size()));
                if (event != null) {
                    if (event.occurs()) {
                        timeline.pause();
                        eventTriggered = true;

                        switch (event.getName()) {
                            case "Hitchhiker Encounter":
                                handleHitchhikerEvent();
                                break;
                            case "Severe Weather":
                                handleSevereWeatherEvent();
                                break;
                            case "Car Breakdown":
                                handleCarBreakdownEvent();
                                break;
                            case "A Strange Man":
                                handleAStrangeManEvent();
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Handles the hitchhiker event, offering the player a choice to accept a passenger for a cash reward.
     * The better the handling the more profit the player gets.
     */
    private void handleHitchhikerEvent() {
        if (playerFinished) {resumeRace(); return;}
        int cash = new Random().nextInt(200) + (500 * (player.getSelectedCar().getHandling() / 100));
        PopupManager.showConfirmationInGame(
                "Hitchhiker Encounter",
                "A hitchhiker is offering $"+ cash + " for a ride. Do you accept?",
                "Come in!",
                "Turn away",
                accepted -> {
                    if (!raceEnded && !playerFinished) {
                        if (accepted) {
                            player.setMoney(player.getMoney() + cash);
                            player.setTimePenalty(player.getTimePenalty() + 5);
                            PopupManager.inGameInfo("Info", "Hitchhiker accepted. You gain $" + cash + ".", this::resumeRace);
                        } else {
                            PopupManager.inGameInfo("Info", "Hitchhiker turned away.", this::resumeRace);
                        }
                    }
                }
        );
    }

    /**
     * Handles the Breakdown event, offering the player a choice to repair their car or concede the current race.
     */
    private void handleCarBreakdownEvent() {
        if (playerFinished) { resumeRace(); return;}

        PopupManager.showConfirmationInGame(
                "Car Breakdown",
                "Your car has broken down:\nRepair for $500 or concede the race.",
                "Repair for $500",
                "Concede the race",
                accepted -> {
                    if (!raceEnded && !playerFinished) {
                        if (accepted) {
                            if (player.getMoney() < 500) {
                                PopupManager.inGameInfo("Info", "Not enough money to repair.", this::resumeRace);
                                player.getSelectedCar().setCondition(true);
                                playerFinished = true;
                            } else {
                                player.setMoney(player.getMoney() - 500);
                                player.setTimePenalty(player.getTimePenalty() + 5);
                                PopupManager.inGameInfo("Info", "Your car has been repaired!", this::resumeRace);
                            }
                        } else {
                            PopupManager.inGameInfo("Info", "You have conceded the race.", this::resumeRace);
                            player.getSelectedCar().setCondition(true); // Broken
                            playerFinished = true;
                        }
                    }
                }
        );
    }

    /**
     * Handles the hitchhiker event, offering the player a choice to fuel up and lose time or continue racing.
     */
    private void handleFuelUpEvent() {
        if (playerFinished) { resumeRace(); return;}

        PopupManager.showConfirmationInGame(
                "Fuel Up",
                "Refuel your car for $300. Do you accept?",
                "Refuel for $300",
                "Cancel",
                accepted -> {
                    if (!raceEnded && !playerFinished) {
                        if (accepted) {
                            if (player.getMoney() < 300) {
                                PopupManager.inGameInfo("Info", "Not enough money to refuel.", this::resumeRace);
                            } else {
                                player.setTimePenalty(player.getTimePenalty() + 5);
                                player.setMoney(player.getMoney() - 300);
                                player.setFuel(100.0);
                                PopupManager.inGameInfo("Info", "Your car has been refueled!", this::resumeRace);
                            }
                        } else {
                            resumeRace();
                        }
                    }
                }
        );
    }

    /**
     * This method handles the Strange Man event where the player can accept or decline the ride request.
     * If accepted it will give the player $11 and a random part, while also adding penalty time.
     */
    private void handleAStrangeManEvent() {
        if (playerFinished) { resumeRace(); return;}

        int cash = 11;
        PopupManager.showConfirmationInGame(
                "A Strange Man",
                "A... Hitchhiker? is asking for a ride. Do you accept?",
                "Hey come on in!",
                "...",
                accepted -> {
                    if (!raceEnded && !playerFinished) {
                        if (accepted) {
                            player.setMoney(player.getMoney() + cash);
                            player.setTimePenalty(player.getTimePenalty() + 7);
                            List<Part> parts = AllParts.getParts();
                            Part randomPart = parts.get(new Random().nextInt(parts.size()));
                            player.addPart(randomPart);
                            PopupManager.inGameInfo("Info", "You earned $" + cash + " and received a part: " + randomPart.getName(), this::resumeRace);
                        } else {
                            PopupManager.inGameInfo("Info", "You turned him away...", this::resumeRace);
                        }
                    }
                }
        );
    }

    /**
     * Handles the severe weather event, ending the race.
     */
    private void handleSevereWeatherEvent() {
        PopupManager.inGameInfo(
                "Severe Weather",
                "Severe weather has hit! All vehicles have been forced to retired.",
                SceneSwitcher::moveToMenu );
        endRace();
        player.getSelectedCar().setCondition(true);
        SceneSwitcher.moveToMenu();
    }

    /**
     * Sets the leaderboard ListView and updates the leaderboard with data from the current tick.
     *
     * @param leaderboardListView The onscreen ListView that displays the leaderboard.
     */
    public void setLeaderboardBox(ListView<Label> leaderboardListView) { this.leaderboardListView = leaderboardListView; updateLeaderboard(); }

    /**
     * Updates the leaderboard ListViews entries with the current status of all racers.
     * Including racer name, distance covered, car name, fuel level, status and placing.
     */
    public void updateLeaderboard() {
        ObservableList<Label> leaderboardItems = FXCollections.observableArrayList();

        List<Racer> racers = new ArrayList<>(opponentService.getOpponents());
        racers.add(player);

        racers.sort(Comparator.comparingDouble(a -> (a.getFinishTime() + a.getTimePenalty())));

        int placing = 1;

        for (Racer racer : racers) {
            String placingText = "N/A";
            String statusText = "";
            String finishTimeText = "";
            String timePenaltyText = "";

            if (racer.getDistanceCovered() >= GameService.getInstance().getSelectedRoute().getDistance()) {

                if (racer == player && playerFinished) {
                    player.setPlacing(placing);
                }

                placingText = String.valueOf(placing);
                finishTimeText = " | Finish Time: " + racer.getFinishTime() + " + " + racer.getTimePenalty();
                placing++;
            }
            else if (racer.getFuel() <= 0) { statusText = "Out of Fuel!"; }
            else if (racer == player && player.getSelectedCar().getCondition()) { statusText = "Broken Down!"; }
            else { statusText = "Racing"; }

            Label label = new Label(racer.getName() + " | " +
                                    (int) racer.getDistanceCovered() + " km | " +
                                    racer.getCar().getName() + " | Fuel: " +
                                    (int) racer.getFuel() + " | Place: " + placingText +
                                    " | " + statusText + finishTimeText + timePenaltyText );
            leaderboardItems.add(label);
        }
        leaderboardListView.setItems(leaderboardItems);
    }

    /**
     * Determines the player's race result, updates earnings, and displays the result popup.
     */
    private void raceResult() {
        float winnings = 0;

        player.setPlacings(player.getPlacing());

        if (player.getPlacing() == 1) {
            winnings = (int) (GameService.getInstance().getSelectedRace().getPrizeMoney() / 1);
        }
        else if (player.getPlacing() == 2) {
            winnings = (int) (GameService.getInstance().getSelectedRace().getPrizeMoney() / 2);
        }
        else if (player.getPlacing() == 3) {
            winnings = (int) (GameService.getInstance().getSelectedRace().getPrizeMoney() / 3);
        }
        else if (player.getPlacing() == 4) {
            winnings = 300;
        }
        else if (player.getPlacing() == -1) {
            winnings = 0;
        }

        if (playerStatusString == null || playerStatusString.isEmpty()) {
            playerStatusString = "Race completed";
        }

        if (playerPlaced) {
            PopupManager.inGameInfo("Race Ended", player.getName()+ ":\n" + "You placed: " + player.getPlacing() + "\n" + "You have won: $" + (int) winnings, SceneSwitcher::moveToEnd);
        } else {
            PopupManager.inGameInfo("Race Ended", player.getName()+ ":\n" + "You did not place" + "\n" + "You have won: $" + (int) winnings, SceneSwitcher::moveToEnd);
        }

        player.setMoney(player.getMoney() + (int) winnings);
        player.setEarnedMoney(player.getEarnedMoney() + (int) winnings);

        GameService.getInstance().endGameCheck();
    }

    /**
     * Triggers a reaction challenge during the race.
     * If the player fails to react within the specified time, a time penalty is applied, otherwise updates the React counter
     * The time is also affected by the players current selected cars handling.
     */
    private void triggerReactionChallenge() {
        if (playerFinished || raceEnded) return;

        reactionButtonActive = true;
        buttonWasClicked = false;

        controller.showReactionButton();

        int handling = player.getSelectedCar().getHandling();
        double seconds = Math.max(1, 5 - (handling / 25.0));

        Timeline timeout = new Timeline(new KeyFrame(Duration.seconds(seconds), e -> {
            controller.hideReactionButton();
            reactionButtonActive = false;

            if (!buttonWasClicked) {
                player.setTimePenalty(player.getTimePenalty() + 1);
            } else {
                player.addButtonPressed();
            }
        }));
        timeout.play();
    }

    /**
     * Sets the state of the reaction button.
     *
     * @param active True to activate the reaction button, false to deactivate it.
     */
    public void setReactionButtonActive(boolean active) {
        this.reactionButtonActive = active;
    }

    /**
     * Sets the state indicating if reaction button was clicked.
     *
     * @param clicked True if the button was clicked, false otherwise.
     */
    public void setButtonWasClicked(boolean clicked) {
        this.buttonWasClicked = clicked;
    }

    /**
     * Returns whether the reaction button is currently active.
     * @return true if the reaction button is active, and false otherwise.
     */
    public boolean isReactionButtonActive() {
        return reactionButtonActive;
    }

    /**
     * Returns the current speed of the gameplay ticks
     * @return the current speed at which the game is running
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }
}
