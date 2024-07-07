package app.player;

import app.utils.Enums;
import lombok.Getter;

/**
 * The type PLayer Stats.
 */
@Getter
public class PlayerStats {
    /**
     * -- GETTER --
     * Getter for name, remainedTime, repeat,
     * shuffle and indexShuffled.
     */
    private final String name;
    private final int remainedTime;
    private final String repeat;
    private final boolean shuffle;
    private final boolean paused;

    /**
     * Instantiates new Player Stats.
     *
     * @param name the name
     * @param remainedTime the remained time
     * @param repeatMode the repeat mode
     * @param shuffle the shuffle status
     * @param paused the paused status
     */
    public PlayerStats(final String name,
                       final int remainedTime,
                       final Enums.RepeatMode repeatMode,
                       final boolean shuffle,
                       final boolean paused) {
        this.name = name;
        this.remainedTime = remainedTime;
        this.paused = paused;
        switch (repeatMode) {
            case REPEAT_ALL -> {
                this.repeat = "Repeat All";
            }
            case REPEAT_ONCE -> {
                this.repeat = "Repeat Once";
            }
            case REPEAT_INFINITE -> {
                this.repeat = "Repeat Infinite";
            }
            case REPEAT_CURRENT_SONG -> {
                this.repeat = "Repeat Current Song";
            }
            case NO_REPEAT -> {
                this.repeat = "No Repeat";
            }
            default -> {
                this.repeat = "";
            }
        }
        this.shuffle = shuffle;
    }

}
