/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for stand.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class StandTest {

    /**
     * The stand to be tested
     */
    private Stand stand;

    @Before
    public void setUp() {
        this.stand = new Stand(23.2f, "Cars");
    }

    /**
     * Test of setNumberID method, of class Stand.
     */
    @Test
    public void testSetNumberID() {
        System.out.println("setNumberID");
        int numberID = 22;
        this.stand.setNumberID(numberID);
        assertEquals(numberID, this.stand.getNumberID());
    }

    /**
     * Test of getArea method, of class Stand.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        float expResult = 23.2F;
        float result = this.stand.getArea();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of setArea method, of class Stand.
     */
    @Test
    public void testSetArea() {
        System.out.println("setArea");
        float area = 11.11F;
        this.stand.setArea(area);
        assertEquals(area, this.stand.getArea(), 0.01);
    }

    /**
     * Test of getDescription method, of class Stand.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "Cars";
        String result = this.stand.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Stand.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Stand instance = new Stand();
        instance.setDescription(description);
    }

    /**
     * Test of validate method, of class Stand.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(this.stand.validate());
    }

    /**
     * Test of equals method, of class Stand.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = this.stand = new Stand(23.2f, "Cars");
        this.stand.equals(otherObject);
    }

    /**
     * Test of getDisplayInfo method, of class Stand.
     */
    @Test
    public void testGetDisplayInfo() {
        System.out.println("getDisplayInfo");
        String expResult = "Cars";
        String result = this.stand.getDisplayInfo();
        assertEquals(expResult, result);
    }

}
