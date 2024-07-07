package fileio.input;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type FiltersInput, used to store the ObjectMapper input for filters.
 */
@Getter
@Setter
public final class FiltersInput {
    /**
     * -- GETTER && SETTER --
     * for all search filter parameters
     */
    private String name;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private String releaseYear;
    private String artist;
    private String owner;
    private String followers;
    private String description;

    /**
     * Instantiates a new FiltersInput.
     */
    public FiltersInput() {
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "FiltersInput{"
                + "name='" + name + '\''
                + ", album='" + album + '\''
                + ", tags=" + tags
                + ", lyrics='" + lyrics + '\''
                + ", genre='" + genre + '\''
                + ", releaseYear='" + releaseYear + '\''
                + ", artist='" + artist + '\''
                + ", owner='" + owner + '\''
                + ", followers='" + followers + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
