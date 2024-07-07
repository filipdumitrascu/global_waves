package app.player;

import lombok.Getter;

/**
 * The type Podcast Bookmark.
 */
@Getter
public final class PodcastBookmark {
    /**
     * -- GETTER --
     * Getter for name, id and timestamp.
     */
    private final String name;
    private final int id;
    private final int timestamp;

    /**
     * Instantiates a new Podcast Bookmark.
     *
     * @param name the name
     * @param id the id
     * @param timestamp the timestamp
     */
    public PodcastBookmark(final String name,
                           final int id,
                           final int timestamp) {
        this.name = name;
        this.id = id;
        this.timestamp = timestamp;
    }

    /**
     * toString method.
     *
     * @return string
     */
    @Override
    public String toString() {
        return "PodcastBookmark{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", timestamp=" + timestamp
                + '}';
    }
}
