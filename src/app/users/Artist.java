package app.users;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.data.artist.Event;
import app.data.artist.Merch;
import app.pages.ArtistPage;
import fileio.input.CommandInput;
import fileio.input.SongInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Artist.
 */
@Getter
public class Artist extends UserEntry {
    /**
     * -- GETTER --
     * Getter for albums, events, marches, and the page of the artist.
     */
    private final ArrayList<Album> albums;
    private final ArrayList<Event> events;
    private final ArrayList<Merch> marches;
    private final ArtistPage artistPage;

    private static final int FIRST_CHAR_DASH = 2;
    private static final int SECOND_CHAR_DASH = 5;
    private static final int DATE_LENGTH = 10;

    private static final int FIRST_YEAR = 1900;
    private static final int LAST_YEAR = 2023;

    private static final int FEBRUARY = 2;
    private static final int DECEMBER = 12;

    private static final int LAST_DAY_FEBRUARY = 28;
    private static final int LAST_DAY = 31;

    /**
     * Instantiates a new Artist.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        this.albums = new ArrayList<>();
        this.events = new ArrayList<>();
        this.marches = new ArrayList<>();
        this.artistPage = new ArtistPage(albums, marches, events);
    }

    /**
     * Checks if in an array, there is a song with a given name.
     *
     * @param songs array of songs
     * @param name the name
     * @return boolean
     */
    private boolean containsSongWithName(final List<Song> songs, final String name) {
        for (Song song : songs) {
            if (song.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if in an array with songs, there are two songs with the same name.
     *
     * @param songs array of songs
     * @return boolean
     */
    private boolean areDuplicates(final ArrayList<Song> songs) {
        for (int i = 0; i < songs.size() - 1; i++) {
            for (int j = i + 1; j < songs.size(); j++) {
                if (songs.get(i).getName().equals(songs.get(j).getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if a given date is valid
     *
     * @param date the date
     * @return boolean
     */
    private boolean isDateValid(final String date) {
        if (date.length() != DATE_LENGTH) {
            return false;
        }

        if (!Character.toString(date.charAt(FIRST_CHAR_DASH)).equals("-")
                || !Character.toString(date.charAt(SECOND_CHAR_DASH)).equals("-")) {
            return false;
        }

        int day = Integer.parseInt(date.substring(0, FIRST_CHAR_DASH));
        int month = Integer.parseInt(date.substring(FIRST_CHAR_DASH + 1, SECOND_CHAR_DASH));
        int year = Integer.parseInt(date.substring(SECOND_CHAR_DASH + 1, DATE_LENGTH));

        /*
         * Checks the year and the month.
         */
        if (year < FIRST_YEAR || year > LAST_YEAR || month < 1 || month > DECEMBER || day < 1) {
            return false;
        }

        if (month == FEBRUARY) {
            return day <= LAST_DAY_FEBRUARY;

        } else {
            return day <= LAST_DAY;
        }
    }

    /**
     * Add Album string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String addAlbum(final CommandInput commandInput) {
        if (albums.stream().anyMatch(album -> album.getName().equals(commandInput.getName()))) {
            return getUsername() + " has another album with the same name.";
        }

        ArrayList<Song> songs = new ArrayList<>();
        for (SongInput songInput : commandInput.getSongs()) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }

        if (areDuplicates(songs)) {
            return getUsername() + " has the same song at least twice in this album.";
        }

        for (Song song : songs) {
            if (!containsSongWithName(Admin.getSongs(), song.getName())) {
                Admin.addSong(song);
            }
        }

        Album album = new Album(commandInput.getName(), getUsername(),
                commandInput.getReleaseYear(), commandInput.getDescription());
        album.setSongs(songs);
        albums.add(album);

        return getUsername() + " has added new album successfully.";
    }

    /**
     * Remove album string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String removeAlbum(final CommandInput commandInput) {
        Album chosenAlbum = null;
        for (Album album : albums) {
            if (album.getName().equals(commandInput.getName())) {
                chosenAlbum = album;
                break;
            }
        }

        if (chosenAlbum == null) {
            return getUsername() + " doesn't have an album with the given name.";
        }

        for (NormalUser normalUser : Admin.getAllNormalUsers()) {
            if (normalUser.getPlayer().getSource() != null) { // daca are ceva in player
                switch (normalUser.getPlayer().getType()) { // daca in player e un
                    case "song":
                        for (Song song : chosenAlbum.getSongs()) {
                            if (normalUser.getPlayer().getSource().getAudioFile().equals(song)) {
                                return getUsername() + " can't delete this album.";
                            }
                        }
                        break;

                    case "album":
                        if (normalUser.getPlayer().getSource().getAudioCollection()
                                .equals(chosenAlbum)) {
                            return getUsername() + " can't delete this album.";
                        }

                        break;

                    default:
                }
            }

            for (Playlist playlist : normalUser.getPlaylists()) {
                for (Song playlistSong : playlist.getSongs()) {
                    for (Song albumSong : chosenAlbum.getSongs()) {
                        if (playlistSong.equals(albumSong)) {
                            return getUsername() + " can't delete this album.";
                        }
                    }
                }
            }
        }

        albums.remove(chosenAlbum);

        for (Song albumSong: chosenAlbum.getSongs()) {
            for (Song librarySong : Admin.getSongs()) {
                if (albumSong.getName().equals(librarySong.getName())) {
                    Admin.removeSong(librarySong);
                }
            }
        }

        return getUsername() +  " deleted the album successfully.";
    }

    /**
     * Add Event string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String addEvent(final CommandInput commandInput) {
        if (events.stream().anyMatch(event -> event.getName().equals(commandInput.getName()))) {
            return getUsername() + " has another event with the same name.";
        }

        if (!isDateValid(commandInput.getDate())) {
            return "Event for " + getUsername() + " does not have a valid date.";
        }

        events.add(new Event(commandInput.getName(), commandInput.getDescription(),
                commandInput.getDate()));

        return getUsername() + " has added new event successfully.";
    }

    /**
     * Remove Event string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String removeEvent(final CommandInput commandInput) {
        Event chosenEvent = null;

        for (Event event : events) {
            if (event.getName().equals(commandInput.getName())) {
                chosenEvent = event;
                break;
            }
        }

        if (chosenEvent == null) {
            return getUsername() + " doesn't have an event with the given name.";
        }

        events.remove(chosenEvent);

        return getUsername() + " deleted the event successfully.";
    }

    /**
     * Add Merch string.
     *
     * @param commandInput the command input
     * @return string
     */
    public String addMerch(final CommandInput commandInput) {
        if (marches.stream().anyMatch(merch -> merch.getName().equals(commandInput.getName()))) {
            return getUsername() + " has merchandise with the same name.";
        }

        if (commandInput.getPrice() < 0) {
            return "Price for merchandise can not be negative.";
        }

        marches.add(new Merch(commandInput.getName(), commandInput.getDescription(),
                commandInput.getPrice()));

        return getUsername() + " has added new merchandise successfully.";
    }

    /**
     * Calculates the total number of likes for all the songs in all the albums.
     *
     * @return total likes
     */
    public int getTotalArtistLikes() {
        int totalArtistLikes = 0;

        for (Album album : getAlbums()) {
            totalArtistLikes += album.getTotalLikes();
        }

        return totalArtistLikes;
    }
}
