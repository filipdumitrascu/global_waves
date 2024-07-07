package app.audio.Files;

import lombok.Getter;

/**
 * The type Episode.
 */
@Getter
public final class Episode extends AudioFile {
    /**
     * -- GETTER --
     * Getter for description.
     */
    private final String description;

    /**
     * Instantiates a new Episode.
     *
     * @param name the name
     * @param duration the duration
     * @param description the description
     */
    public Episode(final String name, final Integer duration, final String description) {
        super(name, duration);
        this.description = description;
    }
}
