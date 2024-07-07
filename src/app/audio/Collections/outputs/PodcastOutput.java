package app.audio.Collections.outputs;

import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import lombok.Getter;

import java.util.ArrayList;

/**
 * The type PodcastOutput.
 */
@Getter
public class PodcastOutput {
    /**
     * -- GETTER --
     * Getter for name, songs.
     */
    private final String name;
    private final ArrayList<String> episodes;

    /**
     * Instantiates a new PodcastOutput.
     *
     * @param podcast the podcast
     */
    public PodcastOutput(final Podcast podcast) {
        this.name = podcast.getName();
        this.episodes = new ArrayList<>();

        for (Episode episode : podcast.getEpisodes()) {
            episodes.add(episode.getName());
        }
    }

}
