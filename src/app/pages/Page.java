package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public abstract class Page {
    /**
     * -- GETTER --
     * Getter for likedSongs and followedPlaylists of the home and liked content pages.
     */
    private final ArrayList<Song> likedSongs;
    private final ArrayList<Playlist> followedPlaylists;

    /**
     * Instantiates a new Page.
     */
    public Page() {
        this.likedSongs = new ArrayList<>();
        this.followedPlaylists = new ArrayList<>();
    }

    /**
     * Instantiates a new Artist Page.
     *
     * @param likedSongs the likedSongs
     * @param followedPlaylists the followedPlaylists
     */
    public Page(final ArrayList<Song> likedSongs, final ArrayList<Playlist> followedPlaylists) {
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    /**
     * Accepts the visitor.
     *
     * @param visitor the visitor
     * @return string
     */
    public abstract String accept(PageVisitor visitor);
}
