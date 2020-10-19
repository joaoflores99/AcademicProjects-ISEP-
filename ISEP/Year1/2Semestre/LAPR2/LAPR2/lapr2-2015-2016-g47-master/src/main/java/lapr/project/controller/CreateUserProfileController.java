/**
 * Package location for Controller concepts.
 */
package lapr.project.controller;

import lapr.project.model.ExhibitionCenter;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;

/**
 * Represents the controller for a create user profile controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateUserProfileController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The users register.
     */
    private UsersRegister usersRegister;

    /**
     * The user.
     */
    private User user;

    /**
     * Constructs a create user profile controller.
     *
     * @param exhibitionCenter
     */
    public CreateUserProfileController(ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
    }

    /**
     * Creates a new user.
     */
    public void newUser() {
        this.usersRegister = this.exhibitionCenter.getUsersRegister();
        this.user = this.usersRegister.newUser();
    }

    /**
     * Sets the user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.usersRegister = this.exhibitionCenter.getUsersRegister();
        this.user = user;
    }

    /**
     * Gets the user
     *
     * @return the user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Sets user information data
     *
     * @param name
     * @param username
     * @param email
     * @param password
     * @param userCypher
     * @return true if a user is valid and false otherwise
     */
    public boolean setUserData(String name, String username, String email, String password, String userCypher) {
        this.user.setUserCypher(userCypher);
        this.user.setName(name);
        this.user.setUsername(username);
        this.user.setEmail(email);
        this.user.setPassword(password);

        return this.user.validate();
    }

    /**
     * Gets the user's register.
     *
     * @return the user's register
     */
    public UsersRegister getUsersRegister() {
        return this.usersRegister;
    }

    /**
     * Register a user
     *
     * @return true if it successful registered, and false otherwise
     */
    public boolean registerUser() {
        boolean result = this.usersRegister.registerUser(this.user);

        exhibitionCenter.setUsersRegister(usersRegister);
        if (!exhibitionCenter.getExhibitionsManagerRegister().hasAnyExhibitionManager()) {
            exhibitionCenter.getExhibitionsManagerRegister().addExhibitionManager(user);
            user.setConfirmedStatus(true);
        }
        return result;
    }

}
