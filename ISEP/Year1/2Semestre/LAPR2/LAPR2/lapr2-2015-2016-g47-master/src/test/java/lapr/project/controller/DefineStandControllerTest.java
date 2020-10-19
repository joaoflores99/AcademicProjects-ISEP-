/**
 * Package location for Controller concept tests.
 */
package lapr.project.controller;

import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Stand;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the define stand controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineStandControllerTest {
    
    /**
     * The controller to be tested.
     */
    private DefineStandController defineStandController;
    
    /**
     * The exhibition center
     */
    private ExhibitionCenter exhibitionCenter;

    
    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.defineStandController = new DefineStandController(this.exhibitionCenter);
    }
    
    /**
     * Test stand creation with invalid area.
     */
    @Test
    public void testNewStandAreaInvalid() {
        System.out.print("testNewStandAreaInvalid");
        //Arrange
        float area = 0;
        String description = "test";
        //Act      
        boolean result = this.defineStandController.newStand(area, description);
        //Assert
        assertFalse(result);
    }
    
    /**
     * Test stand creation with invalid description.
     */
    @Test
    public void testNewStandDescriptionInvalid() {
        System.out.print("testNewStandDescriptionInvalid");
        //Arrange
        float area = 1;
        String description = "";
        //Act      
        boolean result = this.defineStandController.newStand(area, description);
        //Assert
        assertFalse(result);
    }
    
    /**
     * Test stand creation with invalid area and description.
     */
    @Test
    public void testNewStandAreaInvalidDescriptionInvalid() {
        System.out.print("testNewStandAreaInvalidDescriptionInvalid");
        //Arrange
        float area = 0;
        String description = "";
        //Act      
        boolean result = this.defineStandController.newStand(area, description);
        //Assert
        assertFalse(result);
    }
    
    /**
     * Test stand creation with valid area and description.
     */
    @Test
    public void testNewStandAreaValidDescriptionValid() {
        System.out.print("testNewStandAreaValidDescriptionValid");
        //Arrange
        float area = 1;
        String description = "test";
        //Act      
        boolean result = this.defineStandController.newStand(area, description);
        //Assert
        assertTrue(result);
    }
    
    /**
     * Test valid stand registration.
     */
    @Test
    public void testValidStandRegistration() {
        System.out.print("testValidStandRegistration");
        //Arrange
        Stand stand = this.exhibitionCenter.getStandsRegister().getStandsList().get(0);
        //Act
        this.defineStandController.newStand(stand.getArea(), stand.getDescription());
        boolean result = this.defineStandController.registerStand();
        //Assert
        assertTrue(result);
    }
    
     /**
     * Test invalid stand registration.
     */
    @Test
    public void testInvalidStandRegistration() {
        System.out.print("testInvalidStandRegistration");
        //Arrange
        Stand stand = this.exhibitionCenter.getStandsRegister().getStandsList().get(0);
        //Act
        this.defineStandController.newStand(stand.getArea(), stand.getDescription());
        this.defineStandController.registerStand();
        boolean result = this.defineStandController.registerStand();
        //Assert
        assertFalse(result);
    }
}
