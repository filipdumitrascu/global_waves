package fileio.input;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type PodcastInput, used to store the ObjectMapper input for podcasts.
 */
@Getter
@Setter
public final class PodcastInput {
    /**
     * -- GETTER && SETTER --
     * for name, owner and the episodes of the podcast
     */
    private String name;
    private String owner;
    private ArrayList<EpisodeInput> episodes;

    /**
     * Instantiates a new PodcastInput.
     */
    public PodcastInput() {
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "PodcastInput{"
                + "name='" + name + '\''
                + ", owner='" + owner + '\''
                + ", episodes=" + episodes
                + '}';
    }
}
