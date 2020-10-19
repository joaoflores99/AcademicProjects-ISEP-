/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.utils.Encrypter;

/**
 * Represents a user.
 *
 * @author Daniel Goncalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Selectable, Serializable {

    /**
     * The user's name.
     */
    private String name;

    /**
     * The user's username.
     */
    @XmlAttribute
    private String username;

    /**
     * The user's email.
     */
    private String email;

    /**
     * The user's password.
     */    
    private String password;
    
    /**
     * The users shift for encryption.
     */    
    private int userShift;
    
    /**
    * The user's cypher.
    */
    private String userCypher;
    
    /**
     * The user's confirmed register status.
     *
     * By default this parameter is initialized as false
     */
    private boolean confirmedStatus;

    /**
     * List of related users.
     */
    private List<User> relatedUsers;

    /**
     * The user's name by default.
     */
    private static final String DEFAULT_NAME = "no name";

    /**
     * The user's username by default.
     */
    private static final String DEFAULT_USERNAME = "username";

    /**
     * The user's email by default.
     */
    private static final String DEFAULT_EMAIL = "me@email.com";

    /**
     * The user's password by default.
     */
    private static final String DEFAULT_PASSWORD = "password";
    
    /**
     * The default Cypher.
     */
    private static final String DEFAULT_CYPHER ="";

    /**
     * Default constructor of a user class.
     */
    public User() {
        this.name = DEFAULT_NAME;
        this.username = DEFAULT_USERNAME;
        this.email = DEFAULT_EMAIL;
        this.password = DEFAULT_PASSWORD;
        this.relatedUsers = new ArrayList<>();
        this.userShift =  new Random().nextInt(20);
        this.userCypher = DEFAULT_CYPHER;
    }

    /**
     * Constructor of a user class.
     *
     * @param name user's name
     * @param username user's username
     * @param email user's email
     * @param password user's password
     * @param relatedUsers list of relatives
     * @param userCypher the user's cypher
     */
    public User(String name, String username, String email, String password, List<User> relatedUsers,String userCypher) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.relatedUsers = new ArrayList<>(relatedUsers);
        this.userShift =  new Random().nextInt(19)+1;
        this.userCypher = userCypher;
    }

    /**
     * Copy constructor of a UsersRegister class.
     *
     * @param user User to copy
     */
    public User(User user) {
        this.name = user.name;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.confirmedStatus = user.confirmedStatus;
        this.relatedUsers = new ArrayList<>(user.getRelatedUsers());
        this.userShift =  user.userShift;
        this.userCypher = user.userCypher;
    }

    /**
     * Obtain the user's name.
     *
     * @return the user's name
     */
    public String getName() {
        return Encrypter.decryptStringKeyword(name, userShift, userCypher);
    }

    /**
     * Set the user's name.
     *
     * @param name the user's name to set
     */
    public void setName(String name) {
        this.name = Encrypter.encryptStringKeyword(name, userShift, userCypher);
    }

    /**
     * Obtain the user's username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user's username.
     *
     * @param username the user's username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtain the user's email.
     *
     * @return the user's email
     */
    public String getEmail() {
        return Encrypter.decryptStringKeyword(email, userShift, userCypher);
    }

    /**
     * Set the user's email.
     *
     * @param email the user's email to set
     */
    public void setEmail(String email) {
        this.email = Encrypter.encryptStringKeyword(email, userShift, userCypher);
    }

    /**
     * Sets the user cypher.
     * @param userCypher the user cypher
     */
    public void setUserCypher(String userCypher) {
        this.userCypher = userCypher;
    }

    /**
     * Obtain the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's name.
     *
     * @param password the user's password to set
     */
    public void setPassword(String password) {
        this.password = Encrypter.encryptStringCaesar(password, userShift);
    }

    /**
     * Obtain the user's confirmed status.
     *
     * @return the user's confirmedStatus
     */
    public boolean getConfirmedStatus() {
        return confirmedStatus;
    }

    /**
     * Sets the user's confirmed status.
     *
     * @param confirmedStatus the user's confirmed status to set
     */
    public void setConfirmedStatus(boolean confirmedStatus) {
        this.confirmedStatus = confirmedStatus;
    }

    /**
     * Obtain the related users list.
     *
     * @return the related users list
     */
    public List<User> getRelatedUsers() {
        return new ArrayList<>(this.relatedUsers);
    }

    /**
     * Set the related users list.
     *
     * @param relatedUsers the related users list to set
     */
    public void setRelatedUsers(List<User> relatedUsers) {
        this.relatedUsers = new ArrayList<>(relatedUsers);
    }

    
    public boolean comparePassword(String password){
       return password.equals(Encrypter.decryptStringCaesar(getPassword(), userShift));
    }
    /**
     * Validates the user.
     *
     * @return true if the user is valid, false otherwise
     */
    public boolean validate() {
        return !this.username.isEmpty()
                && this.username.length() > 3
                && !this.email.isEmpty()
                && this.email.length() > 5;
    }

    /**
     * Return the textual representation of a user.
     *
     * @return the textual representation of a user
     */
    @Override
    public String toString() {
        return String.format("User{%nname=%s%nusername=%s%nemail=%s}", this.name, this.username, this.email);
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it represents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        User otherUser = (User) otherObject;

        return this.username.equals(otherUser.username) || this.email.equals(otherUser.email);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return String.format("%s (%s)", this.getName(), this.getUsername());
    }

}
