package seng201.team0.models.Races;

/**
 * AllRaces class contains hardcoded races for the different race regions: Japan, America, and Europe, each Race is represented by the race object
 */
public class AllRaces {

    private static final Race RACE_JAPAN = new Race("Japan Derby",5,3,3700f, AllRoutes.getRoutesJapan());
    private static final Race RACE_AMERICA = new Race("American 500",5, 3,4300f, AllRoutes.getRoutesAmerica());
    private static final Race RACE_EUROPE = new Race("Europa Special", 6,3,4200f, AllRoutes.getRoutesEurope());

    public static Race getJapan() {
        return RACE_JAPAN;
    }
    public static Race getAmerica() {
        return RACE_AMERICA;
    }
    public static Race getEurope() {
        return RACE_EUROPE;
    }
}
