package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static app.Admin.printLimitedPlaylists;
import static app.Admin.printLimitedSongs;

/**
 * The type HomePage.
 */
public class HomePage extends Page {
    /**
     * Instantiates a new Home Page.
     *
     * @param likedSongs   the likedSongs
     * @param followedPlaylists  the followedPlaylists
     */
    public HomePage(final ArrayList<Song> likedSongs, final ArrayList<Playlist> followedPlaylists) {
        super(likedSongs, followedPlaylists);
    }

    /**
     * Gets the songs.
     *
     * @return the recommended songs
     */
    public List<String> getRecommended5Songs() {
        List<Song> sortedSongs = new ArrayList<>(getLikedSongs());
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        return printLimitedSongs(sortedSongs);
    }

    /**
     * Gets the playlists.
     *
     * @return the recommended playlists
     */
    public List<String> getRecommended5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getFollowedPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getTotalLikes).reversed());
        return printLimitedPlaylists(sortedPlaylists);
    }

    /**
     * Accepts the visitor.
     *
     * @param visitor the visitor
     * @return string
     */
    public String accept(final PageVisitor visitor) {
        return visitor.printHomePage(this);
    }
}
