/**
 * Package location for Controller concepts.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;

/**
 * Represents the controller for an update user profile controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UpdateUserProfileController {
    
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
     * Constructs an update user profile controller.
     * 
     * @param exhibitionCenter
     */
    public UpdateUserProfileController(ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
    }
    
    /**
     * Gets the user by username.
     * 
     * @param username the user's username
     * @return the user to be updated
     */
    public User getUser(String username) {
        this.usersRegister = this.exhibitionCenter.getUsersRegister();
        this.user = this.usersRegister.getUser(username);
        return this.user;
    }
    
    /**
     * Update user data.
     * 
     * @param name
     * @param username
     * @param email
     * @param password
     * @return true if valid or false otherwise
     */
    public String setUserData( String name, String username, String email, String password) {
        User userClone = new User(this.user);
        List<User> userListClone = new ArrayList<>(this.usersRegister.getUsersList());
        
        userClone.setName(name);
        userClone.setUsername(username);
        userClone.setEmail(email);
        userClone.setPassword(password);
        
        if(userClone.validate()) {
            for(User user : userListClone) {
                if(user.getUsername().equals(this.user.getUsername())){
                    userListClone.remove(user);
                    break;
                }
            }
            
            if(!userListClone.contains(userClone) && userListClone.add(userClone)) {
                this.usersRegister.setUsersList(userListClone);
                return "Success";
            }
            else {
                return "Username/email duplicated";
            }
        }
        
        return "Invalid username/email";
    }
    
    /**
     * Register a user
     * 
     * @return true if it successful registered, and false otherwise
     */
    public boolean registerUser() {
        return this.usersRegister.registerUser(this.user);
    }
    
}
