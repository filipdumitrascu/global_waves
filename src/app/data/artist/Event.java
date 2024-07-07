package app.data.artist;

import app.data.DataEntry;
import lombok.Getter;

/**
 * The type Event.
 */
@Getter
public class Event extends DataEntry {
    /**
     * -- GETTER --
     * Getter for date.
     */
    private final String date;

    /**
     * Instantiates a new Event.
     *
     * @param name the name
     * @param description the description
     * @param date the date
     */
    public Event(final String name, final String description, final String date) {
        super(name, description);
        this.date = date;
    }

}
