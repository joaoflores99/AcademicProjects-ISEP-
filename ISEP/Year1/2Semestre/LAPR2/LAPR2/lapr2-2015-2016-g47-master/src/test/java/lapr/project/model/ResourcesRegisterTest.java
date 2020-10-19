/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the resources register class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ResourcesRegisterTest {

    /**
     * The resuorces register.
     */
    private ResourcesRegister resourcesRegister;

    /**
     * A valida resource.
     */
    private Resource validResource;

    /**
     * An existing resource.
     */
    private Resource existingResource;

    @Before
    public void setUp() throws Exception {
        this.resourcesRegister = DefaultInstantiator.createExhibitionCenter().getResourcesRegister();
        this.validResource = new Resource("Wine");
        this.existingResource = DefaultInstantiator.createExhibitionCenter().getResourcesRegister().getResourcesList().get(0);
    }

    /**
     * Test of newResourse method, of class ResourcesRegister.
     */
    @Test
    public void testNewResource() {
        System.out.println("newResource");
        Resource expResult = new Resource();
        assertEquals(this.resourcesRegister.newResource(), expResult);
    }

    /**
     * Test of validateResource method, of class ResourcesRegister.
     */
    @Test
    public void testValidateResource() {
        System.out.println("validateResource");
        assertTrue(resourcesRegister.validateResource(this.validResource));
    }
    
    /**
     * Test of removeResource method, of class ResourcesRegister.
     */
    @Test
    public void testRemoveResource() {
        System.out.println("removeResource");
        assertTrue(this.resourcesRegister.removeResource(this.existingResource));
    }

    /**
     * Test of validateResource method, of class ResourcesRegister with an
     * existing resource.
     */
    @Test
    public void testValidateResourceWithExistingResourse() {
        System.out.println("validateResource with existing resource");
        assertFalse(resourcesRegister.validateResource(this.existingResource));
    }

    /**
     * Test of registerResource method, of class ResourcesRegister.
     */
    @Test
    public void testRegisterResource() {
        System.out.println("registerResource");
        assertTrue(this.resourcesRegister.registerResource(this.validResource));
    }

    /**
     * Test of registerResource method, of class ResourcesRegister with an
     * existing resource.
     */
    @Test
    public void testRegisterResourceWithExistingResourse() {
        System.out.println("registerResource with existing resource");
        assertFalse(this.resourcesRegister.registerResource(this.existingResource));
    }

    /**
     * Test of equals method, of class ResourcesRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = DefaultInstantiator.createExhibitionCenter().getResourcesRegister();
        assertTrue(this.resourcesRegister.equals(otherObject));
    }

}
