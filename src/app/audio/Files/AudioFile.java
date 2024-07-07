package app.audio.Files;

import app.audio.LibraryEntry;
import lombok.Getter;

/**
 * The type AudioFile.
 */
@Getter
public abstract class AudioFile extends LibraryEntry {
    /**
     * -- GETTER --
     * Getter for duration.
     */
    private final Integer duration;

    /**
     * Instantiates a new AudioFile.
     *
     * @param name the name
     * @param duration the duration
     */
    public AudioFile(final String name, final Integer duration) {
        super(name);
        this.duration = duration;
    }
}
