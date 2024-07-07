package app.audio.Files;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Song.
 */
@Getter
public final class Song extends AudioFile {
    /**
     * -- GETTER --
     * Getter for album, tags, lyrics, genre, releaseYear, artist, likes.
     */
    private final String album;
    private final ArrayList<String> tags;
    private final String lyrics;
    private final String genre;
    private final Integer releaseYear;
    private final String artist;
    private Integer likes;

    /**
     * Instantiates a new Song.
     *
     * @param name        the name
     * @param duration    the duration
     * @param album       the album
     * @param tags        the tags
     * @param lyrics      the lyrics
     * @param genre       the genre
     * @param releaseYear the release year
     * @param artist      the artist
     */
    public Song(final String name, final Integer duration, final String album,
                final ArrayList<String> tags, final String lyrics, final String genre,
                final Integer releaseYear, final String artist) {
        super(name, duration);
        this.album = album;
        this.tags = tags;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.likes = 0;
    }

    /**
     * Checks if the song has the same album with a given input.
     *
     * @param albumName the album
     * @return boolean
     */
    @Override
    public boolean matchesAlbum(final String albumName) {
        return this.getAlbum().equalsIgnoreCase(albumName);
    }

    /**
     * Checks if the song has the same tags with a given input.
     *
     * @param tagsList the tags
     * @return boolean
     */
    @Override
    public boolean matchesTags(final ArrayList<String> tagsList) {
        List<String> songTags = new ArrayList<>();
        for (String tag : this.getTags()) {
            songTags.add(tag.toLowerCase());
        }

        for (String tag : tagsList) {
            if (!songTags.contains(tag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the song has in the lyrics a given input.
     *
     * @param lyricFilter the lyrics
     * @return boolean
     */
    @Override
    public boolean matchesLyrics(final String lyricFilter) {
        return this.getLyrics().toLowerCase().contains(lyricFilter.toLowerCase());
    }

    /**
     * Checks if the song has the same genre with a given input.
     *
     * @param genreFilter the genre
     * @return boolean
     */
    @Override
    public boolean matchesGenre(final String genreFilter) {
        return this.getGenre().equalsIgnoreCase(genreFilter);
    }

    /**
     * Checks if the song has the same artist with a given input.
     *
     * @param artistFilter the artist
     * @return boolean
     */
    @Override
    public boolean matchesArtist(final String artistFilter) {
        return this.getArtist().equalsIgnoreCase(artistFilter);
    }

    /**
     * Checks if the song has the same release year with a given input.
     *
     * @param releaseYearFilter the release year
     * @return boolean
     */
    @Override
    public boolean matchesReleaseYear(final String releaseYearFilter) {
        return filterByYear(this.getReleaseYear(), releaseYearFilter);
    }

    /**
     * Parses the string into an integer compare
     *
     * @param year the year
     * @param query the query
     * @return boolean
     */
    private static boolean filterByYear(final int year, final String query) {
        if (query.startsWith("<")) {
            return year < Integer.parseInt(query.substring(1));
        } else if (query.startsWith(">")) {
            return year > Integer.parseInt(query.substring(1));
        } else {
            return year == Integer.parseInt(query);
        }
    }

    /**
     * Like.
     */
    public void like() {
        likes++;
    }

    /**
     * Dislike.
     */
    public void dislike() {
        likes--;
    }
}
