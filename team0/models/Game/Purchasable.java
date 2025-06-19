package seng201.team0.models.Game;

/**
 * Interface which represents any in game item which can be purchased by the player.
 * Representing cars and parts.
 */
public interface Purchasable {
    /** @return price to purchase the item. */
    public int getBuyPrice();
    /** @return the sell price of the item. */
    public int getSellPrice();
    /** Returns the description of the item. */
    public String getDescription();
    /** Returns the image of the item. */
    public String getPath();
    /** Returns the name of the item. */
    public String getName();
}
