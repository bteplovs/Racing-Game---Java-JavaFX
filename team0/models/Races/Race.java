package seng201.team0.models.Races;

import java.util.List;

/**
 * Represents a race in the game with attributes: name, duration in hours and ticks, entries, prize money, and list of routes
 *
 */
public class Race {

    private final String name;
    private final int durationHours;
    private final int entries;
    private final float prizeMoney;
    private final List<Route> routes;
    private final int durationTicks;
    private final int currentRouteCount;


    /**
     * Constructs a new Race instance with the specified parameters
     *
     * @param name The name of the race
     * @param durationHours The duration of the race in hours
     * @param entries The number of entries
     * @param prizeMoney The prize for completing this race
     * @param routes The routes of this race
     */
    public Race(String name, int durationHours, int entries, Float prizeMoney, List<Route> routes) {
        this.name = name;
        this.durationHours = durationHours;
        this.entries = entries;
        this.prizeMoney = prizeMoney;
        this.routes = routes;

        this.currentRouteCount = routes.size();
        this.durationTicks = durationHours * 60;
    }

    public String getRaceInfo() {
        return String.format("Name: %s\n" +
                            "Race: %d hours\n" +
                            "%d entries\n" +
                            "$%.2f prize money\n" +
                            "%d available routes",
                            name,
                            durationHours,
                            entries,
                            prizeMoney,
                            currentRouteCount);
    }

    public List<Route> getRoutes() { return routes; }
    public int getDurationHours() { return durationHours; }
    public int getEntries() { return entries; }
    public Float getPrizeMoney() { return prizeMoney; }
    public int getDurationTicks() { return durationTicks; }
    public String getName() { return name; }
}
