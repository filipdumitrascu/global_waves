package fileio.input;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type SongInput, used to store the ObjectMapper input for songs.
 */
@Getter
@Setter
public final class SongInput {
    /**
     * -- GETTER && SETTER --
     * for name, duration album, tags, lyrics, genre, releaseYear and artist for the song
     */
    private String name;
    private Integer duration;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private Integer releaseYear;
    private String artist;

    /**
     * Instantiates a new SongInput.
     */
    public SongInput() {
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "SongInput{"
                + "name='" + name + '\''
                + ", duration=" + duration
                + ", album='" + album + '\''
                + ", tags=" + tags
                + ", lyrics='" + lyrics + '\''
                + ", genre='" + genre + '\''
                + ", releaseYear='" + releaseYear + '\''
                + ", artist='" + artist + '\''
                + '}';
    }
}
