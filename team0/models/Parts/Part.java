package seng201.team0.models.Parts;

import seng201.team0.models.Game.Purchasable;

/**
 * Represents a part in the game that can be purchased and sold.
 * Each part attributes: name, type, description, stat boost value, and availability status.
 */
public class Part implements Purchasable {

    private final String name;
    private final String description;
    private final PartType type;
    private final int statBoost;
    private final String availableURL;
    private boolean isAvailable;

    /**
     * Constructs a new parts instance
     *
     * @param name The name of the part
     * @param type The part type
     * @param description The description of the part
     * @param statBoost The stat boost the part provides
     * @param pathURL The URL path to the image associated with the part
     */
    public Part(String name, PartType type, String description, int statBoost, String pathURL) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.statBoost = statBoost;
        this.availableURL = pathURL;

        this.isAvailable = true;
    }

    public String getName() { return name; }
    @Override
    public String getDescription() { return description; }
    @Override
    public String getPath() { return ""; }
    public PartType getType() { return type; }
    public Integer getStatBoost() { return statBoost; }

    /**
     * Calculates and returns the purchase price of the part.
     *
     * @return The buy price of the part.
     */
    @Override
    public int getBuyPrice() {
        return (int) (statBoost * 5.0);
    }

    /**
     * Calculates and returns the sell price of the part.
     * The sell price is 70% of the buy price
     *
     * @return The sell price of the part.
     */
    @Override
    public int getSellPrice() {
        return (int) (getBuyPrice() * 0.7);
    }


    /**
     * Provides a string representation of the part for display.
     *
     * @return returns the string repr of the part
     */
    @Override
    public String toString() {
        return String.format("Name: %s\n" +
                        "Boosts: %s\n" +
                        "By + %s\n" +
                        "Sell Price: %s",
                        name,
                        type,
                        statBoost,
                        getSellPrice());
    }

    /**
     * Provides a string representation of the part for display.
     *
     * @return A formatted string with detailed part information.
     */
    public String partFormat() {
        return String.format("Part Name: %s\n" +
                        "Type: %s\n" +
                        "Description: %s\n" +
                        "Stat Boost: +%d\n" +
                        "Buy Price: $%.2f\n" +
                        "Sell Price: $%.2f",
                        name,
                        type,
                        description,
                        statBoost,
                        (double) getBuyPrice(),
                        (double) getSellPrice());
    }

    /**
     * returns the availability status of the part
     *
     * @return The part's availability, true -> available, false otherwise
     */
    public boolean partIsAvailable() { return isAvailable; }

    /**
     * Sets the availability status of the part
     *
     * @param b The new status
     */
    public void setPartIsAvailable(boolean b) { isAvailable = b; }

}
