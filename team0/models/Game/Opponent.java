package seng201.team0.models.Game;

import seng201.team0.models.Cars.AllCars;
import seng201.team0.models.Cars.Car;
import seng201.team0.services.GameService;

import java.util.List;
import java.util.Random;

/**
 * This class represents a single AI opponent in the game.
 * Each opponent has a car, fuel, and progress tracking which are also
 * influenced by the games difficulty setting.
 */
public class Opponent implements Racer {

    private String name;
    private double distanceCovered;
    private double fuel;
    private int finishTime;
    private Car car;
    private int timePenalty;
    private Double difficulty;


    /**
     * Constructs a new opponent with a given name and car.
     * It also initialises the fuel randomly between 60-100 inclusive
     * and applies the difficulty multiplier to the opponent.
     *
     * @param name The name of the opponent
     * @param car The car of the opponent
     */
    public Opponent(String name, Car car) {
        this.name = name;
        this.distanceCovered = 0;
        this.fuel = 60 + new Random().nextInt(41);
        this.finishTime = -1;
        this.car = car;
        this.timePenalty = 0;
        this.difficulty = GameService.getInstance().getDifficulty().getMultiplier();
    }


    /** @return finish time in tick or -1 if the opponent did not finish. */
    public int getFinishTime() {
        return finishTime;
    }
    /** @return total penalty time in ticks. */
    @Override
    public int getTimePenalty() {
        return timePenalty;
    }
    /** @return the car assigned to the opponent. */

    @Override
    public Car getCar() {
        return car;
    }

    /** @return total distance covered by the opponent so far. */
    @Override
    public double getDistanceCovered() {
        return distanceCovered;
    }
    /** @return Opponents name. */
    @Override
    public String getName() {
        return name;
    }
    /** @return opponent's name. */
    @Override
    public double getFuel() {return fuel;}


    /** Sets the opponents time to what it was when they finished the race. */
    @Override
    public void setFinishTime(int finishTime) { this.finishTime = finishTime; }
    /** Implemented inorder to meet the requirements. */
    @Override
    public void setTimePenalty(int timePenalty) {}
    /** Updates the distance covered by the opponent. */
    @Override
    public void setDistanceCovered(double distance) {
        distanceCovered = distance;
    }


    /**
     * This function is called every tick in order to update the opponents
     * fuel consumption, refueling chance, and finish conditions.
     */
    public void update(int currentTick, double routeDistance, List<Integer> fuelStationTicks) {
        if (finishTime == -1 && fuel > 0 && distanceCovered < routeDistance) {
            double speedMultiplier = ((double) car.getSpeed() / 100) + 1 * this.difficulty;

            distanceCovered += speedMultiplier;

            // Fuel consumption logic
            if (fuel <= 0) {
                fuel = 0;
                finishTime = (int) routeDistance;
            } else {
                if (getCar().equals(AllCars.getHorseAndWagon())) {
                    fuel = 100;

                } else {
                    fuel -= (1.0 / car.getFuelEconomy()) + 1;
                }
            }

            // Refueling logic 50/50
            if (fuelStationTicks.contains(currentTick)) {
                Random rand = new Random();
                int chance = rand.nextInt(2);
                if (chance == 0) {
                    refuel();
                }
            }

            // Finish condition for finishing the race
            if (distanceCovered >= routeDistance) {
                distanceCovered = routeDistance;
                finishTime = currentTick;
            }
        }
    }

    /**
     * Refuels the opponents car and adds a time penalty if the chance for them to refuel is hit.
     */
    public void refuel() {
        this.fuel = 100.0;
        timePenalty += 10;
    }
}
