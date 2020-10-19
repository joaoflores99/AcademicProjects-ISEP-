/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the resource class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ResourceTest {
    
    /**
     * The resource to be tested.
     */
    private Resource resource;
    
    @Before
    public void setUp() {
        this.resource = new Resource("Water");
    }
    /**
     * Test of validate method, of class Resource.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(this.resource.validate());
    }

    /**
     * Test of equals method, of class Resource.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Resource("Water");
        assertTrue(this.resource.equals(otherObject));
    }
    
}
