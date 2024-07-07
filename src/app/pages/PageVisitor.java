package app.pages;

/**
 * The type PageVisitor.
 */
public interface PageVisitor {
    /**
     * Prints the home page.
     *
     * @param homePage the home page.
     * @return string
     */
    String printHomePage(HomePage homePage);

    /**
     * Prints the liked content page.
     *
     * @param likedContentPage the liked content page.
     * @return string
     */
    String printLikedContentPage(LikedContentPage likedContentPage);

    /**
     * Prints the host page.
     *
     * @param artistPage the artist page.
     * @return string
     */
    String printArtistPage(ArtistPage artistPage);

    /**
     * Prints the host page.
     *
     * @param hostPage the host page.
     * @return string
     */
    String printHostPage(HostPage hostPage);
}
