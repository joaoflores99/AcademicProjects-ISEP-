/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the staff member analytics class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class StaffMemberAnalyticTest {

    @Before
    public void setUp() {
    }

    /**
     * Test of updateWarning method, of class StaffMemberAnalytic. Test if its
     * over the critical region.
     */
    @Test
    public void testUpdateWarningOverCriticalRegion() {
        System.out.println("updateWarning");
        StaffMemberAnalytic.ConfidenceIntervals confidenceInterval = StaffMemberAnalytic.ConfidenceIntervals.NINETY_FIVE;
        StaffMemberAnalytic instance = new StaffMemberAnalytic();
        instance.setHypothesisTestValue(5.0f);

        instance.updateWarning(confidenceInterval);
        assertTrue(instance.isWarning());
    }

    /**
     * Test of updateWarning method, of class StaffMemberAnalytic. Test if its
     * under the critical region.
     */
    @Test
    public void testUpdateWarningUnderCriticalRegion() {
        System.out.println("updateWarning");
        StaffMemberAnalytic.ConfidenceIntervals confidenceInterval = StaffMemberAnalytic.ConfidenceIntervals.NINETY_FIVE;
        StaffMemberAnalytic instance = new StaffMemberAnalytic();
        instance.setHypothesisTestValue(0.8f);

        instance.updateWarning(confidenceInterval);
        assertFalse(instance.isWarning());
    }

    /**
     * Test of updateWarning method, of class StaffMemberAnalytic. Test if its
     * equal the critical region value.
     */
    @Test
    public void testUpdateWarningEqualCriticalRegionValue() {
        System.out.println("updateWarning");
        StaffMemberAnalytic.ConfidenceIntervals confidenceInterval = StaffMemberAnalytic.ConfidenceIntervals.NINETY_FIVE;
        StaffMemberAnalytic instance = new StaffMemberAnalytic();
        instance.setHypothesisTestValue(1.645f);

        instance.updateWarning(confidenceInterval);
        assertFalse(instance.isWarning());
    }

    /**
     * Test of equals method, of class StaffMemberAnalytic.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        StaffMemberAnalytic instance = new StaffMemberAnalytic();
        Object otherObject = (Object) instance;

        boolean result = instance.equals(otherObject);
        assertTrue(result);
    }
}
