package seng201.team0.models.Game;

import seng201.team0.models.Cars.Car;
import seng201.team0.models.Parts.Part;
import seng201.team0.services.GameService;
import seng201.team0.util.Scene.PopupManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class which represents the player in the game. It holds and manages the
 * players money, cars, parts, stats, and racing progress throughout the entire game.
 */
public class Player implements Racer {

    private String name;
    private Double money;
    private Double allMoney;
    private Car selectedCar;
    private int racesPlayed;

    private double distanceCovered;
    private double fuel;
    private int finishTime;

    public List<Part> selectedParts;
    private final List<Part> playerParts;
    private final List<Car> playerCars;
    private final List<Float> playerPlacings;
    private int timePenalty;
    private int placing;
    private float earnedMoney;
    private int buttonPressed;

    /**
     * Constructs a new Player with predetermined starting parameters.
     */
    public Player(String name) {
        this.name = name;
        this.money = 7500.0;
        this.racesPlayed = 0;
        this.fuel = 100.0;
        this.timePenalty = 0;
        this.finishTime = -1;
        this.placing = -1;
        this.earnedMoney = 0;

        this.playerPlacings = new ArrayList<>();
        this.selectedParts = new ArrayList<>();
        this.playerParts = new ArrayList<>();
        this.playerCars = new ArrayList<>();
    }

    /**
     * @return the player's name.
     */
    @Override
    public String getName() {return name;}
    /**
     * @return the player's current money.
     */
    public Double getMoney() { return money; }
    /**
     * @return the player's total money throughout the entire game.
     */
    public Double getAllMoney() { return allMoney; }
    /**
     * @return the player's car which is used for racing.
     */
    @Override
    public Car getCar() { return selectedCar; }
    /**
     * @return player's selected car.
     */
    public Car getSelectedCar() { return selectedCar;}
    /**
     * @return all the parts the player owns.
     */
    public List<Part> getPlayerParts() { return playerParts;}
    /**
     * @return all the cars the player owns.
     */
    public List<Car> getPlayerCars() { return playerCars;}
    /**
     * @return the number of races the player has played.
     */
    public int getRacesPlayed() { return racesPlayed; }
    /**
     * @return the player's time penalty accumulated in the race.
     */
    public int getTimePenalty() { return timePenalty; }

    /**
     * @return player's reward won from the race.
     */
    public float getEarnedMoney() {
        return earnedMoney;
    }
    /**
     * @return player's current placing in a race.
     */
    public int getPlacing() {
        return placing;
    }
    /**
     * @return the list of all previous placings the player has gotten.
     */
    public List<Float> getPlayerPlacings() { return playerPlacings; }

    public int getButtonPressed() {
        return buttonPressed;
    }

    @Override
    public double getDistanceCovered() { return distanceCovered; }
    @Override
    public void setDistanceCovered(double distance) { this.distanceCovered = distance; }
    @Override
    public double getFuel() { return fuel; }
    @Override
    public int getFinishTime() { return finishTime; }
    @Override
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }


    public void setName(String name) { this.name = name; }
    public void setMoney(Double money) { this.money = money; }

    /**
     * Increments the number of races played by the player.
     */
    public void setRacesPlayed() {
        if (racesPlayed >= GameService.getInstance().getSeasonLength()) {
            racesPlayed = GameService.getInstance().getSeasonLength();
        }else {this.racesPlayed ++;}
    }
    public void setSelectedCar(Car selectedCar) { this.selectedCar = selectedCar; }
    public void setTimePenalty(int time) {timePenalty = time;}
    public void setEarnedMoney(float earnedMoney) {
        this.earnedMoney = earnedMoney;
    }
    public void setFuel(double v) {
        fuel = v;
    }
    public void setPlacing(int placing) {
        this.placing = placing;
    }
    public void setPlacings(int placings) {
        playerPlacings.add((float) placings);
    }


    /**
     * Adds a car to the player's garage while displaying a randomised title of the car.
     */
    public void addCar(Car car) {
        playerCars.add(car);
        String[] prestigeTitles = {
                "legendary,",
                "prestigious,",
                "normal,",
                "Whiskers special,",
                "beast of a machine,",
                "hand crafted,",
                "remade version of the,",
                "brand new of the shelf,",
                "comfortable,",
                "eco-friendly version of the,",
                "Bogdan's lucky charm engineered,",
                "Aljaz's super special,",
                "Bibo's late and godly,,",
                "super Gosha's raw,",
                "Sebo's version of the,",
                "Undaground nettspent leak,"
        };

        Random rand = new Random();
        String randomTitle = prestigeTitles[rand.nextInt(prestigeTitles.length)];
        PopupManager.showInfo("New Car Acquired!",
                "You got the " + randomTitle + " " + car.getName() + "!");
    }

    /**
     * Adds a part to the player's inventory.
     */
    public void addPart(Part part) {
        playerParts.add(part);
    }
    public boolean ownsCar(Car car) {
        return this.getPlayerCars().contains(car);
    }

    public boolean tooManyCars() {
        if (playerCars.size() >= 5) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the player's total net worth which is based on cars and parts.
     */
    public float getNetWorth() {
        float netWorth = 0;
        for (Car car : playerCars) {
            netWorth += car.getSellPrice() + car.costOfAllParts();
        }
        for (Part part : playerParts) {
            netWorth += part.getSellPrice();
        }
        return netWorth;
    }

    public void addButtonPressed() {
        buttonPressed++;
    }
}
