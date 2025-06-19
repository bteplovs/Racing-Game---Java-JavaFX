package seng201.team0.models.Races;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AllRoutes class contains hardcoded routes for the different race regions: Japan, America, and Europe, each route is represented by the route object
 */
public class  AllRoutes {

    private static final Route JAPAN_ROUTE_ONE = new Route("Sendai",  "People here love street food, narrow alleys, and aggressive corners. Some locals call it the proving ground.", "Maybe a car that can dance would be best here...", 110, "Hard", 2, Arrays.asList(40,90),0.9,1.15,0.75);
    private static final Route JAPAN_ROUTE_TWO = new Route("Tokyo", "Japans capital, it buzzes with neon lights, drifting legends, and underground racers listening to Tokyo Drift.", "Winners formula is inside the famous 'Tokyo Drift' song", 100, "Medium", 2, Arrays.asList(40,90),1.1,1.2,0.95);
    private static final Route JAPAN_ROUTE_THREE = new Route("Kyoto", "Mt. Fuji, Japan's iconic peak, a symbol of tranquility and strength.", "Going fast is not always the best option... You know what they say... Slow and steady wins the race", 80, "Easy", 2, Arrays.asList(40,60),1,1.1,0.9);

    private static final Route AMERICA_ROUTE_ONE = new Route("Florida", "Where the palm trees sway, the humidity hugs you like a wet blanket, and the headlines are as unpredictable as the afternoon thunderstorms.", "It's a long straight shot, just watch your fuel...", 150, "Medium", 1, Arrays.asList(30),0.9,0.8,1.1);
    private static final Route AMERICA_ROUTE_TWO = new Route("New Mexico", "A straight desert, who even decided to inhabit this?", "Don't forget to stock up on fuel... oh... and try not to get stuck in the sand!", 160, "Hard", 1, Arrays.asList(20),0.80,1.15,1.05);
    private static final Route AMERICA_ROUTE_THREE = new Route("Los Angeles", "From random celebrities and shining stars of LA to the whispering tall redwoods of north California throws, this track has everything.", "California, it's time to partyyyy!.. No but seriously, make sure everything is good :)", 170, "Easy", 3, Arrays.asList(10,30,40),1,1,1);

    private static final Route EUROPE_ROUTE_ONE = new Route("Russia", "Endless tundras, icy winds, and forgotten roads buried in snow. Only the ones with old Soviet Vodka survive Russia’s icy grip...", "Your tires may grip, but your soul needs to hold firm too. Fuel up, freeze less, and pack the right water, your soul needs to be kept warm.", 180, "Hard", 3, Arrays.asList(30,75,140),0.75,1.25,0.75);
    private static final Route EUROPE_ROUTE_TWO = new Route("Germany", "No speed limits, no mercy, at least some say... Just you, the asphalt, and the ghosts of racers from the past screaming down the Autobahn.", "I heard that only the slow ones leave with both tires and pride...", 160, "Medium", 2, Arrays.asList(75,140),1,1,1.1);
    private static final Route EUROPE_ROUTE_THREE = new Route("Slovakia", "Land of lakes, peaks, and peace. But don’t be fooled, some quiet roads can echo the loudest losses.", "A bit of balance is probably the best in this beautiful country", 177, "Easy", 4, Arrays.asList(20,50,110,140),1,1.1,0.95);

    /**
     * Returns a list of all routes associated with Japan
     *
     * @return Returns a list of all routes associated with Japan
     */
    public static List<Route> getRoutesJapan() {
        List<Route> routesJapan = new ArrayList<>();
        routesJapan.add(JAPAN_ROUTE_ONE);
        routesJapan.add(JAPAN_ROUTE_TWO);
        routesJapan.add(JAPAN_ROUTE_THREE);
        return routesJapan;
    }

    /**
     * Returns a list of all routes associated with America
     *
     * @return Returns a list of all routes associated with America
     */
    public static List<Route> getRoutesAmerica() {
        List<Route> routesAmerica = new ArrayList<>();
        routesAmerica.add(AMERICA_ROUTE_ONE);
        routesAmerica.add(AMERICA_ROUTE_TWO);
        routesAmerica.add(AMERICA_ROUTE_THREE);
        return routesAmerica;
    }

    /**
     * Returns a list of all routes associated with Europe
     *
     * @return Returns a list of all routes associated with Europe
     */
    public static List<Route> getRoutesEurope() {
        List<Route> routesEurope = new ArrayList<>();
        routesEurope.add(EUROPE_ROUTE_ONE);
        routesEurope.add(EUROPE_ROUTE_TWO);
        routesEurope.add(EUROPE_ROUTE_THREE);
        return routesEurope;
    }
}
