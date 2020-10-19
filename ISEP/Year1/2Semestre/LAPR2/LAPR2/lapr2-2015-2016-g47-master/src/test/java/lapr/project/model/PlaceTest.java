
/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a place,
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class PlaceTest {

    /**
     * The place to be tested.
     */
    private Place place;

    @Before
    public void setUp() {
        place = new Place("Maia");
    }

    /**
     * Test of getLocation method, of class Place.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        String expResult = "Maia";
        String result = this.place.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocation method, of class Place.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "Porto";
        this.place.setLocation(location);
        assertEquals(this.place.getLocation(), location);
    }

    /**
     * Test of equals method, of class Place.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Place("Maia");;
        assertTrue(this.place.equals(otherObject));
    }

}

