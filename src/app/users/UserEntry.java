package app.users;

import lombok.Getter;

/**
 * The type User.
 */
@Getter
public class UserEntry {
    /**
     * -- GETTER --
     * Getter for username, age and city of the user.
     */
    private final String username;
    private final int age;
    private final String city;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public UserEntry(final String username, final int age, final String city) {
        this.username = username;
        this.age = age;
        this.city = city;
    }

    /**
     * Checks if the username starts with the given string
     *
     * @param nameFilter input string for the match
     * @return boolean
     */
    public boolean matchesName(final String nameFilter) {
        return getUsername().toLowerCase().startsWith(nameFilter.toLowerCase());
    }
}
