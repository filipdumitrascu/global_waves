package app.searchBar;

import app.audio.LibraryEntry;
import app.users.UserEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Filter utils.
 */
public final class FilterUtils {
    /**
     * Instantiates a new Filter Utils.
     *
     */
    private FilterUtils() {
    }

    /**
     * Filter by name list.
     *
     * @param entries the entries
     * @param name    the name
     * @return the list
     */
    public static List<LibraryEntry> filterAudioByName(final List<LibraryEntry> entries,
                                                  final String name) {
        List<LibraryEntry> result = new ArrayList<>();
        for (LibraryEntry entry : entries) {
            if (entry.matchesName(name)) {
                result.add(entry);
            }
        }
        return result;
    }

    /**
     * Filter by album list.
     *
     * @param entries the entries
     * @param album   the album
     * @return the list
     */
    public static List<LibraryEntry> filterByAlbum(final List<LibraryEntry> entries,
                                                   final String album) {
        return filter(entries, entry -> entry.matchesAlbum(album));
    }

    /**
     * Filter by tags list.
     *
     * @param entries the entries
     * @param tags    the tags
     * @return the list
     */
    public static List<LibraryEntry> filterByTags(final List<LibraryEntry> entries,
                                                  final ArrayList<String> tags) {
        return filter(entries, entry -> entry.matchesTags(tags));
    }

    /**
     * Filter by lyrics list.
     *
     * @param entries the entries
     * @param lyrics  the lyrics
     * @return the list
     */
    public static List<LibraryEntry> filterByLyrics(final List<LibraryEntry> entries,
                                                    final String lyrics) {
        return filter(entries, entry -> entry.matchesLyrics(lyrics));
    }

    /**
     * Filter by genre list.
     *
     * @param entries the entries
     * @param genre   the genre
     * @return the list
     */
    public static List<LibraryEntry> filterByGenre(final List<LibraryEntry> entries,
                                                   final String genre) {
        return filter(entries, entry -> entry.matchesGenre(genre));
    }

    /**
     * Filter by artist list.
     *
     * @param entries the entries
     * @param artist  the artist
     * @return the list
     */
    public static List<LibraryEntry> filterByArtist(final List<LibraryEntry> entries,
                                                    final String artist) {
        return filter(entries, entry -> entry.matchesArtist(artist));
    }

    /**
     * Filter by release year list.
     *
     * @param entries     the entries
     * @param releaseYear the release year
     * @return the list
     */
    public static List<LibraryEntry> filterByReleaseYear(final List<LibraryEntry> entries,
                                                         final String releaseYear) {
        return filter(entries, entry -> entry.matchesReleaseYear(releaseYear));
    }

    /**
     * Filter by owner list.
     *
     * @param entries the entries
     * @param user    the user
     * @return the list
     */
    public static List<LibraryEntry> filterByOwner(final List<LibraryEntry> entries,
                                                   final String user) {
        return filter(entries, entry -> entry.matchesOwner(user));
    }

    /**
     * Filter by playlist visibility list.
     *
     * @param entries the entries
     * @param user    the user
     * @return the list
     */
    public static List<LibraryEntry> filterByPlaylistVisibility(final List<LibraryEntry> entries,
                                                                final String user) {
        return filter(entries, entry -> entry.isVisibleToUser(user));
    }

    /**
     * Filter by followers list.
     *
     * @param entries   the entries
     * @param followers the followers
     * @return the list
     */
    public static List<LibraryEntry> filterByFollowers(final List<LibraryEntry> entries,
                                                       final String followers) {
        return filter(entries, entry -> entry.matchesFollowers(followers));
    }

    /**
     * Filter by owner.
     *
     * @param entries the entries
     * @param owner the owner
     * @return list of library entries
     */
    public static List<LibraryEntry> filterByOwnerName(final List<LibraryEntry> entries,
                                                       final String owner) {
        return filter(entries, entry -> entry.matchesOwnerByName(owner));
    }

    /**
     * Filter by description.
     *
     * @param entries the entries
     * @param description the description
     * @return list of library entries
     */
    public static List<LibraryEntry> filterByDescription(final List<LibraryEntry> entries,
                                                         final String description) {
        return filter(entries, entry -> entry.matchesDescription(description));
    }

    /**
     * Filter user by name.
     *
     * @param users the users
     * @param name the name
     * @return the list with the users
     */
    public static List<UserEntry> filterUserByName(final List<UserEntry> users, final String name) {
        List<UserEntry> result = new ArrayList<>();
        for (UserEntry user : users) {
            if (user.matchesName(name)) {
                result.add(user);
            }
        }
        return result;
    }

    /**
     * Checks the matches.
     *
     * @param entries the entries
     * @param criteria the criteria
     * @return the list with library entries
     */
    private static List<LibraryEntry> filter(final List<LibraryEntry> entries,
                                             final FilterCriteria criteria) {
        List<LibraryEntry> result = new ArrayList<>();
        for (LibraryEntry entry : entries) {
            if (criteria.matches(entry)) {
                result.add(entry);
            }
        }
        return result;
    }

    @FunctionalInterface
    private interface FilterCriteria {
        /**
         * Matches boolean.
         *
         * @param entry the entry
         * @return the boolean
         */
        boolean matches(LibraryEntry entry);
    }
}
