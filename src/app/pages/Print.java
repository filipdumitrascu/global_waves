package app.pages;

/**
 * The type Print.
 */
public class Print implements PageVisitor {
    /**
     * Instantiates a new Print.
     */
    public Print() {
    }

    /**
     * Prints the home page.
     *
     * @param homePage the home page
     * @return string
     */
    public String printHomePage(final HomePage homePage) {
        return "Liked songs:\n\t" + homePage.getRecommended5Songs() + "\n\nFollowed playlists:\n\t"
                + homePage.getRecommended5Playlists();
    }

    /**
     * Prints liked content page.
     *
     * @param likedContentPage the liked content page
     * @return string
     */
    public String printLikedContentPage(final LikedContentPage likedContentPage) {
        return "Liked songs:\n\t" + likedContentPage.getLikedSongsContent()
                + "\n\nFollowed playlists:\n\t" + likedContentPage.getFollowedPlaylistsContent();
    }

    /**
     * Prints the artist page.
     *
     * @param artistPage the artist page
     * @return string
     */
    public String printArtistPage(final ArtistPage artistPage) {
        return "Albums:\n\t" + artistPage.getAlbumNames() + "\n\nMerch:\n\t"
                + artistPage.getMarchesContent() + "\n\nEvents:\n\t"
                + artistPage.getEventsContent();
    }

    /**
     * Prints the host page.
     *
     * @param hostPage the host page
     * @return string
     */
    public String printHostPage(final HostPage hostPage) {
        return "Podcasts:\n\t" + hostPage.getPodcastsContent()
                + "\n\nAnnouncements:\n\t" + hostPage.getAnnouncementsContent();
    }
}
