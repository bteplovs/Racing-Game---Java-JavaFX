package seng201.team0.models.Races;

import java.net.InterfaceAddress;
import java.util.List;

/**
 * Represents a route in the game with attributes: name, description, distance, fuel stations, and various multipliers that affect gameplay mechanics
 */
public class Route {

    private final String name;
    private final String description;
    private final String tip;
    private final int distance;
    private final String difficulty;
    private final int fuelStations;
    private final List<Integer> fuelStationTicks;
    private final double fuelMultiplier;
    private final double reliabilityMultiplier;
    private final double speedMultiplier;

    /**
     * Constructs a new Route instance with the specified parameters
     *
     * @param name The name of the route
     * @param description The Description of the route
     * @param tip A tip for the player regarding the route
     * @param distance The distance of the route in KM
     * @param difficulty The routes difficulty
     * @param fuelStations The number of fuel stations on the route
     * @param fuelStationTicks The ticks at which the fuel station appear to the player
     * @param fuelMultiplier The multiplier affecting car fuel consumption
     * @param reliabilityMultiplier The multiplier affecting car reliability
     * @param speedMultiplier The multiplier affecting vehicle car speed
     */
    public Route(String name, String description, String tip, int distance, String difficulty, int fuelStations, List<Integer> fuelStationTicks, double fuelMultiplier, double reliabilityMultiplier, double speedMultiplier) {
        this.name = name;
        this.description = description;
        this.tip = tip;
        this.distance = distance;
        this.difficulty = difficulty;
        this.fuelStations = fuelStations;
        this.fuelStationTicks = fuelStationTicks;
        this.fuelMultiplier = fuelMultiplier;
        this.reliabilityMultiplier = reliabilityMultiplier;
        this.speedMultiplier = speedMultiplier;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getTip() { return tip; }
    public Integer getDistance() { return distance; }
    public String getDifficulty() { return difficulty; }
    public int getFuelStations() { return fuelStations; }
    public List<Integer> getFuelStationTicks() { return fuelStationTicks; }
    public double getFuelMultiplier() { return fuelMultiplier; }
    public double getReliabilityMultiplier() { return reliabilityMultiplier; }
    public double getSpeedMultiplier() { return speedMultiplier; }

    /**
     * Returns a formatted string repr of the route object
     * @return A string representing the routes key attributes
     */
    @Override
    public String toString() {
        return String.format(
                        "Name: %s\n" +
                        "Description: %s\n" +
                        "Tip: %s\n" +
                        "Distance: %d km\n" +
                        "Difficulty: %s\n" +
                        "Fuel Stops: %d",
                        name,
                        description,
                        tip,
                        distance,
                        difficulty,
                        fuelStations
        );
    }

}
