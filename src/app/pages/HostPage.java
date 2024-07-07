package app.pages;

import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.data.host.Announcement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HostPage extends Page {
    /**
     * -- GETTER --
     * for podcasts and announcements of the page.
     */
    private final ArrayList<Podcast> podcasts;
    private final ArrayList<Announcement> announcements;

    /**
     * Instantiates a new HostPage.
     *
     * @param podcasts the podcasts
     * @param announcements the announcements
     */
    public HostPage(final ArrayList<Podcast> podcasts,
                    final ArrayList<Announcement> announcements) {
        super();
        this.podcasts = podcasts;
        this.announcements = announcements;
    }

    /**
     * Gets the podcast content.
     *
     * @return the podcasts content
     */
    public List<String> getPodcastsContent() {
        List<String> results = new ArrayList<>();
        List<String> episodesContent = new ArrayList<>();

        for (Podcast podcast : podcasts) {
            for (Episode episode : podcast.getEpisodes()) {
                episodesContent.add(episode.getName() + " - " + episode.getDescription());
            }

            results.add(podcast.getName() + ":\n\t" + episodesContent + "\n");
            episodesContent.clear();
        }

        return results;
    }

    /**
     * Gets the announcement content.
     *
     * @return the announcements content
     */
    public List<String> getAnnouncementsContent() {
        List<String> results = new ArrayList<>();

        for (Announcement announcement : announcements) {
            results.add(announcement.getName() + ":\n\t" + announcement.getDescription() + "\n");
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
        return visitor.printHostPage(this);
    }
}
