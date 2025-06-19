package seng201.team0.models.Parts;

import java.util.ArrayList;
import java.util.List;

/**
 * AllParts class contains hardcoded races for the different parts available in the shop. Each part is represented by the part object
 */
public class AllParts {

    private static final Part COIL_SPRING_PART = new Part(
            "Coil Spring",
            PartType.RELIABILTY,
            "Improves a car's reliability",
            3,
            "/Assets/Parts/Reliability.png");

    private static final Part GAS_CAN_PART = new Part(
            "Gas Can",
            PartType.FUEL_ECONOMY,
            "What do you think it does?",
            3,
            "/Assets/Parts/FuelEco.png");

    private static final Part NITO_PART = new Part(
            "Turbo Charger",
            PartType.SPEED,
            "Nitro, boosts a cars speeeeeeeeeeeed",
            3,
            "/Assets/Parts/Speed.png");

    private static final Part STEERING_WHEEL_PART = new Part(
            "Steering Wheel",
            PartType.HANDLING,
            "Boosts a cars Handling",
            3,
            "/Assets/Parts/Handling.png");

    public static Part getCoilSpringPart() {
        return COIL_SPRING_PART;
    }
    public static Part getGasCanPart() {
        return GAS_CAN_PART;
    }
    public static Part getNitroPart() {
        return NITO_PART;
    }
    public static Part getSteeringWheelPart() {
        return STEERING_WHEEL_PART;
    }

    public static List<Part> getParts() {
        List<Part> parts = new ArrayList<>();
        parts.add(COIL_SPRING_PART);
        parts.add(GAS_CAN_PART);
        parts.add(NITO_PART);
        parts.add(STEERING_WHEEL_PART);
        return parts;
    }
}