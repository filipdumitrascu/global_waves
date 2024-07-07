package app.users;

import app.Admin;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.data.host.Announcement;
import app.pages.HostPage;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;
import lombok.Getter;

import java.util.ArrayList;

/**
 * The type Host.
 */
@Getter
public class Host extends UserEntry {
    /**
     * -- GETTER --
     * Getter for podcasts, announcements and the page of the host.
     */
    private final ArrayList<Podcast> podcasts;
    private final ArrayList<Announcement> announcements;
    private final HostPage hostPage;

    /**
     * Instantiates a new Host.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Host(final String username, final int age, final String city) {
        super(username, age, city);
        this.podcasts = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.hostPage = new HostPage(podcasts, announcements);
    }

    /**
     * Checks if in a podcast, two episodes are the same.
     *
     * @param episodes the episodes
     * @return boolean
     */
    private boolean areDuplicates(final ArrayList<Episode> episodes) {
        for (int i = 0; i < episodes.size() - 1; i++) {
            for (int j = i + 1; j < episodes.size(); j++) {
                if (episodes.get(i).getName().equals(episodes.get(j).getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Add Podcast string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String addPodcast(final CommandInput commandInput) {
        if (podcasts.stream().anyMatch(podcast -> podcast.getName()
                .equals(commandInput.getName()))) {
            return getUsername() + " has another podcast with the same name.";
        }

        ArrayList<Episode> episodes = new ArrayList<>();
        for (EpisodeInput episodeInput : commandInput.getEpisodes()) {
            episodes.add(new Episode(episodeInput.getName(),
                    episodeInput.getDuration(),
                    episodeInput.getDescription()));
        }

        if (areDuplicates(episodes)) {
            return getUsername() + " has the same episode in this podcast.";
        }

        Podcast newPodcast = new Podcast(commandInput.getName(), commandInput.getUsername(),
                episodes);
        podcasts.add(newPodcast);

        if (Admin.getPodcasts().stream().noneMatch(podcast -> podcast.getName()
                .equals(newPodcast.getName()))) {
            Admin.addPodcast(newPodcast);
        }

        return getUsername() + " has added new podcast successfully.";
    }

    /**
     * Remove Podcast string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String removePodcast(final CommandInput commandInput) {
        Podcast chosenPodcast = null;
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(commandInput.getName())) {
                chosenPodcast = podcast;
                break;
            }
        }

        if (chosenPodcast == null) {
            return getUsername() + " doesn't have a podcast with the given name.";
        }

        for (NormalUser normalUser : Admin.getAllNormalUsers()) {
            if (normalUser.getPlayer().getSource() != null && normalUser.getPlayer().getType()
                    .equals("podcast")) { // daca are ceva in player
                if (normalUser.getPlayer().getSource().getAudioCollection().equals(chosenPodcast)) {
                    return getUsername() + " can't delete this podcast.";
                }
            }
        }

        for (Podcast podcast : Admin.getPodcasts()) {
            if (podcast.getName().equals(chosenPodcast.getName())) {
                Admin.removePodcast(chosenPodcast);
                break;
            }
        }

        podcasts.remove(chosenPodcast);

        return getUsername() + " deleted the podcast successfully.";
    }

    /**
     * Add Announcement string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String addAnnouncement(final CommandInput commandInput) {
        if (announcements.stream().anyMatch(announcement -> announcement.getName()
                .equals(commandInput.getName()))) {
            return getUsername() + " has already added an announcement with this name.";
        }

        announcements.add(new Announcement(commandInput.getName(), commandInput.getDescription()));

        return getUsername() + " has successfully added new announcement.";
    }

    /**
     * Remove Announcement string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String removeAnnouncement(final CommandInput commandInput) {
        Announcement chosenAnnouncement = null;
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(commandInput.getName())) {
                chosenAnnouncement = announcement;
                break;
            }
        }

        if (chosenAnnouncement == null) {
            return getUsername() + " has no announcement with the given name.";
        }

        announcements.remove(chosenAnnouncement);

        return getUsername() + " has successfully deleted the announcement.";
    }

}
