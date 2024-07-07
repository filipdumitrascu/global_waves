package app.data.artist;

import app.data.DataEntry;
import lombok.Getter;

/**
 * The type Merch.
 */
@Getter
public class Merch extends DataEntry {
    /**
     * -- GETTER --
     * Getter for price.
     */
    private final int price;

    /**
     * Instantiates a new Merch.
     *
     * @param name the name
     * @param description the description
     * @param price the price
     */
    public Merch(final String name, final String description, final int price) {
        super(name, description);
        this.price = price;
    }
}
