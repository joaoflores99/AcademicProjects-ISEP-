/**
 * Package location for Apllication Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Demonstration;
import lapr.project.model.Exhibition;
import lapr.project.model.Organizer;
import lapr.project.model.Resource;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a createDemonstrationController.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateDemonstrationControllerTest {

    /**
     * This Create Demonstration Controller.
     */
    private CreateDemonstrationController controller;

    @Before
    public void setUp() {

        this.controller = new CreateDemonstrationController(DefaultInstantiator.createExhibitionCenter(), new Organizer());

        Exhibition selectedExhibition = this.controller.getExhibitionCenter()
                .getExhibitionsRegister().getExhibitionsList().get(0);

        this.controller.setSelectedExhibition(selectedExhibition);

        List<Resource> resourcesList = new ArrayList<>();
        resourcesList.add(new Resource("testFailed"));
        Demonstration demonstration = new Demonstration();
        demonstration.setResourcesList(resourcesList);
        this.controller.setDemonstration(demonstration);
    }

    /**
     * Test of newDemonstration method, of class CreateDemonstrationController.
     */
    @Test
    public void testNewDemonstration() {
        System.out.println("newDemonstration");

        Demonstration expResult = new Demonstration();

        this.controller.newDemonstration();
        Demonstration result = this.controller.getDemonstration();

        assertEquals(expResult, result);
    }

    /**
     * Test of addResource method, of class CreateDemonstrationController.
     */
    @Test
    public void addResource() {
        System.out.println("addResource");

        Resource resource = new Resource("testAdded");

        this.controller.setDemonstration(new Demonstration());
        this.controller.addResource(resource);

        List<Resource> resourcesList = new ArrayList<>();
        resourcesList.add(resource);
        List<Resource> expResult = resourcesList;

        List<Resource> result = this.controller.getDemonstration().getResourcesList();

        assertEquals(expResult, result);
    }

    /**
     * Test of addResource method, of class CreateDemonstrationController,
     * returns true & adds resource.
     */
    @Test
    public void addResourceTrue() {
        System.out.println("addResource");

        boolean addedResource = this.controller.addResource(new Resource("test"));

        assertTrue(addedResource);
    }

    /**
     * Test of addResource method, of class CreateDemonstrationController,
     * returns false & doesn't add the resource.
     */
    @Test
    public void addResourceFalse() {
        System.out.println("addResource");

        boolean dontAddResource = this.controller.addResource(new Resource("testFailed"));

        assertFalse(dontAddResource);
    }

    /**
     * Test of registerDemonstration method, of class CreateDemonstrationController.
     */
    @Test
    public void testRegisterDemonstration() {

        System.out.println("registerDemonstration");

        Demonstration demonstration = new Demonstration("test");

        this.controller.setDemonstration(demonstration);

        boolean result = this.controller.registerDemonstration();
        assertTrue(result);
    }
}
