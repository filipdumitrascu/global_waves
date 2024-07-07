package app;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.users.NormalUser;
import app.users.Artist;
import app.users.Host;
import app.users.UserEntry;
import fileio.input.CommandInput;
import fileio.input.UserInput;
import fileio.input.SongInput;
import fileio.input.PodcastInput;
import fileio.input.EpisodeInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Admin.
 */
public final class Admin {
    private static List<Artist> artists;
    private static List<Host> hosts;
    private static List<NormalUser> normalUsers;
    private static List<Song> songs;
    private static List<Podcast> podcasts;
    private static int timestamp;
    private static final int LIMIT = 5;
    private static volatile Admin instance;

    /**
     * Instantiates a new Admin.
     */
    private Admin() {
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
        normalUsers = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        timestamp = 0;
    }

    /**
     * Anti-multiple threads singleton.
     *
     * @return instance
     */
    public static Admin getInstance() {
        Admin result = instance;

        if (result == null) {
            synchronized (Admin.class) {
                result = instance;
                if (result == null) {
                    instance = new Admin();
                    result = instance;
                }
            }
        }

        return result;
    }

    /**
     * Form all users by json input.
     *
     * @param userInputList the list of users
     */
    public void setUsers(final List<UserInput> userInputList) {
        for (UserInput userInput : userInputList) {
            normalUsers.add(new NormalUser(userInput.getUsername(), userInput.getAge(),
                    userInput.getCity()));
        }
    }

    /**
     * Form all songs by json input.
     *
     * @param songInputList the list of songs
     */
    public  void setSongs(final List<SongInput> songInputList) {
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    /**
     * Form all podcasts by json input.
     *
     * @param podcastInputList the list of podcasts
     */
    public void setPodcasts(final List<PodcastInput> podcastInputList) {
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                        episodeInput.getDuration(),
                        episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Adds a song in the system.
     *
     * @param song the song
     */
    public static void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the system.
     *
     * @param song the song
     */
    public static void removeSong(final Song song) {
        songs.remove(song);
    }

    /**
     * Adds a podcast to the system.
     *
     * @param podcast the podcast
     */
    public static void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Removes a podcast from the system.
     *
     * @param podcast the podcast
     */
    public static void removePodcast(final Podcast podcast) {
        podcasts.remove(podcast);
    }

    /**
     * Gets all the songs from the system.
     *
     * @return all songs
     */
    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Gets all the podcasts from the system.
     *
     * @return all the podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * Gets all the playlists from the system.
     *
     * @return all the playlists
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (NormalUser normalUser : normalUsers) {
            playlists.addAll(normalUser.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets all the albums from the system.
     *
     * @return all the albums
     */
    public static List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        for (Artist artist : artists) {
            albums.addAll(artist.getAlbums());
        }

        return albums;
    }

    /**
     * Gets all the artists from the system.
     *
     * @return all the artists
     */
    public static List<Artist> getArtists() {
        return new ArrayList<>(artists);
    }

    /**
     * Gets all the hosts from the system.
     *
     * @return all the hosts
     */
    public static List<Host> getHosts() {
        return new ArrayList<>(hosts);
    }

    /**
     * Gets all the normal users from the system.
     *
     * @return all the normal users
     */
    public static List<NormalUser> getAllNormalUsers() {
        return new ArrayList<>(normalUsers);
    }

    /**
     * Choose a normal user.
     *
     * @param username the username
     * @return the user
     */
    public static NormalUser getNormalUser(final String username) {
        for (NormalUser normalUser : normalUsers) {
            if (normalUser.getUsername().equals(username)) {
                return normalUser;
            }
        }
        return null;
    }

    /**
     * Choose an artist.
     *
     * @param username the username
     * @return teh artist
     */
    public static Artist getArtist(final String username) {
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                return artist;
            }
        }
        return null;
    }

    /**
     * Choose a host.
     *
     * @param username the username
     * @return the host
     */
    public static Host getHost(final String username) {
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                return host;
            }
        }
        return null;
    }

    /**
     * Prints at most LIMIT sorted songs.
     *
     * @param sortedSongs the sorted songs
     * @return strings
     */
    public static List<String> printLimitedSongs(final List<Song> sortedSongs) {
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Prints at most LIMIT sorted playlists.
     *
     * @param sortedPlaylists the sorted playlists
     * @return strings
     */
    public static List<String> printLimitedPlaylists(final List<Playlist> sortedPlaylists) {
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * Prints at most LIMIT sorted albums.
     *
     * @param sortedAlbums sorted albums
     * @return strings
     */
    public static List<String> printLimitedAlbums(final List<Album> sortedAlbums) {
        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= LIMIT) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     * Prints at most LIMIT sorted artists.
     *
     * @param sortedArtists sorted artists
     * @return the strings
     */
    public static List<String> printLimitedArtists(final List<Artist> sortedArtists) {
        List<String> topArtists = new ArrayList<>();
        int count = 0;
        for (Artist artist : sortedArtists) {
            if (count >= LIMIT) {
                break;
            }
            topArtists.add(artist.getUsername());
            count++;
        }
        return topArtists;
    }

    /**
     * The admin gets top 5 songs.
     *
     * @return the top
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        return printLimitedSongs(sortedSongs);
    }

    /**
     * The admin gets top 5 playlists.
     *
     * @return the top
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        return printLimitedPlaylists(sortedPlaylists);
    }

    /**
     * The admin gets top 5 albums.
     *
     * @return the top
     */
    public static List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(getAlbums());
        sortedAlbums.sort(Comparator.comparingInt(Album::getTotalLikes).reversed()
                .thenComparing(Album::getName, Comparator.naturalOrder()));
        return printLimitedAlbums(sortedAlbums);
    }

    /**
     * The admin gets top 5 artists.
     *
     * @return the top
     */
    public static List<String> getTop5Artists() {
        List<Artist> sortedArtists = new ArrayList<>(getArtists());
        sortedArtists.sort(Comparator.comparingInt(Artist::getTotalArtistLikes).reversed());
        return printLimitedArtists(sortedArtists);
    }

    /**
     * The admin gets all the online users.
     *
     * @return the strings
     */
    public static List<String> getOnlineUsers() {
        List<NormalUser> onlineUsers = new ArrayList<>(normalUsers);
        onlineUsers.removeIf(user -> !user.isOnline());

        List<String> usernames = new ArrayList<>();
        for (NormalUser normalUser : onlineUsers) {
            usernames.add(normalUser.getUsername());
        }

        return usernames;
    }

    /**
     * The admin adds a user to the system.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String addUser(final CommandInput commandInput) {

        if (getNormalUser(commandInput.getUsername()) != null
                || getArtist(commandInput.getUsername()) != null
                || getHost(commandInput.getUsername()) != null) {
            return "The username " + commandInput.getUsername() + " is already taken.";
        }

        switch (commandInput.getType()) {
            case "user":
                normalUsers.add(new NormalUser(commandInput.getUsername(), commandInput.getAge(),
                        commandInput.getCity()));
                break;

            case "artist":
                artists.add(new Artist(commandInput.getUsername(), commandInput.getAge(),
                        commandInput.getCity()));
                break;

            case "host":
                hosts.add(new Host(commandInput.getUsername(), commandInput.getAge(),
                        commandInput.getCity()));
                break;

            default:
                return "The user type is unknown";
        }

        return "The username " + commandInput.getUsername() + " has been added successfully.";
    }

    /**
     * If any other normal user has one of the chosen normal user's
     * playlists loaded, the chosen normal user can't be deleted.
     *
     * @param chosenNormalUser the chosen normal user to be deleted
     * @return the boolean
     */
    private static boolean hasPlaylistsLoaded(final NormalUser chosenNormalUser) {
        for (Playlist playlist : chosenNormalUser.getPlaylists()) {
            for (NormalUser otherNormalUser : Admin.getAllNormalUsers()) {
                if (!chosenNormalUser.equals(otherNormalUser)) {
                    if (otherNormalUser.getPlayer().getSource() != null
                            && otherNormalUser.getPlayer().getType().equals("playlist")) {
                        if (otherNormalUser.getPlayer().getSource().getAudioCollection()
                                .equals(playlist)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * If the chosen normal user can be deleted, for any other normal user,
     * removes from their list of followed playlists, the chosen normal user playlists
     * and decreases the number of followers for the playlists that are followed by
     * the chosen normal user.
     *
     * @param chosenNormalUser the chosen normal user to be deleted
     */
    private static void removeFollowedPlaylists(final NormalUser chosenNormalUser) {
        for (NormalUser otherNormalUser : Admin.getAllNormalUsers()) {
            if (!chosenNormalUser.equals(otherNormalUser)) {
                for (Playlist chosenNormalUserPlaylist : chosenNormalUser.getPlaylists()) {
                    if (otherNormalUser.getFollowedPlaylists().stream()
                            .anyMatch(followedPlaylist -> followedPlaylist.getName()
                                    .equals(chosenNormalUserPlaylist.getName()))) {
                        otherNormalUser.getFollowedPlaylists().remove(chosenNormalUserPlaylist);
                    }
                }

                for (Playlist chosenNormalUserFollowedPlaylist : chosenNormalUser
                        .getFollowedPlaylists()) {
                    for (Playlist playlist : otherNormalUser.getPlaylists()) {
                        if (playlist.getName().equals(chosenNormalUserFollowedPlaylist
                                .getName())) {
                            playlist.decreaseFollowers();
                        }
                    }
                }
            }
        }
    }

    /**
     * If any normal user has the artist's/host's page selected,
     * the artist/host can't be deleted.
     *
     * @param chosenUser the chosen artist/host to be deleted
     * @return the boolean
     */
    private static boolean isPageSelected(final UserEntry chosenUser) {
        for (NormalUser normalUser : Admin.getAllNormalUsers()) {
            if (normalUser.getSearchBar().getLastUserSelected() != null
                    && normalUser.getSearchBar().getLastUserSelected()
                    .equals(chosenUser)) {
                return true;
            }
        }
        return false;
    }

    /**
     * If any normal user has one of the artist's albums loaded, or has one of the
     * songs from his albums loaded or has a playlist loaded which has one of the songs
     * from artist's albums, than the artist can't be deleted.
     *
     * @param chosenArtist the chosen artist to be deleted
     * @return the boolean
     */
    private static boolean hasAlbumsLoaded(final Artist chosenArtist) {
        for (Album album : chosenArtist.getAlbums()) {
            for (NormalUser normalUser : Admin.getAllNormalUsers()) {
                if (normalUser.getPlayer().getSource() != null
                        && normalUser.getPlayer().getType().equals("album")) {
                    if (normalUser.getPlayer().getSource().getAudioCollection().equals(album)) {
                        return true;
                    }

                } else if (normalUser.getPlayer().getSource() != null
                        && normalUser.getPlayer().getType().equals("song")) {
                    for (Song song : album.getSongs()) {
                        if (normalUser.getPlayer().getSource().getAudioFile().equals(song)) {
                            return true;
                        }
                    }
                } else if (normalUser.getPlayer().getSource() != null
                        && normalUser.getPlayer().getType().equals("playlist")) {
                    for (Song albumSong : album.getSongs()) {
                        for (Song playlistSong : ((Playlist) normalUser.getPlayer().getSource()
                                .getAudioCollection()).getSongs()) {
                            if (albumSong.equals(playlistSong)) {
                                return true;
                            }
                        }

                    }
                }
            }
        }

        return false;
    }

    /**
     * If the chosen artist can be deleted, for every song on every album, removes it if
     * it is in the system, if it is in any normal user's playlist, if it is in any normal user's
     * followed playlists list and if it is in any normal user liked songs list.
     *
     * @param chosenArtist the chosen artist to be deleted
     */
    private static void removeAlbumSongs(final Artist chosenArtist) {
        for (Album album : chosenArtist.getAlbums()) {
            for (Song albumSong : album.getSongs()) {
                if (Admin.getSongs().stream().anyMatch(song -> song.getName()
                        .equals(albumSong.getName()))) {
                    Admin.removeSong(albumSong);
                }

                for (NormalUser normalUser : Admin.getAllNormalUsers()) {
                    for (Playlist playlist : normalUser.getPlaylists()) {
                        if (playlist.getSongs().stream().anyMatch(song -> song.getName()
                                .equals(albumSong.getName()))) {
                            playlist.removeSong(albumSong);
                        }
                    }

                    for (Playlist followedPlaylist : normalUser.getFollowedPlaylists()) {
                        if (followedPlaylist.getSongs().stream().anyMatch(song -> song.getName()
                                .equals(albumSong.getName()))) {
                            followedPlaylist.removeSong(albumSong);
                        }
                    }

                    if (normalUser.getLikedSongs().stream().anyMatch(song -> song.getName()
                            .equals(albumSong.getName()))) {
                        normalUser.getLikedSongs().remove(albumSong);
                    }
                }
            }
        }
    }

    /**
     * If any normal user has one of the host's podcasts loaded, the host can't be deleted.
     *
     * @param chosenHost the chosen host to be deleted
     * @return the boolean
     */
    private static boolean hasPodcastsLoaded(final Host chosenHost) {
        for (Podcast podcast : chosenHost.getPodcasts()) {
            for (NormalUser normalUser : Admin.getAllNormalUsers()) {
                if (normalUser.getPlayer().getSource() != null && normalUser.getPlayer().getType()
                        .equals("podcast")) {
                    if (normalUser.getPlayer().getSource().getAudioCollection().equals(podcast)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * If the chosen host can be deleted, removes the podcasts from the system.
     *
     * @param chosenHost the chosen host to be deleted
     */
    private static void removeHostPodcast(final Host chosenHost) {
        for (Podcast hostPodcast : chosenHost.getPodcasts()) {
            if (Admin.getPodcasts().stream().anyMatch(podcast -> podcast.getName()
                    .equals(hostPodcast.getName()))) {
                Admin.removePodcast(hostPodcast);
            }
        }
    }

    /**
     * The admin deletes a user from the system
     * (if no other user has an interaction with it at the moment).
     *
     * @param commandInput the command input
     * @return the system without the user
     */
    public static String deleteUser(final CommandInput commandInput) {
        NormalUser chosenNormalUser = getNormalUser(commandInput.getUsername());
        Artist chosenArtist = getArtist(commandInput.getUsername());
        Host chosenHost = getHost(commandInput.getUsername());
        String userType;

        if (chosenNormalUser == null && chosenArtist == null && chosenHost == null) {
            return "The username " + commandInput.getUsername() + " doesn't exist.";
        }

        if (chosenNormalUser != null) {
            userType = "normalUser";
        } else if (chosenArtist != null) {
            userType = "artist";
        } else {
            userType = "host";
        }

        switch (userType) {
            case "normalUser":
                if (hasPlaylistsLoaded(chosenNormalUser)) {
                    return commandInput.getUsername() + " can't be deleted.";
                }

                removeFollowedPlaylists(chosenNormalUser);

                normalUsers.remove(chosenNormalUser);
                break;

            case "artist":
                if (isPageSelected(chosenArtist) || hasAlbumsLoaded(chosenArtist)) {
                    return commandInput.getUsername() + " can't be deleted.";
                }

                removeAlbumSongs(chosenArtist);

                artists.remove(chosenArtist);
                break;

            case "host":
                if (isPageSelected(chosenHost) || hasPodcastsLoaded(chosenHost)) {
                    return commandInput.getUsername() + " can't be deleted.";
                }

                removeHostPodcast(chosenHost);

                hosts.remove(chosenHost);
                break;

            default:
        }


        return commandInput.getUsername() + " was successfully deleted.";
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (NormalUser normalUser : normalUsers) {
            if (normalUser.isOnline()) {
                normalUser.simulateTime(elapsed);
            }
        }
    }

    /**
     * Reset.
     */
    public void reset() {
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
        normalUsers = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        timestamp = 0;
    }
}
