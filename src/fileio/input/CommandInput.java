package fileio.input;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type CommandInput, used to store the ObjectMapper input for commands.
 */
@Getter
@Setter
public final class CommandInput {
    /**
     * -- GETTER && SETTER --
     * for:
     *  command -> which command to execute
     *  username -> who does the command / who to add or remove
     *  timestamp -> the second the command is executed
     *  type -> what to search (song / playlist / podcast / album / artist / host)
     *  filters -> filters applied to the search
     *  itemNumber -> which search result to select
     *  seed -> what seed to shuffle with
     *  playlistId -> in which playlist to add/remove/switchVisibility/
     *  playlistName -> the name of the playlist to be created
     *  nextPage -> which page the user moves to
     *  age -> user's age
     *  city -> user's city
     *  name -> entity's name
     *  releaseYear -> entity's releaseYear
     *  description -> entity's description
     *  songs -> album's songs
     *  date -> event's date
     *  price -> march's price
     *  episodes -> host podcast's episodes
     */
    private String command;
    private String username;
    private Integer timestamp;
    private String type;
    private FiltersInput filters;
    private Integer itemNumber;
    private Integer playlistId;
    private String playlistName;
    private Integer seed;
    private String nextPage;
    private int age;
    private String city;
    private String name;
    private int releaseYear;
    private String description;
    private List<SongInput> songs = new ArrayList<>();
    private String date;
    private int price;
    private List<EpisodeInput> episodes;

    /**
     * Instantiates a new CommandInput.
     */
    public CommandInput() {
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "CommandInput{"
                + "command='" + command + '\''
                + ", username='" + username + '\''
                + ", timestamp=" + timestamp
                + ", type='" + type + '\''
                + ", filters=" + filters
                + ", itemNumber=" + itemNumber
                + ", playlistId=" + playlistId
                + ", playlistName='" + playlistName + '\''
                + ", seed=" + seed
                + ", nextPage='" + nextPage + '\''
                + ", age=" + age
                + ", city='" + city + '\''
                + ", name='" + name + '\''
                + ", releaseYear=" + releaseYear
                + ", description='" + description + '\''
                + ", songs=" + songs
                + ", date='" + date + '\''
                + ", price=" + price
                + ", episodes=" + episodes
                + '}';
    }
}
