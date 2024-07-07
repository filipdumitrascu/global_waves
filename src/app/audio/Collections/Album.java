package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type AudioCollection.
 */
@Getter
public class Album extends AudioCollection {
    /**
     * -- GETTER --
     * Getter for songs, visibility, followers and timestamp.
     */
    private final int releaseYear;
    private final String description;
    @Setter
    private ArrayList<Song> songs;

    /**
     * Instantiates a new Album.
     *
     * @param name the name
     * @param owner the owner
     * @param releaseYear the release year
     * @param description the description
     */
    public Album(final String name, final String owner, final int releaseYear,
                 final String description) {
        super(name, owner);
        this.releaseYear = releaseYear;
        this.description = description;
        this.songs = new ArrayList<>();
    }

    /**
     * Gets the number of songs from the album.
     *
     * @return size
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     * Gets the index song.
     *
     * @param index the index
     * @return the song
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    /**
     * Checks if the album description starts with the given string.
     *
     * @param descriptionFilter the description
     * @return the boolean
     */
    public boolean matchesDescription(final String descriptionFilter) {
        return getDescription().toLowerCase().startsWith(descriptionFilter.toLowerCase());
    }

    /**
     * Calculates the total number of likes on an album.
     *
     * @return the number of likes
     */
    public int getTotalLikes() {
        int totalLikes = 0;

        for (Song song : songs) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }
}
