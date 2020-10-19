/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the staff list class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class StaffListTest {

    /**
     * The staff lits to be tested.
     */
    public StaffList staffList;

    @Before
    public void setUp() {
        this.staffList = new StaffList();
    }

    /**
     * Test of isStaffMember method, of class StaffList.
     */
    @Test
    public void testIsStaffMember() {
        System.out.println("isStaffMember");

        StaffMember staffMember = new StaffMember();
        List<StaffMember> staffMembersList = new ArrayList<>();
        staffMembersList.add(staffMember);
        this.staffList.setStaffList(staffMembersList);

        assertTrue(this.staffList.isStaffMember(staffMember));
    }

    /**
     * Test of equals method, of class StaffList.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new StaffList();
        assertTrue(this.staffList.equals(otherObject));
    }

}
