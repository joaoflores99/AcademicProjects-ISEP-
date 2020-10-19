/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a user class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UserTest {

    /**
     * Instance of user.
     */
    private User user;

    @Before
    public void setUp() {
        this.user = new User();
    }

    /**
     * Test if validate method, of class User, returns true.
     */
    @Test
    public void testValidateTrue() {
        System.out.println("validate (expects true)");

        this.user.setUsername("username");
        this.user.setEmail("me@email.com");

        boolean result = this.user.validate();
        assertTrue(result);
    }

    /**
     * Test if validate method, of class User, returns false.
     */
    @Test
    public void testValidateFalse() {
        System.out.println("validate (expects false)");

        this.user.setUsername("use");
        this.user.setEmail("me");

        boolean result = this.user.validate();
        assertFalse(result);
    }

}
