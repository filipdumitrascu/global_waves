package fileio.input;

import lombok.Getter;
import lombok.Setter;

/**
 * The type EpisodeInput, used to store the ObjectMapper input for episodes.
 */
@Getter
@Setter
public final class EpisodeInput {
    /**
     * -- GETTER && SETTER --
     * for name, duration and description of the episode.
     */
    private String name;
    private Integer duration;
    private String description;

    /**
     * Instantiates a new EpisodeInput.
     */
    public EpisodeInput() {
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "EpisodeInput{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", duration=" + duration
                + '}';
    }
}
