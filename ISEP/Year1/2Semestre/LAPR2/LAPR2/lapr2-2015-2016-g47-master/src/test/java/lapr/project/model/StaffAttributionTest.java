/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StaffAttribution class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class StaffAttributionTest {

    /**
     * The staff attribution to be tested.
     */
    private StaffAttribution staffAttribution;

    @Before
    public void setUp() {
        this.staffAttribution = new StaffAttribution();
    }

    /**
     * Test of isStaffMember method, of class StaffAttribution.
     */
    @Test
    public void testIsStaffMember() {
        System.out.println("isStaffMember");
        StaffMember staffMember = new StaffMember();
        assertTrue(this.staffAttribution.isStaffMember(staffMember));
    }

    /**
     * Test of equals method, of class StaffAttribution.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new StaffAttribution();
        assertTrue(this.staffAttribution.equals(otherObject));
    }

}
