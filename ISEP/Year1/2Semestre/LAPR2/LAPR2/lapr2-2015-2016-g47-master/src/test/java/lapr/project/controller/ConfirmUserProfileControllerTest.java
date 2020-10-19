/**
 * Package location for Controllers tests.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.User;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the confirmUserProfileController.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ConfirmUserProfileControllerTest {
    
    /**
     * The controller to be tested.
     */
    private ConfirmUserProfileController confirmUserProfileController;
    
    /**
     * The exhibition center
     */
    private ExhibitionCenter exhibitionCenter;
    
    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.confirmUserProfileController = new ConfirmUserProfileController(this.exhibitionCenter);
    }
    
    /**
    * Test confirms the register of a user.
    */
    @Test
    public void testConfirmRegisterOfUser() {
        //Arrange
        List <User> unconfirmedUsers = this.confirmUserProfileController.getUnconfirmedUserList();
        //simulate UI user choosing
        User user = unconfirmedUsers.get(0);
        //Act
        this.confirmUserProfileController.confirmUser(user);
        //Assert
        assertTrue(user.getConfirmedStatus());
    }
    
}
