package seng201.team0.models.Events;

import seng201.team0.services.GameService;

import java.util.ArrayList;
import java.util.List;

/**
 * AllEvents class contains hardcoded events which across during racing, each Event is represented by the event object
 */
public class AllEvents {

    private static final Event HITCHHIKER_ENCOUNTER = new Event(
            "Hitchhiker Encounter",
            "You see a hitchhiker on the road.",
            0.1
    );

    private static final Event SEVERE_WEATHER = new Event(
            "Severe Weather",
            "A storm hits suddenly.",
            0.015
    );

    private static final Event CAR_BREAKDOWN = new Event(
            "Car Breakdown",
            "Your car suddenly breaks down.",
            ((double) (100 - GameService.getInstance().getPlayer().getSelectedCar().getReliability()) / 1000) * GameService.getInstance().getSelectedRoute().getReliabilityMultiplier()
    );

    private static final Event A_STRANGE_MAN = new Event(
        "A Strange Man",
        "A strange man suddenly appears",
        0.07
    );

    public static List<Event> getEvents() {
        List<Event> randomEvents = new ArrayList<>();
        randomEvents.add(HITCHHIKER_ENCOUNTER);
        randomEvents.add(SEVERE_WEATHER);
        randomEvents.add(CAR_BREAKDOWN);
        randomEvents.add(A_STRANGE_MAN);
        return randomEvents;
    }

    public static Event getHitchhikerEncounter() { return HITCHHIKER_ENCOUNTER;}
    public static Event getSevereWeather() { return SEVERE_WEATHER;}
    public static Event getCarBreakdown() { return CAR_BREAKDOWN;}
    public static Event getaStrangeMan() { return A_STRANGE_MAN;}
}
