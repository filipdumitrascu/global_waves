package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import lombok.Getter;

import java.util.List;

/**
 * The type Podcast
 */
@Getter
public final class Podcast extends AudioCollection {
    /**
     * -- GETTER --
     * Getter for episodes.
     */
    private final List<Episode> episodes;

    /**
     * Instantiates a new Podcast.
     *
     * @param name the name
     * @param owner the owner
     * @param episodes the episodes
     */
    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        super(name, owner);
        this.episodes = episodes;
    }

    /**
     * Gets the number of episodes
     *
     * @return size
     */
    @Override
    public int getNumberOfTracks() {
        return episodes.size();
    }

    /**
     * Gets the index episode
     *
     * @param index the index
     * @return the episode
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return episodes.get(index);
    }
}
