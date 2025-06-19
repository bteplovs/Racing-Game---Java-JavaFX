package seng201.team0.models.Cars;

import seng201.team0.models.Parts.Part;
import seng201.team0.models.Parts.PartType;
import seng201.team0.models.Game.Purchasable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a car object in the game with base stats, parts, and dynamic pricing.
 */
public class Car implements Purchasable {
    private final String name;
    private final String description;
    private final int baseSpeed;
    private final int baseHandling;
    private final int baseReliability;
    private final int baseFuelEconomy;
    private final int cost;
    private final String imagePath;
    private boolean condition;

    private int speedUpgrades = 0;
    private int handlingUpgrades = 0;
    private int reliabilityUpgrades = 0;
    private int fuelEconomyUpgrades= 0;

    private final List<Part> parts;

    /**
     * Constructs the car object
     *
     *
     * @param name The name of the car
     * @param description The description of the car
     * @param baseSpeed The base speed of the car
     * @param baseHandling The base handling of the car
     * @param baseReliability The base reliability of the car
     * @param baseFuelEconomy The base Fuel economy of the car
     * @param cost The cost of the car
     * @param imagePath The image path of the car
     */
    public Car(String name, String description, int baseSpeed, int baseHandling, int baseReliability, int baseFuelEconomy, int cost, String imagePath) {
        this.name = name;
        this.description = String.valueOf(description);
        this.baseSpeed = baseSpeed;
        this.baseHandling = baseHandling;
        this.baseReliability = baseReliability;
        this.baseFuelEconomy = baseFuelEconomy;
        this.cost = cost;
        this.imagePath = imagePath;
        this.parts = new ArrayList<>();
        this.condition = false;
    }

    // All the getters for the car including name, cost, description, sell and buy price,
    // cars stats like fuel economy, reliability, speed and handling.
    public String getName() { return name; }

    @Override
    public int getBuyPrice() {
        return (int) cost;
    }

    @Override
    public int getSellPrice() {
        return (int) (getBuyPrice() * 0.7);
    }

    public String getDescription() { return description; }

    public int getSpeed() { return baseSpeed + speedUpgrades; }

    public int getHandling() { return baseHandling + handlingUpgrades; }

    public int getReliability() { return baseReliability + reliabilityUpgrades; }

    public int getFuelEconomy() { return baseFuelEconomy + fuelEconomyUpgrades; }

    public int getCost() { return cost; }

    public String getPath() { return imagePath; }

    public List<Part> getAllParts() { return parts; }

    public int getSpeedUpgrades() { return speedUpgrades; }

    public int getHandlingUpgrades() { return handlingUpgrades; }

    public int getReliabilityUpgrades() { return reliabilityUpgrades; }

    public int getFuelEconomyUpgrades() { return fuelEconomyUpgrades; }

    /** Gets the total sell cost of the car including its parts. */
    public int getTotalCarValue() {
        return getSellPrice() + costOfAllParts();
    }

    public void setSpeedUpgrades(int speedUpgrades) { this.speedUpgrades = speedUpgrades; }

    public void setHandlingUpgrades(int handlingUpgrades) { this.handlingUpgrades = handlingUpgrades; }

    public void setReliabilityUpgrades(int reliabilityUpgrades) { this.reliabilityUpgrades = reliabilityUpgrades; }

    public void setFuelEconomyUpgrades(int fuelEconomyUpgrades) { this.fuelEconomyUpgrades = fuelEconomyUpgrades; }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns a formated string with full car stats and upgrades.
     */
    public String carFormat() {
        return String.format("Car Name: %s\n" +
                            "Description: %s\n" +
                            "Speed: %d (Base: %d, Upgrades: %d)\n" +
                            "Handling: %d (Base: %d, Upgrades: %d)\n" +
                            "Reliability: %d (Base: %d, Upgrades: %d)\n" +
                            "Fuel Economy: %d (Base: %d, Upgrades: %d)\n" +
                            "Cost: $%.2f\n" +
                            "Sell Price: $%.2f\n" +
                            "Is Broken: %s",
                            name,
                            description,
                            getSpeed(), getSpeed(), getSpeedUpgrades(),
                            getHandling(), baseHandling, handlingUpgrades,
                            getReliability(), baseReliability, reliabilityUpgrades,
                            getFuelEconomy(), baseFuelEconomy, fuelEconomyUpgrades,
                            (double) cost,
                            (double) getTotalCarValue(),
                            condition);
    }

    /**
     * Adds the part to the car and updates the cars stats based on the upgrade.
     *
     * @param part A part object
     */
    public void addUpgrade(Part part) {
        PartType type = part.getType();
        switch (type) {
            case SPEED:
                setSpeedUpgrades(getSpeedUpgrades() + part.getStatBoost());
                break;
            case HANDLING:
                setHandlingUpgrades(getHandlingUpgrades() + part.getStatBoost());
                break;
            case RELIABILTY:
                setReliabilityUpgrades(getReliabilityUpgrades() + part.getStatBoost());
                break;
            case FUEL_ECONOMY:
                setFuelEconomyUpgrades(getFuelEconomyUpgrades() + part.getStatBoost());
                break;
        }
        parts.add(part);
    }

    /**
     *  Removes the part from the car and remove the upgrade based on what part it was.
     *
     * @param part A part object
     */
    public void removeUpgrade(Part part) {
        PartType type = part.getType();
        switch (type) {
            case SPEED:
                setSpeedUpgrades(getSpeedUpgrades() - part.getStatBoost());
                break;
            case HANDLING:
                setHandlingUpgrades(getHandlingUpgrades() - part.getStatBoost());
                break;
            case RELIABILTY:
                setReliabilityUpgrades(getReliabilityUpgrades() - part.getStatBoost());
                break;
            case FUEL_ECONOMY:
                setFuelEconomyUpgrades(getFuelEconomyUpgrades() - part.getStatBoost());
                break;
        }
        parts.remove(part);
    }


    /**
     * Returns the total value of parts on the car
     *
     * @return the total value of the parts installed on the car.
     */
    public int costOfAllParts() {
        int cost = 0;
        for (Part part : getAllParts()) {
            cost += part.getSellPrice();
        }
        return cost;
    }

    /**
     * Check if the car objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return this.name.equals(car.name);
    }

    /**
     * Sets the condition of the car after an event which breaks the vehicle
     *
     * @param condition broken if True, false otherwise
     */
    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public Boolean getCondition() {
        return condition;
    }

}