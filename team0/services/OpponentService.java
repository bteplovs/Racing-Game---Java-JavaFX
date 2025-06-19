package seng201.team0.services;

import seng201.team0.models.Cars.AllCars;
import seng201.team0.models.Cars.Car;
import seng201.team0.models.Game.Opponent;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the initialization of Opponent settings, including generating opponents for each race.
 * and updating their status.
 */
public class OpponentService {

    private final List<Opponent> opponents;

    /**
     * Initializes an opponents array which holds a races randomly generated opponents
     *
     * @param numberOfOpponents A races entries defines the number of generated opponents
     */
    public OpponentService(int numberOfOpponents) {
        opponents = new ArrayList<>();
        generateOpponents(numberOfOpponents);
    }

    /**
     * Generates a list of random opponents, each player is ensured a random username out of a list of colours
     * by modular arith, and a random car out of all the games cars
     *
     * @param numberOfOpponents A races entries defines the number of generated opponents
     */
    private void generateOpponents(int numberOfOpponents) {
        String[] colors = {"Red", "Blue", "Green", "Orange", "Yellow", "Purple", "Black", "White", "Champion"};
        int colorIndex = 0;

        List<Car> allCars = AllCars.getCars();

        for (int i = 0; i < numberOfOpponents; i++) {
            String name = colors[colorIndex % colors.length];

            Car randomCar = allCars.get((int)(Math.random() * allCars.size()));

            opponents.add(new Opponent(name, randomCar));
            colorIndex++;
        }
    }

    /**
     * For all active opponents, updates an opponents status while racing with each passing tick.
     *
     * @param currentTick The current in game tick
     * @param routeDistance The distance of the selected route
     * @param fuelStationTicks The tick locations of each fuel station
     */
    public void updateOpponents(int currentTick, double routeDistance, List<Integer> fuelStationTicks) {
        for (Opponent opponent : opponents) { opponent.update(currentTick, routeDistance,fuelStationTicks); }
    }

    public List<Opponent> getOpponents() {
        return opponents;
    }
}
