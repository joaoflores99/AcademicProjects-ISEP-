/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests an exhibitor.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitorTest {

    private Exhibitor exhibitor;

    @Before
    public void setUp() {
        this.exhibitor = new Exhibitor("ivo", "Maia", "915267777", new ExhibitorResponsible());
    }

    /**
     * Test of getName method, of class Exhibitor.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "ivo";
        String result = this.exhibitor.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Exhibitor.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "Maia";
        String result = this.exhibitor.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMobileNumber method, of class Exhibitor.
     */
    @Test
    public void testGetMobileNumber() {
        System.out.println("getMobileNumber");
        String expResult = "915267777";
        String result = this.exhibitor.getMobileNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Exhibitor.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "ferro";
        this.exhibitor.setName(name);
        assertEquals(this.exhibitor.getName(), name);
    }

    /**
     * Test of setAddress method, of class Exhibitor.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "porto";
        this.exhibitor.setAddress(address);
        assertEquals(this.exhibitor.getAddress(), address);
    }

    /**
     * Test of setMobileNumber method, of class Exhibitor.
     */
    @Test
    public void testSetMobileNumber() {
        System.out.println("setMobileNumber");
        String mobileNumber = "912345678";
        this.exhibitor.setMobileNumber(mobileNumber);
        assertEquals(this.exhibitor.getMobileNumber(), mobileNumber);
    }

    /**
     * Test of getExhibitorResponsible method, of class Exhibitor.
     */
    @Test
    public void testGetExhibitorResponsible() {
        System.out.println("getExhibitorResponsible");
        ExhibitorResponsible expResult = new ExhibitorResponsible();
        ExhibitorResponsible result = this.exhibitor.getExhibitorResponsible();
        assertEquals(expResult, result);
    }

    /**
     * Test of setExhibitorResponsible method, of class Exhibitor.
     */
    @Test
    public void testSetExhibitorResponsible() {
        System.out.println("setExhibitorResponsible");
        ExhibitorResponsible exhibitorResponsible = new ExhibitorResponsible();
        this.exhibitor.setExhibitorResponsible(exhibitorResponsible);
        assertEquals(this.exhibitor.getExhibitorResponsible(), exhibitorResponsible);
    }

    /**
     * Test of equals method, of class Exhibitor.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Exhibitor("ivo", "Maia", "915267777", new ExhibitorResponsible());
        assertTrue(this.exhibitor.equals(otherObject));
    }

    /**
     * Test of validate method, of class Exhibitor.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(this.exhibitor.validate());
    }

}
