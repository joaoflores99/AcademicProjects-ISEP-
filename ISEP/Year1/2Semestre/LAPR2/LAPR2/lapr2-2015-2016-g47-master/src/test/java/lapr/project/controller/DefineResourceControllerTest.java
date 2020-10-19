/**
 * Package location for Model concept tests.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Resource;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the define resource controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineResourceControllerTest {

    /**
     * The controller to be tested.
     */
    private DefineResourceController defineResourceController;
    
    /**
     * Exhibition center used for tests.
     */
    private ExhibitionCenter exhibitionCenter;
    
    /**
     * Resource for tests.
     */
    private Resource resource;
    
    /**
     * Existing resource for tests.
     */
    private Resource existingResource;
    
    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.defineResourceController = new DefineResourceController(this.exhibitionCenter);
        this.resource = new Resource("Grass");
        this.existingResource = this.exhibitionCenter.getResourcesRegister().getResourcesList().get(0);
    }

    /**
     * Test of getResources method, of class DefineResourceController.
     */
    @Test
    public void testGetResources() {
        System.out.println("getResources");
        List<Resource> expResult = this.exhibitionCenter.getResourcesRegister().getResourcesList();
        List<Resource> result = this.defineResourceController.getResources();
        assertEquals(expResult, result);
    }

    /**
     * Test of newResource method, of class DefineResourceController.
     */
    @Test
    public void testNewResource() {
        System.out.println("newResource");
        String description = "Grass";
        assertTrue(this.defineResourceController.newResource(description));
    }

    /**
     * Test of registerResource method, of class DefineResourceController.
     */
    @Test
    public void testRegisterResource() {
        System.out.println("registerResource");
        String description = "Grass";
        this.defineResourceController.newResource(description);
        assertTrue(this.defineResourceController.registerResource());
    }
    
    /**
     * Test of removeResource method, of class DefineResourceController.
     */
    @Test
    public void testRemoveResource() {
        System.out.println("removeResource");
        assertTrue(this.defineResourceController.removeResource(this.existingResource));
    }

}
