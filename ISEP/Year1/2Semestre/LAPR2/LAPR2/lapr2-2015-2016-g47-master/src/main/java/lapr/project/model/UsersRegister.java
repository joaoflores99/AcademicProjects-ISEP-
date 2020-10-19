/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a users register to store users.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UsersRegister implements Serializable {

    /**
     * List of users.
     */
    private List<User> usersList;

    /**
     * Default constructor of a UsersRegister class.
     */
    public UsersRegister() {

        this.usersList = new ArrayList<>();
    }

    /**
     * Constructor of a UsersRegister class.
     *
     * @param usersList list of users
     */
    public UsersRegister(List<User> usersList) {

        this.usersList = new ArrayList<>(usersList);
    }

    /**
     * Copy constructor of a UsersRegister class.
     *
     * @param usersRegister UserRegister to copy
     */
    public UsersRegister(UsersRegister usersRegister) {

        this.usersList = new ArrayList<>(usersRegister.getUsersList());
    }

    /**
     * Obtain the users list.
     *
     * @return the users list
     */
    public List<User> getUsersList() {
        return new ArrayList<>(this.usersList);
    }

    /**
     * Obtain the users list.
     *
     * @param confirmed confirmed status of the users to fill the list
     * @return the users list
     */
    public List<User> getUsersList(boolean confirmed) {
        List<User> resultList = new ArrayList<>();
        for (User user : this.usersList) {
            if (user.getConfirmedStatus() == confirmed) {
                resultList.add(user);
            }
        }
        return resultList;
    }

    /**
     * Set the users list.
     *
     * @param usersList the users list to set
     */
    public void setUsersList(List<User> usersList) {
        this.usersList = new ArrayList<>(usersList);
    }

    /**
     * Registers a user
     *
     * @param user user to be registered
     * @return true if is successfully registered, false otherwise
     */
    public boolean registerUser(User user) {
        return !this.usersList.contains(user) ? addUser(user) : false;
    }

    /**
     * Creates a new user.
     *
     * @return new user
     */
    public User newUser() {
        return new User();
    }

    /**
     * Adds a user to the users list.
     *
     * @param user user to be added
     * @return true if it is successfully added, false otherwise
     */
    private boolean addUser(User user) {
        return this.usersList.add(user);
    }

    /**
     * Removes a user from the users list.
     *
     * @return true if user removed, false otherwise
     */
    private boolean removeUser(String username) {
        for (User user : this.usersList) {
            if (user.getUsername().equals(username)) {
                return this.usersList.remove(user);
            }
        }
        return false;
    }

    /**
     * Return the textual representation of a usersRegister.
     *
     * @return the textual representation of a usersRegister
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("UsersRegister{");
        for (User user : this.usersList) {
            s.append(String.format("%s%n", user));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Gets user by username.
     *
     * @param username
     * @return the user or null if inexistent
     */
    public User getUser(String username) {
        for (User user : this.usersList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Compares if the given object is equal to this users register.
     *
     * @param otherObject Object to compare
     * @return true if the objects are equals, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        UsersRegister otherUsersRegister = (UsersRegister) otherObject;

        return this.usersList.equals(otherUsersRegister.usersList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.usersList);
        return hash;
    }
}
