package seng201.team0.models.Events;

import java.util.Random;

/**
 * Represents an in-game event.
 */
public class Event {
    private final String name;
    private final String description;
    private final double chance;

    /**
     * Constructs an event instance with the given parameters
     *
     * @param name The event name
     * @param description The event description
     * @param chance The chance of the event occurring
     */
    public Event(String name, String description, double chance) {
        this.name = name;
        this.description = description;
        this.chance = chance;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    /**
     * Determines whether the event occurs based on its chance of occurrence.
     * A random double is generated and compared to the event's chance value.
     *
     * @return True if the event occurs, false otherwise
     */
    public boolean occurs() { return new Random().nextDouble() < chance; }

    /**
     * Returns the string repr of the event object
     *
     * @return formatted string repr of the event object
     */
    @Override
    public String toString() { return name + ": " + description; }
}