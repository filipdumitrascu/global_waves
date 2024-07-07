package fileio.input;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type LibraryInput, used to store the ObjectMapper input for library.
 */
@Getter
@Setter
public final class LibraryInput {
    /**
     * -- GETTER && SETTER --
     * for library content
     */
    private ArrayList<SongInput> songs;
    private ArrayList<PodcastInput> podcasts;
    private ArrayList<UserInput> users;

    /**
     * Instantiates a new LibraryInput.
     */
    public LibraryInput() {
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "LibraryInput{"
                + "songs=" + songs
                + ", podcasts=" + podcasts
                + ", users=" + users
                + '}';
    }
}
