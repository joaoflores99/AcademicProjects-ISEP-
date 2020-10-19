/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a conflict.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ConflictTest {

    /**
     * The conflict to be tested
     */
    private Conflict conflict;

    @Before
    public void setUp() {
        this.conflict = new Conflict();
    }

    /**
     * Test of getConflictType method, of class Conflict.
     */
    @Test
    public void testGetConflictType() {
        System.out.println("getConflictType");
        ConflictType expResult = new Conflict().getConflictType();
        ConflictType result = this.conflict.getConflictType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConflictType method, of class Conflict.
     */
    @Test
    public void testSetConflictType() {
        System.out.println("setConflictType");
        ConflictType conflictType = new ConflictType();
        this.conflict.setConflictType(conflictType);
        assertEquals(this.conflict.getConflictType(), conflictType);
    }

    /**
     * Test of getStaffMember method, of class Conflict.
     */
    @Test
    public void testGetStaffMember() {
        System.out.println("getStaffMember");
        StaffMember expResult = new StaffMember();
        StaffMember result = this.conflict.getStaffMember();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStaffMember method, of class Conflict.
     */
    @Test
    public void testSetStaffMember() {
        System.out.println("setStaffMember");
        StaffMember staffMember = new StaffMember();
        this.conflict.setStaffMember(staffMember);
        assertEquals(this.conflict.getStaffMember(), staffMember);
    }

    /**
     * Test of getApplication method, of class Conflict.
     */
    @Test
    public void testGetApplication() {
        System.out.println("getApplication");
        Application expResult = new ExhibitionApplication();
        Application result = this.conflict.getApplication();
        assertEquals(expResult, result);
    }

    /**
     * Test of setApplication method, of class Conflict.
     */
    @Test
    public void testSetApplication() {
        System.out.println("setApplication");
        Application application = new ExhibitionApplication();
        this.conflict.setApplication(application);
        assertEquals(this.conflict.getApplication(), application);
    }

    /**
     * Test of isStaffMember method, of class Conflict.
     */
    @Test
    public void testIsStaffMember() {
        System.out.println("isStaffMember");
        StaffMember staffMember = new StaffMember();
        assertTrue(this.conflict.isStaffMember(staffMember));
    }

    /**
     * Test of validate method, of class Conflict.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(this.conflict.validate());
    }

    /**
     * Test of equals method, of class Conflict.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Conflict();
        assertEquals(this.conflict, otherObject);
    }

}
