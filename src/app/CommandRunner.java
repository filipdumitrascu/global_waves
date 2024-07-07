package app;

import app.audio.Collections.Album;
import app.audio.Collections.outputs.AlbumOutput;
import app.audio.Collections.outputs.PlaylistOutput;
import app.audio.Collections.outputs.PodcastOutput;
import app.audio.Collections.Podcast;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.users.Artist;
import app.users.Host;
import app.users.NormalUser;
import app.users.UserEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Instantiates a new CommandRunner.
     */
    private CommandRunner() {
    }

    /**
     * Assigns the command, user and timestamp in the objectNode for the output
     *
     * @param objectNode the objectNode
     * @param commandInput the commandInput
     */
    private static void assignInObjectNode(final ObjectNode objectNode,
                                           final CommandInput commandInput) {
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
    }

    /**
     * Checks if the user is offline
     *
     * @param normalUser a normal user
     * @param objectNode the object node
     * @return boolean
     */
    private static boolean normalUserIsOffline(final NormalUser normalUser,
                                               final ObjectNode objectNode) {
        if (!normalUser.isOnline()) {
            objectNode.put("message", normalUser.getUsername() + " is offline.");
            return true;
        }

        return false;
    }

    /**
     * Checks if the user is not a normal one
     *
     * @param objectNode the object node
     * @param commandInput the command input
     * @return boolean
     */
    private static boolean isNotANormalUser(final ObjectNode objectNode,
                                        final CommandInput commandInput) {
        if (Admin.getArtist(commandInput.getUsername()) != null
                || Admin.getHost(commandInput.getUsername()) != null) {
            objectNode.put("message", commandInput.getUsername() + " is not a normal user.");
            return true;
        }

        return false;
    }

    /**
     * Checks if the user is not an artist
     *
     * @param objectNode the object node
     * @param commandInput the command input
     * @return boolean
     */
    private static boolean isNotAnArtist(final ObjectNode objectNode,
                                    final CommandInput commandInput) {
        if (Admin.getNormalUser(commandInput.getUsername()) != null
                || Admin.getHost(commandInput.getUsername()) != null) {
            objectNode.put("message", commandInput.getUsername() + " is not an artist.");
            return true;
        }

        return false;
    }

    /**
     * Checks if the user is not a host
     *
     * @param objectNode the object node
     * @param commandInput the command input
     * @return boolean
     */
    private static boolean isNotAHost(final ObjectNode objectNode,
                                    final CommandInput commandInput) {
        if (Admin.getNormalUser(commandInput.getUsername()) != null
                || Admin.getArtist(commandInput.getUsername()) != null) {
            objectNode.put("message", commandInput.getUsername() + " is not a host.");
            return true;
        }

        return false;
    }

    /**
     * Checks if the user exists in the system
     *
     * @param objectNode the object node
     * @param commandInput the command input
     * @param user the user
     * @return boolean
     */
    private static boolean doesUserNotExists(final ObjectNode objectNode,
                                            final CommandInput commandInput, final UserEntry user) {
        if (user == null) {
            objectNode.put("message", "The username " + commandInput.getUsername()
                    + " doesn't exist.");
            return true;
        }

        return false;
    }

    /**
     * A normal user searches for audio files or collections.
     *
     * @param commandInput the command input
     * @return the searched entities
     */
    public static ObjectNode search(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode objectNode = objectMapper.createObjectNode();
        ArrayList<String> results = new ArrayList<>();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            objectNode.put("results", objectMapper.valueToTree(results));
            return objectNode;
        }

        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();
        results = normalUser.search(filters, type);

        objectNode.put("message", "Search returned " + results.size() + " results");
        objectNode.put("results", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * A normal user selects an audio file or an audio collection.
     *
     * @param commandInput the command input
     * @return the selected entity
     */
    public static ObjectNode select(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.select(commandInput.getItemNumber()));

        return objectNode;
    }

    /**
     * A normal user loads an audio collection or an audio file in the player.
     *
     * @param commandInput the command input
     * @return the player
     */
    public static ObjectNode load(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.load());

        return objectNode;
    }

    /**
     * A normal user uses the play/pause button in the player.
     *
     * @param commandInput the command input
     * @return the player
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.playPause());

        return objectNode;
    }

    /**
     * A normal user uses the repeat button in the player.
     *
     * @param commandInput the command input
     * @return the player
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.repeat());

        return objectNode;
    }

    /**
     * A normal user shuffles his loaded collection.
     *
     * @param commandInput the command input
     * @return the shuffled loaded collection
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        Integer seed = commandInput.getSeed();
        objectNode.put("message", normalUser.shuffle(seed));

        return objectNode;
    }

    /**
     * A normal user uses the forward button in the player.
     *
     * @param commandInput the command input
     * @return the player
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.forward());

        return objectNode;
    }

    /**
     * A normal user uses the backward button in the player.
     *
     * @param commandInput the command input
     * @return the player
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.backward());

        return objectNode;
    }

    /**
     * A normal user likes his loaded song.
     *
     * @param commandInput the command input
     * @return the like
     */
    public static ObjectNode like(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.like());

        return objectNode;
    }

    /**
     * A normal user uses the next button in the player.
     *
     * @param commandInput the command input
     * @return the player
     */
    public static ObjectNode next(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.next());

        return objectNode;
    }

    /**
     * A normal user uses the previous button in the player.
     *
     * @param commandInput the command input
     * @return the player
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.prev());

        return objectNode;
    }

    /**
     * A normal user creates a playlist.
     *
     * @param commandInput the command input
     * @return the playlist
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.createPlaylist(commandInput.getPlaylistName(),
                commandInput.getTimestamp()));

        return objectNode;
    }

    /**
     * A normal user adds or remove a song in playlist.
     *
     * @param commandInput the command input
     * @return the playlist
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.addRemoveInPlaylist(commandInput.getPlaylistId()));

        return objectNode;
    }

    /**
     * A normal user switches the visibility of one of his playlists.
     *
     * @param commandInput the command input
     * @return the visibility
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message",
                normalUser.switchPlaylistVisibility(commandInput.getPlaylistId()));

        return objectNode;
    }

    /**
     * A normal user shows his playlists.
     *
     * @param commandInput the command input
     * @return the playlists
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = normalUser.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * A normal user (un)follows a playlist from other users.
     *
     * @param commandInput the command input
     * @return the followed playlist
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ObjectNode  objectNode = objectMapper.createObjectNode();

        assignInObjectNode(objectNode, commandInput);

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.follow());

        return objectNode;
    }

    /**
     * A normal user asks for his player status.
     *
     * @param commandInput the command input
     * @return the status
     */
    public static ObjectNode status(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        PlayerStats stats = normalUser.getPlayerStats();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * A normal user show his liked songs.
     *
     * @param commandInput the command input
     * @return the liked songs
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        ArrayList<String> songs = normalUser.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * A normal user gets his preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        String preferredGenre = normalUser.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * The Admin gets the top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * The Admin gets the top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * A normal user changes the current page.
     *
     * @param commandInput the command input
     * @return the new current page
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotANormalUser(objectNode, commandInput)) {
            return objectNode;
        }

        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, normalUser)) {
            return objectNode;
        }

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.changePage(commandInput));

        return objectNode;
    }

    /**
     * A normal user prints his current page.
     *
     * @param commandInput the command input
     * @return the printed page
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotANormalUser(objectNode, commandInput)) {
            return objectNode;
        }

        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, normalUser)) {
            return objectNode;
        }

        if (normalUserIsOffline(normalUser, objectNode)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.printCurrentPage());

        return objectNode;
    }

    /**
     * The admin adds a new user in the system.
     *
     * @param commandInput the command input
     * @return a new user
     */
    public static ObjectNode addUser(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);
        objectNode.put("message", Admin.addUser(commandInput));

        return objectNode;
    }

    /**
     * The admin deletes one user from the system.
     *
     * @param commandInput the command input
     * @return the system without the user
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);
        objectNode.put("message", Admin.deleteUser(commandInput));

        return objectNode;
    }

    /**
     * An artist shows his albums.
     *
     * @param commandInput the command input
     * @return the albums
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        Artist artist = Admin.getArtist(commandInput.getUsername());
        ArrayList<AlbumOutput> result = new ArrayList<>();

        for (Album album : artist.getAlbums()) {
            result.add(new AlbumOutput(album));
        }

        objectNode.put("result", objectMapper.valueToTree(result));

        return objectNode;
    }

    /**
     * A host shows his podcasts.
     *
     * @param commandInput the command input
     * @return the podcasts
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        Host host = Admin.getHost(commandInput.getUsername());
        ArrayList<PodcastOutput> result = new ArrayList<>();

        for (Podcast podcast : host.getPodcasts()) {
            result.add(new PodcastOutput(podcast));
        }

        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;
    }

    /**
     * An artist adds an album to his page.
     *
     * @param commandInput the command input
     * @return a new album
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAnArtist(objectNode, commandInput)) {
            return objectNode;
        }

        Artist artist = Admin.getArtist(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, artist)) {
            return objectNode;
        }

        objectNode.put("message", artist.addAlbum(commandInput));

        return objectNode;
    }

    /**
     * An artist removes an album from his page.
     *
     * @param commandInput the command input
     * @return the page without the album
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAnArtist(objectNode, commandInput)) {
            return objectNode;
        }

        Artist artist = Admin.getArtist(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, artist)) {
            return objectNode;
        }

        objectNode.put("message", artist.removeAlbum(commandInput));

        return objectNode;
    }

    /**
     * An artist adds an event to his page.
     *
     * @param commandInput the command input
     * @return the event
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAnArtist(objectNode, commandInput)) {
            return objectNode;
        }

        Artist artist = Admin.getArtist(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, artist)) {
            return objectNode;
        }

        objectNode.put("message", artist.addEvent(commandInput));

        return objectNode;

    }

    /**
     * An artist removes an event from his page.
     *
     * @param commandInput the command input
     * @return the page without the event
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAnArtist(objectNode, commandInput)) {
            return objectNode;
        }

        Artist artist = Admin.getArtist(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, artist)) {
            return objectNode;
        }

        objectNode.put("message", artist.removeEvent(commandInput));

        return objectNode;
    }

    /**
     * An artist adds a merch to his page.
     *
     * @param commandInput the command input
     * @return a merch
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAnArtist(objectNode, commandInput)) {
            return objectNode;
        }

        Artist artist = Admin.getArtist(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, artist)) {
            return objectNode;
        }

        objectNode.put("message", artist.addMerch(commandInput));

        return objectNode;
    }

    /**
     * A host adds a podcast to his page.
     *
     * @param commandInput the command input
     * @return a podcast
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAHost(objectNode, commandInput)) {
            return objectNode;
        }

        Host host = Admin.getHost(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, host)) {
            return objectNode;
        }

        objectNode.put("message", host.addPodcast(commandInput));

        return objectNode;
    }

    /**
     * A host removes a podcast from his page.
     *
     * @param commandInput the command input
     * @return the page without the podcast
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAHost(objectNode, commandInput)) {
            return objectNode;
        }

        Host host = Admin.getHost(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, host)) {
            return objectNode;
        }

        objectNode.put("message", host.removePodcast(commandInput));

        return objectNode;
    }

    /**
     * A host adds an announcement to his page.
     *
     * @param commandInput the command input
     * @return an announcement
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAHost(objectNode, commandInput)) {
            return objectNode;
        }

        Host host = Admin.getHost(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, host)) {
            return objectNode;
        }

        objectNode.put("message", host.addAnnouncement(commandInput));

        return objectNode;
    }

    /**
     * A host removes an announcement from his page.
     *
     * @param commandInput the command input
     * @return the page without the announcement
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotAHost(objectNode, commandInput)) {
            return objectNode;
        }

        Host host = Admin.getHost(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, host)) {
            return objectNode;
        }

        objectNode.put("message", host.removeAnnouncement(commandInput));

        return objectNode;
    }

    /**
     * A normal user changes his status in the system.
     *
     * @param commandInput the command input
     * @return new connection status
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        assignInObjectNode(objectNode, commandInput);

        if (isNotANormalUser(objectNode, commandInput)) {
            return objectNode;
        }

        NormalUser normalUser = Admin.getNormalUser(commandInput.getUsername());
        if (doesUserNotExists(objectNode, commandInput, normalUser)) {
            return objectNode;
        }

        objectNode.put("message", normalUser.switchConnectionStatus());

        return objectNode;
    }

    /**
     * The Admin gets the top 5 albums from the system.
     *
     * @param commandInput the command input
     * @return the top 5 albums
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        List<String> result = Admin.getTop5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;
    }

    /**
     * The Admin gets the top 5 artists from the system.
     *
     * @param commandInput the command input
     * @return the top 5 artists
     */
    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        List<String> result = Admin.getTop5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;
    }

    /**
     * The Admin gets all the users from the system.
     *
     * @param commandInput the command input
     * @return all users
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayList<String> result = new ArrayList<>();

        for (NormalUser normalUser : Admin.getAllNormalUsers()) {
            result.add(normalUser.getUsername());
        }

        for (Artist artist : Admin.getArtists()) {
            result.add(artist.getUsername());
        }

        for (Host host : Admin.getHosts()) {
            result.add(host.getUsername());
        }

        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;
    }

    /**
     * The Admin gets all the online users from the system.
     *
     * @param commandInput the command input
     * @return the online users
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        List<String> onlineUsers = Admin.getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));

        return objectNode;
    }
}
