package app.audio.Collections.outputs;

import app.audio.Collections.Album;
import app.audio.Files.Song;
import lombok.Getter;

import java.util.ArrayList;

/**
 * The type AlbumOutput.
 */
@Getter
public class AlbumOutput {
    /**
     * -- GETTER --
     * Getter for name, songs.
     */
    private final String name;
    private final ArrayList<String> songs;

    /**
     * Instantiates a new AlbumOutput.
     *
     * @param album the album
     */
    public AlbumOutput(final Album album) {
        this.name = album.getName();
        this.songs = new ArrayList<>();

        for (Song song : album.getSongs()) {
            songs.add(song.getName());
        }
    }

}
