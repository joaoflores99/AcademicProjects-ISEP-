/**
 * Package location for Controller concept tests.
 */
package lapr.project.controller;

import static junit.framework.Assert.assertEquals;
import lapr.project.model.ExhibitionCenter;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the update user profile controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UpdateUserProfileControllerTest {
    
    /**
     * The controller to be tested.
     */
    private UpdateUserProfileController updateUserProfileController;
    
    /**
     * The exhibition center.
     */
    private ExhibitionCenter exhibitionCenter;
    
    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.updateUserProfileController = new UpdateUserProfileController(this.exhibitionCenter);
    }
    
    /**
     * Test user update with invalid username.
     */
    @Test
    public void testUpdateUserUsernameInvalid() {
        System.out.println("testUpdateUserUsernameInvalid");
        //Arrange
        String name = "teste1";
        String username = "te1";
        String email = "teste1@b.com";
        String password = "teste1password";
        //Act
        this.updateUserProfileController.getUser("ricardocorreia");
        String result = this.updateUserProfileController.setUserData(name, username, email, password);
        //Assert
        assertEquals(result, "Invalid username/email");
    }
    
    /**
     * Test user update with invalid email.
     */
    @Test
    public void testUpdateUserEmailInvalid() {
        System.out.println("testUpdateUserUsernameInvalid");
        //Arrange
        String name = "teste1";
        String username = "teste1";
        String email = "tew1";
        String password = "teste1password";
        //Act
        this.updateUserProfileController.getUser("ricardocorreia");
        String result = this.updateUserProfileController.setUserData(name, username, email, password);
        //Assert
        assertEquals(result, "Invalid username/email");
    }
    
    /**
     * Test user update with valid fields.
     */
    @Test
    public void testUpdateUserValid() {
        System.out.println("testUpdateUserUsernameInvalid");
        //Arrange
        String name = "teste1";
        String username = "teste1";
        String email = "teste1@b.com";
        String password = "teste1password";
        //Act
        this.updateUserProfileController.getUser("ricardocorreia");
        String result = this.updateUserProfileController.setUserData(name, username, email, password);
        //Assert
        assertEquals(result, "Success");
    }
    
    /**
     * Test user update with duplicated username.
     */
    @Test
    public void testUpdateUsernameDuplicated() {
        System.out.println("testUpdateUsernameDuplicated");
        //Arrange
        String name = "teste1";
        String username = "renatooliveira";
        String email = "teste1@b.com";
        String password = "teste1password";
        //Act
        this.updateUserProfileController.getUser("ricardocorreia");
        String result = this.updateUserProfileController.setUserData(name, username, email, password);
        //Assert
        assertEquals(result, "Username/email duplicated");
    }
    
    /**
     * Test user update with duplicated email.
     */
    //COMMENTED OUT BECAUSE OF ENCRYPTION RANDOMNESS WHILE DATA IS NOT NORMALIZED
//    @Test
//    public void testUpdateEmailDuplicated() {
//        System.out.println("testUpdateEmailDuplicated");
//        //Arrange
//        String name ="teste1";
//        String username = "teste1";
//        String email = "@@qwuooCxHtExxEExEI";
//        String password = "teste1password";
//        //Act
//        this.updateUserProfileController.getUser("ricardocorreia");
//        String result = this.updateUserProfileController.setUserData(name, username, email, password);
//        //Assert
//        assertEquals(result, "Username/email duplicated");
//    }
}
