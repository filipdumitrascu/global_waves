package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.LibraryEntry;
import lombok.Getter;

/**
 * The type Audio collection.
 */
@Getter
public abstract class AudioCollection extends LibraryEntry {
    /**
     * -- GETTER --
     * Getter for owner.
     */
    private final String owner;

    /**
     * Instantiates a new Audio collection.
     *
     * @param name  the name
     * @param owner the owner
     */
    public AudioCollection(final String name, final String owner) {
        super(name);
        this.owner = owner;
    }

    /**
     * Gets number of tracks.
     *
     * @return the number of tracks
     */
    public abstract int getNumberOfTracks();

    /**
     * Gets track by index.
     *
     * @param index the index
     * @return the track by index
     */
    public abstract AudioFile getTrackByIndex(int index);

    /**
     * Checks if the owner of te collection is the same
     * with the given one.
     *
     * @param user the user
     * @return if the user is the owner
     */
    public boolean matchesOwner(final String user) {
        return this.getOwner().equals(user);
    }

    /**
     * Checks if the owner of the collection
     * has the same name with a given input.
     *
     * @param ownerFilter the filter
     * @return the boolean
     */
    public boolean matchesOwnerByName(final String ownerFilter) {
        return getOwner().toLowerCase().startsWith(ownerFilter.toLowerCase());
    }

}
