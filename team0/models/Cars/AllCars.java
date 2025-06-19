package seng201.team0.models.Cars;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a utility class which holds all the predefined instances
 * of the available cars in the game.
 */
public class AllCars {

    // Unique instances of each car
    private static final Car HORSE_AND_WAGON = new Car(
            "Horse and Wagon",
            "At least you will save on fuel :)",
            6,
            50,
            30,
            100,
            150,
            "/Assets/Cars/Horse.png");

    private static final Car RED_RACER = new Car(
            "Red Racer",
            "A classic among the racers",
            50,
            50,
            50,
            50,
            1500,
            "/Assets/Cars/Red.png");

    private static final Car DRIFT_PHANTOM = new Car(
            "Drift Phantom V4",
            "Built for drifting, baby!",
            70,
            90,
            60,
            35,
            5500,
            "/Assets/Cars/Sport.png");

    private static final Car CHILL_TURTLE = new Car(
            "Speedy Snail",
            "Fast at the start, but not for long...",
            50,
            60,
            70,
            15,
            2200,
            "/Assets/Cars/Tank.png");

    private static final Car PINK_CAR = new Car(
            "Bubblegum Racer",
            "What color is it?. Anyway it is sweet on the outside, but fierce on the track.",
            45,
            60,
            60,
            38,
            2500,
            "/Assets/Cars/Pink.png");

    private static final Car CRAB_XL = new Car(
            "Burger XL",
            "I swear I've seen this before... anyways, this handles like a crab—sideways!",
            40,
            40,
            90,
            20,
            2222,
            "/Assets/Cars/Burger.png");

    private static final Car CAT_BUS = new Car(
            "Whiskers Uncle",
            "*MEOW*",
            60,
            95,
            50,
            50,
            4550,
            "/Assets/Cars/Bus.png");

    private static final Car TISLA_READSTER = new Car(
            "Tisla Readster",
            "It reminds me of a car I've seen before...",
            80,
            80,
            60,
            60,
            7700,
            "/Assets/Cars/Speeder.png");

    private static final Car TOY_YODA = new Car(
            "Toy-Yoda Corona",
            "Reliable and economical since November 1966!",
            50,
            50,
            80,
            70,
            3000,
            "/Assets/Cars/Rust.png");

    private static final Car VAN = new Car(
            "Ze Van Banan",
            "Some say it's a classic, others say it's just a van, what do you think?",
            55,
            75,
            79,
            35,
            3500,
            "/Assets/Cars/Van.png");

    private static final Car CRYSTAL_MOVER = new Car(
            "Crystal Mover",
            "An old RV with secrets in the vents, slow on the outside, explosive on the inside.",
            45,
            50,
            65,
            30,
            1400,
            "/Assets/Cars/Breaking.png");

    private static final Car CLUES_FROM_BLUES = new Car(
            "Clues From Blues",
            "If you look under the seats you might find a clue about what is really going on...",
            35,
            60,
            45,
            65,
            1000,
            "/Assets/Cars/Blue.png");

    private static final Car GREEN_MACHINE = new Car(
            "Green Machine",
            "Won all of 2077 ECO-Friendly awards Including the UN's Champions of the Earth award, " +
                    " International Sustainability Awards, and the Green Product Award.",
            27,
            35,
            50,
            100,
            1000,
            "/Assets/Cars/Green.png");

    public static Car getRedRacer() {
        return RED_RACER;
    }

    public static Car getHorseAndWagon() {
        return HORSE_AND_WAGON;
    }

    public static Car getDriftPhantom() {
        return DRIFT_PHANTOM;
    }

    public static Car getChillTurtle() {
        return CHILL_TURTLE;
    }

    public static Car getPinkCar() {
        return PINK_CAR;
    }

    public static Car getCrabXL() {
        return CRAB_XL;
    }

    public static Car getCatBus() {
        return CAT_BUS;
    }

    public static Car getTislaReadster() {
        return TISLA_READSTER;
    }

    public static Car getToyYoda() {
        return TOY_YODA;
    }

    public static Car getVan() {
        return VAN;
    }

    public static Car getCrystalMover() {return CRYSTAL_MOVER;}

    public static Car getCluesFromBlues() {return CLUES_FROM_BLUES;}

    public static Car getGreenMachine() {return GREEN_MACHINE;}

    /**
     * @return all the cars which are available in the shop
     */
    public static List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(HORSE_AND_WAGON);
        cars.add(DRIFT_PHANTOM);
        cars.add(CHILL_TURTLE);
        cars.add(CRAB_XL);
        cars.add(CAT_BUS);
        cars.add(TISLA_READSTER);
        cars.add(TOY_YODA);
        cars.add(VAN);
        cars.add(CRYSTAL_MOVER);
        cars.add(CAT_BUS);
        cars.add(RED_RACER);
        return cars;
    }
    /**
     * @return all the available starting cars
     */
    public static List<Car> getDefaultStarterCars() {
        List<Car> defaultCars = new ArrayList<>();
        defaultCars.add(CLUES_FROM_BLUES);
        defaultCars.add(PINK_CAR);
        defaultCars.add(GREEN_MACHINE);
        return defaultCars;
    }
}
