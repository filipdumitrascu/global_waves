package app.data;

import lombok.Getter;

/**
 * The type DataEntry.
 */
@Getter
public class DataEntry {
    /**
     * -- GETTER --
     * Getter for name, description.
     */
    private final String name;
    private final String description;

    /**
     * Instantiates a new DataEntry.
     *
     * @param name the name
     * @param description the description
     */
    public DataEntry(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

}
