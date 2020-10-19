/**
 * Package location for Controller concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;

/**
 * Represents the controller to confirm a user profile.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ConfirmUserProfileController {
    
    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;
    
    /**
     * The users register.
     */
    private UsersRegister usersRegister;
    
    /**
     * Constructs a confirm user profile controller.
     * 
     * @param exhibitionCenter
     */
    public ConfirmUserProfileController(ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
    }
    
    /**
     * Provides a list of unconfirmed users.
     * 
     * @return list of unconfirmed users
     */
    public List<User> getUnconfirmedUserList() {
        return exhibitionCenter.getUsersRegister().getUsersList(false);
    }
    
    /**
     * Confirms user.
     * 
     * @param user
     * @return true if no errors occur
     */
    public boolean confirmUser(User user) {
        user.setConfirmedStatus(true);
        return true;
    }
            
}
