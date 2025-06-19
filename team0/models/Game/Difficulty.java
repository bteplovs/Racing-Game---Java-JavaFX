package seng201.team0.models.Game;

/**
 * Enum class which represents the difficulty levels in the game.
 * Each difficulty level has its own multiplier which either makes the game harder or easier.
 */
public enum Difficulty {
    /** Easy difficulty reduces difficulty in certain aspects by 50%*/
    EASY(0.5),
    /** Medium difficulty does not enhance or decrease any difficulty*/
    MEDIUM(1.0),
    /** Hard difficulty increases difficulty in certain aspects by 75%*/
    HARD(1.75);

    private final double multiplier;

    /**
     * Constructs the difficulty level for the game.
     */
    Difficulty(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}