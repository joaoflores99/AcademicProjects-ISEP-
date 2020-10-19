/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a record class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class RecordTest {

    /**
     * The record to be tested.
     */
    Record record;

    /**
     * The exhibition center used for tests.
     */
    ExhibitionCenter exhibitionCenter;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.record = this.exhibitionCenter.getRecord();
    }

    /**
     * Test of calculateStaffAnalytics method, of class Record.
     */
    @Test
    public void testCalculateStaffAnalytics() {
        System.out.println("calculateStaffAnalytics");
        
        List<StaffMember> staff = new ArrayList<>(record.getStaffList());

        List<StaffMemberAnalytic> expResult = new ArrayList<>();
        expResult.add(new StaffMemberAnalytic(staff.get(0), 35, 2.7771f, 1.1440f, 1.9973f, false));
        expResult.add(new StaffMemberAnalytic(staff.get(1), 34, 2.1118f, 1.2095f, 1.7737f, false));
        expResult.add(new StaffMemberAnalytic(staff.get(2), 31, 2.5516f, 1.3048f, 1.7976f, false));
        expResult.add(new StaffMemberAnalytic(staff.get(3), 32, 2.4875f, 1.3188f, 1.4556f, false));
        expResult.add(new StaffMemberAnalytic(staff.get(4), 33, 2.3303f, 1.0986f, 1.7351f, false));

        List<StaffMemberAnalytic> result = record.calculateStaffAnalytics();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of equals method, of class Record.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object otherObject = DefaultInstantiator.createExhibitionCenter().getRecord();
//        assertTrue(this.record.equals(otherObject));
//    }
    /**
     * Test of equals method, of class Record.
     */
    @Test
    public void testEqualsFalse() {
        System.out.println("equals - with different object");
        Object otherObject = new Record();
        assertFalse(this.record.equals(otherObject));
    }

//    /**
//     * Test of addEvaluation method, of class Record.
//     */
//    @Test // How can i test this without getters and setters? should this have an unit test?
//    public void testAddEvaluation() {
//        System.out.println("addEvaluation");
//        float average = 3.3F;
//        StaffMember staffMember = new StaffMember();
//        Application application = new ExhibitionApplication();
//        this.record.addEvaluation(average, staffMember, application);
//    }
}
