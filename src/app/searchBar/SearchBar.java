package app.searchBar;


import app.Admin;
import app.audio.LibraryEntry;
import app.users.UserEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static app.searchBar.FilterUtils.filterByAlbum;
import static app.searchBar.FilterUtils.filterByArtist;
import static app.searchBar.FilterUtils.filterByFollowers;
import static app.searchBar.FilterUtils.filterByGenre;
import static app.searchBar.FilterUtils.filterByLyrics;
import static app.searchBar.FilterUtils.filterAudioByName;
import static app.searchBar.FilterUtils.filterByOwner;
import static app.searchBar.FilterUtils.filterByPlaylistVisibility;
import static app.searchBar.FilterUtils.filterByReleaseYear;
import static app.searchBar.FilterUtils.filterByTags;
import static app.searchBar.FilterUtils.filterByOwnerName;
import static app.searchBar.FilterUtils.filterByDescription;
import static app.searchBar.FilterUtils.filterUserByName;

/**
 * The type Search bar.
 */
public final class SearchBar {
    private List<LibraryEntry> audioSearchResults;
    private List<UserEntry> userSearchResults;
    private final String user;
    private static final Integer MAX_RESULTS = 5;
    @Getter
    private String lastSearchType;
    @Getter
    private LibraryEntry lastAudioSelected;
    @Getter
    @Setter
    private UserEntry lastUserSelected;

    /**
     * Instantiates a new Search bar.
     *
     * @param user the user
     */
    public SearchBar(final String user) {
        this.audioSearchResults = new ArrayList<>();
        this.userSearchResults = new ArrayList<>();
        this.user = user;
    }

    /**
     * Clear last selection
     */
    public void clearSelection() {
        lastAudioSelected = null;
        lastUserSelected = null;
        lastSearchType = null;
    }

    /**
     * Search audio list.
     *
     * @param filters the filters
     * @param type    the type
     * @return the list
     */
    public List<LibraryEntry> searchAudio(final Filters filters, final String type) {
        List<LibraryEntry> entries;

        switch (type) {
            case "song":
                entries = new ArrayList<>(Admin.getSongs());

                if (filters.getName() != null) {
                    entries = filterAudioByName(entries, filters.getName());
                }

                if (filters.getAlbum() != null) {
                    entries = filterByAlbum(entries, filters.getAlbum());
                }

                if (filters.getTags() != null) {
                    entries = filterByTags(entries, filters.getTags());
                }

                if (filters.getLyrics() != null) {
                    entries = filterByLyrics(entries, filters.getLyrics());
                }

                if (filters.getGenre() != null) {
                    entries = filterByGenre(entries, filters.getGenre());
                }

                if (filters.getReleaseYear() != null) {
                    entries = filterByReleaseYear(entries, filters.getReleaseYear());
                }

                if (filters.getArtist() != null) {
                    entries = filterByArtist(entries, filters.getArtist());
                }

                break;
            case "playlist":
                entries = new ArrayList<>(Admin.getPlaylists());

                entries = filterByPlaylistVisibility(entries, user);

                if (filters.getName() != null) {
                    entries = filterAudioByName(entries, filters.getName());
                }

                if (filters.getOwner() != null) {
                    entries = filterByOwner(entries, filters.getOwner());
                }

                if (filters.getFollowers() != null) {
                    entries = filterByFollowers(entries, filters.getFollowers());
                }

                break;
            case "podcast":
                entries = new ArrayList<>(Admin.getPodcasts());

                if (filters.getName() != null) {
                    entries = filterAudioByName(entries, filters.getName());
                }

                if (filters.getOwner() != null) {
                    entries = filterByOwner(entries, filters.getOwner());
                }

                break;
            case "album":
              entries = new ArrayList<>(Admin.getAlbums());

              if (filters.getName() != null) {
                  entries = filterAudioByName(entries, filters.getName());
              }

              if (filters.getOwner() != null) {
                  entries = filterByOwnerName(entries, filters.getOwner());
              }

              if (filters.getDescription() != null) {
                  entries = filterByDescription(entries, filters.getDescription());
              }

              break;

            default:
                entries = new ArrayList<>();
        }

        while (entries.size() > MAX_RESULTS) {
            entries.remove(entries.size() - 1);
        }

        this.audioSearchResults = entries;
        this.lastSearchType = type;
        return this.audioSearchResults;
    }

    /**
     * Search user list.
     *
     * @param filters the filters
     * @param type    the type
     * @return the list
     */
    public List<UserEntry> searchUser(final Filters filters, final String type) {
        List<UserEntry> users;

        switch (type) {
            case "artist":
                users = new ArrayList<>(Admin.getArtists());

                if (filters.getName() != null) {
                    users = filterUserByName(users, filters.getName());
                }

                break;

            case "host":
                users = new ArrayList<>(Admin.getHosts());

                if (filters.getName() != null) {
                    users = filterUserByName(users, filters.getName());
                }

                break;

            default:
                users = new ArrayList<>();
        }

        while (users.size() > MAX_RESULTS) {
            users.remove(users.size() - 1);
        }

        this.userSearchResults = users;
        this.lastSearchType = type;
        return this.userSearchResults;

    }

    /**
     * Select audio.
     *
     * @param itemNumber the item number
     * @return the library entry
     */
    public LibraryEntry selectAudio(final Integer itemNumber) {
        if (this.audioSearchResults.size() < itemNumber) {
            audioSearchResults.clear();

            return null;
        } else {
            lastAudioSelected =  this.audioSearchResults.get(itemNumber - 1);
            audioSearchResults.clear();

            return lastAudioSelected;
        }
    }

    /**
     * Select user's page.
     *
     * @param itemNumber the item number
     * @return the library entry
     */
    public UserEntry selectUser(final Integer itemNumber) {
        if (this.userSearchResults.size() < itemNumber) {
            userSearchResults.clear();

            return null;
        } else {
            lastUserSelected =  this.userSearchResults.get(itemNumber - 1);
            userSearchResults.clear();

            return lastUserSelected;
        }
    }
}
