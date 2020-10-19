/**
 * Package location for Model concept tests.
 */
package lapr.project.controller;

import lapr.project.model.ExhibitionCenter;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the create user profile controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateUserProfileControllerTest {
    
    /**
     * The controller to be tested.
     */
    private CreateUserProfileController createUserProfileController;
    
    /**
     * The exhibition center
     */
    private ExhibitionCenter exhibitionCenter;

    
    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.createUserProfileController = new CreateUserProfileController(this.exhibitionCenter);
    }
    
    /**
     * Test user creation with invalid name.
     */
    @Test
    public void testNewUserUsernameInvalid() {
        System.out.print("testNewUserNameInvalid");
        //Arrange
        String name = "test";
        String username = "te";
        String email = "test@b.com";
        String password = "testpassword";
        String userCypher = "wdwd";
        //Act      
        this.createUserProfileController.newUser();
        boolean result = this.createUserProfileController.setUserData(name, username, email, password, userCypher);
        //Assert
        assertFalse(result);
    }
    
    /**
     * Test user creation with invalid name.
     */
    @Test
    public void testNewEmailInvalid() {
        System.out.print("testNewUserNameInvalid");
        //Arrange
        String name = "test";
        String username = "teere";
        String email = "tewe";
        String password = "testpassword";
        String userCypher = "wewew";
        //Act      
        this.createUserProfileController.newUser();
        boolean result = this.createUserProfileController.setUserData(name, username, email, password, userCypher);
        //Assert
        assertFalse(result);
    }    
    
    /**
     * Test user creation with invalid name.
     */
    @Test
    public void testNewUserValid() {
        System.out.print("testNewUserNameInvalid");
        //Arrange
        String name = "test";
        String username = "te";
        String email = "test@b.com";
        String password = "testpassword";
        String userCypher = "wdwdq";
        //Act      
        this.createUserProfileController.newUser();
        boolean result = this.createUserProfileController.setUserData(name, username, email, password, userCypher);
        //Assert
        assertFalse(result);
    }
    
    /**
     * Test valid user registration.
     */
    @Test
    public void testValidUserRegistration() {
        System.out.print("testValidUserRegistration");
        //Arrange
        String name = "test";
        String username = "te";
        String email = "test@b.com";
        String password = "testpassword";
        String userCypher = "dwdae";
        //Act      
        this.createUserProfileController.newUser();
        this.createUserProfileController.setUserData(name, username, email, password, userCypher);
        boolean result = this.createUserProfileController.registerUser();
        //Assert
        assertTrue(result);
    }
    
//     /**
//     * Test invalid user registration.
//     */
//    @Test
//    public void testInvalidUserRegistration() {
//        System.out.print("testInvalidUserRegistration");
//        //Arrange
//        User user = this.exhibitionCenter.getUsersRegister().getUsersList().get(0);
//        //Act
//        this.createUserProfileController.newUser();
//        this.createUserProfileController.setUserData(user.getName(), user.getUsername(), user.getEmail(), user.getPassword());
//        boolean result = this.createUserProfileController.registerUser();
//        //Assert
//        assertFalse(result);
//    }
    
}
