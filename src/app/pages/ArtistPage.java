package app.pages;

import app.audio.Collections.Album;
import app.data.artist.Event;
import app.data.artist.Merch;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type ArtistPage.
 */
@Getter
public class ArtistPage extends Page {
    /**
     * -- GETTER --
     * Getter for albums, marches and events of the page.
     */
    private final ArrayList<Album> albums;
    private final ArrayList<Merch> marches;
    private final ArrayList<Event> events;

    /**
     * Instantiates a new Artist Page.
     *
     * @param albums   the albums
     * @param marches  the marches
     * @param events   the events
     */
    public ArtistPage(final ArrayList<Album> albums, final ArrayList<Merch> marches,
                      final ArrayList<Event> events) {
        super();
        this.albums = albums;
        this.marches = marches;
        this.events = events;
    }

    /**
     * Gets the album names.
     *
     * @return the album names
     */
    public List<String> getAlbumNames() {
        List<String> results = new ArrayList<>();

        for (Album album : albums) {
            results.add(album.getName());
        }
        return results;
    }

    /**
     * Gets the marches content.
     *
     * @return the content
     */
    public List<String> getMarchesContent() {
        List<String> results = new ArrayList<>();

        for (Merch merch : marches) {
            results.add(merch.getName() + " - " + merch.getPrice() + ":\n\t"
                    + merch.getDescription());
        }

        return results;
    }

    /**
     * Gets the events content.
     *
     * @return the content
     */
    public List<String> getEventsContent() {
        List<String> results = new ArrayList<>();

        for (Event event : events) {
            results.add(event.getName() + " - " + event.getDate() + ":\n\t"
                    + event.getDescription());
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
        return visitor.printArtistPage(this);
    }
}
