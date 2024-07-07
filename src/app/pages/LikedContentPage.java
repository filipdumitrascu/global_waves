package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * The type LikedContentPage.
 */
public class LikedContentPage extends Page {
    /**
     * Instantiates a new LikedContentPage.
     *
     * @param likedSongs   the likedSongs
     * @param followedPlaylists  the followedPlaylists
     */
    public LikedContentPage(final ArrayList<Song> likedSongs,
                            final ArrayList<Playlist> followedPlaylists) {
        super(likedSongs, followedPlaylists);
    }

    /**
     * Gets the songs.
     *
     * @return the songs
     */
    public List<String> getLikedSongsContent() {
        List<String> results = new ArrayList<>();

        for (Song song : getLikedSongs()) {
            results.add(song.getName() + " - " + song.getArtist());
        }

        return results;
    }

    /**
     * Gets the playlists.
     *
     * @return the playlists
     */
    public List<String> getFollowedPlaylistsContent() {
        List<String> results = new ArrayList<>();

        for (Playlist playlist : getFollowedPlaylists()) {
            results.add(playlist.getName() + " - " + playlist.getOwner());
        }

        return results;
    }

    /**
     * Accepts the visitor.
     *
     * @param visitor the visitor
     * @return string
     */
    public String accept(final PageVisitor visitor) {
        return visitor.printLikedContentPage(this);
    }
}
