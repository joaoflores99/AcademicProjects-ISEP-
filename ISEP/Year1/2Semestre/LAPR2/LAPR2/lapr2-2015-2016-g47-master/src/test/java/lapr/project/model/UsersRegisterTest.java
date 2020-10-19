/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests an user register.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UsersRegisterTest {
    
    /**
     * Users register.
     */
    private UsersRegister usersRegister;
    
    @Before
    public void setUp() {
        this.usersRegister = DefaultInstantiator.createExhibitionCenter().getUsersRegister();
    }

    /**
     * Test of getUsersList method, of class UsersRegister.
     */
    @Test
    public void testGetUsersList_0args() {
        System.out.println("getUsersList");
        List<User> expResult = new ArrayList<>();
        expResult.add(new User("Ivo Ferro", "ivoferro", "1151159@isep.ipp.pt", "123+Qwe", new ArrayList<>(),""));
        expResult.add(new User("Daniel Gonçalves", "danielgoncalves", "1151452@isep.ipp.pt", "Qwe+123", new ArrayList<>(),""));
        expResult.add(new User("Eric Amaral", "ericamaral", "1141570@isep.ipp.pt", "234+Wer", new ArrayList<>(),""));
        expResult.add(new User("Renato Oliveira", "renatooliveira", "1140822@isep.ipp.pt", "Wer+234", new ArrayList<>(),""));
        expResult.add(new User("Ricardo Correia", "ricardocorreia", "1151231", "123-Asd", new ArrayList<>(),""));
        expResult.add(new User("Belmiro Azevedo", "belmiroazevedo", "belmirazevedo@sonae.com", "321+Ewq", new ArrayList<>(),""));
        expResult.add(new User("Fábio Silva", "fabiosilva", "fabiosilva@blip.pt", "Ewq+321", new ArrayList<>(),""));
        expResult.add(new User("Gonçalo Quadros", "gonacaloquadros", "goncaloquadros@critical.software.com", "432+Rew", new ArrayList<>(),""));
        List<User> result = this.usersRegister.getUsersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class UsersRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = DefaultInstantiator.createExhibitionCenter().getUsersRegister();
        assertTrue(this.usersRegister.equals(otherObject));
    }
    
}
