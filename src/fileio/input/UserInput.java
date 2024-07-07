package fileio.input;

import lombok.Getter;
import lombok.Setter;

/**
 * The type UserInput, used to store the ObjectMapper input for users.
 */
@Getter
@Setter
public final class UserInput {
    /**
     * -- GETTER && SETTER for --
     * username, age and city for the user
     */
    private String username;
    private int age;
    private String city;

    /**
     * Instantiates a new UserInput.
     */
    public UserInput() {
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "UserInput{"
                + "username='" + username + '\''
                + ", age=" + age
                + ", city='" + city + '\''
                + '}';
    }
}
