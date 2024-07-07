package app.audio.Collections.outputs;

import app.audio.Collections.Playlist;
import app.utils.Enums;
import lombok.Getter;

import java.util.ArrayList;

/**
 * The type PlaylistOutput.
 */
@Getter
public class PlaylistOutput {
    /**
     * -- GETTER --
     * Getter for name, songs, visibility, followers.
     */
    private final String name;
    private final ArrayList<String> songs;
    private final String visibility;
    private final int followers;

    /**
     * Instantiates a new PlaylistOutput.
     *
     * @param playlist the playlist
     */
    public PlaylistOutput(final Playlist playlist) {
        this.name = playlist.getName();
        this.songs = new ArrayList<>();
        for (int i = 0; i < playlist.getSongs().size(); i++) {
            songs.add(playlist.getSongs().get(i).getName());
        }
        this.visibility = playlist.getVisibility() == Enums.Visibility.PRIVATE
                                                      ? "private" : "public";
        this.followers = playlist.getFollowers();
    }

}
