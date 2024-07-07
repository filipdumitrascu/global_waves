package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.utils.Enums;
import lombok.Getter;

import java.util.ArrayList;

/**
 * The type Playlist.
 */
@Getter
public final class Playlist extends AudioCollection {
    /**
     * -- GETTER --
     * Getter for songs, visibility, followers and timestamp.
     */
    private final ArrayList<Song> songs;
    private Enums.Visibility visibility;
    private Integer followers;
    private final int timestamp;

    /**
     * Instantiates a new Playlist.
     *
     * @param name      the name
     * @param owner     the owner
     * @param timestamp the timestamp
     */
    public Playlist(final String name, final String owner, final int timestamp) {
        super(name, owner);
        this.songs = new ArrayList<>();
        this.visibility = Enums.Visibility.PUBLIC;
        this.followers = 0;
        this.timestamp = timestamp;
    }

    /**
     * Contains song boolean.
     *
     * @param song the song
     * @return the boolean
     */
    public boolean containsSong(final Song song) {
        return songs.contains(song);
    }

    /**
     * Add song.
     *
     * @param song the song
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Remove song.
     *
     * @param song the song
     */
    public void removeSong(final Song song) {
        songs.remove(song);
    }

    /**
     * Switch visibility.
     */
    public void switchVisibility() {
        if (visibility == Enums.Visibility.PUBLIC) {
            visibility = Enums.Visibility.PRIVATE;
        } else {
            visibility = Enums.Visibility.PUBLIC;
        }
    }

    /**
     * Increase followers.
     */
    public void increaseFollowers() {
        followers++;
    }

    /**
     * Decrease followers.
     */
    public void decreaseFollowers() {
        followers--;
    }

    /**
     * Gets the number of songs in the playlist.
     *
     * @return the size
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     * Gets the index song.
     *
     * @param index the index
     * @return the episode
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    /**
     * Checks if the playlist is visible to a specific user.
     *
     * @param user the user
     * @return the boolean
     */
    @Override
    public boolean isVisibleToUser(final String user) {
        return this.getVisibility() == Enums.Visibility.PUBLIC
               || (this.getVisibility() == Enums.Visibility.PRIVATE
                   && this.getOwner().equals(user));
    }

    /**
     * Checks if the playlist has the number of followers
     * how a given input says.
     *
     * @param followerNum the follower numbers and the comparator
     * @return boolean
     */
    @Override
    public boolean matchesFollowers(final String followerNum) {
        return filterByFollowersCount(this.getFollowers(), followerNum);
    }

    /**
     * Parses the string into an integer compare
     *
     * @param count the year
     * @param query the query
     * @return boolean
     */
    private static boolean filterByFollowersCount(final int count, final String query) {
        if (query.startsWith("<")) {
            return count < Integer.parseInt(query.substring(1));
        } else if (query.startsWith(">")) {
            return count > Integer.parseInt(query.substring(1));
        } else {
            return count == Integer.parseInt(query);
        }
    }

    /**
     * Calculates the total number of likes.
     *
     * @return int
     */
    public int getTotalLikes() {
        int totalLikes = 0;

        for (Song song : songs) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }

}
