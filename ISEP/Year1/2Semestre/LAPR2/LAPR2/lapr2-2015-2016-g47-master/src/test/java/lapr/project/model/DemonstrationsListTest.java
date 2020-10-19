/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a demonstrations list.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DemonstrationsListTest {

    /**
     * Demonstrations List.
     */
    private DemonstrationsList demonstrationsList;

    @Before
    public void setUp() {

        this.demonstrationsList = new DemonstrationsList();
        this.demonstrationsList.setSubStartDate(new Date(2016, 01, 02));
        this.demonstrationsList.setSubEndDate(new Date(2016, 01, 15));
        this.demonstrationsList.setConflictLimitDate(new Date(2016, 01, 20));
        this.demonstrationsList.setEvaluationLimitDate(new Date(2016, 01, 30));
    }

    /**
     * Test of newDemonstration method, of class DemonstrationsList.
     */
    @Test
    public void testNewDemonstration_0args() {
        System.out.println("newDemonstration");
        Demonstration expResult = new Demonstration();
        Demonstration result = this.demonstrationsList.newDemonstration();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateDates method, of class DemonstrationsList, returns true.
     */
    @Test
    public void testValidateDatesTrue() {
        System.out.println("validateDates");

        Demonstration demo = new Demonstration("test");
        demo.setStartDate(new Date(2016, 02, 05));
        demo.setEndDate(new Date(2016, 02, 24));

        boolean result = this.demonstrationsList.validateDates(demo);
        assertTrue(result);
    }

    /**
     * Test of validateDates method, of class DemonstrationsList, returns false.
     */
    @Test
    public void testValidateDatesFalse() {
        System.out.println("validateDates");

        Demonstration demo = new Demonstration("test");
        demo.setStartDate(new Date(2016, 01, 05)); // Wrong date
        demo.setEndDate(new Date(2016, 01, 01)); // Wrong date

        boolean result = this.demonstrationsList.validateDates(demo);
        assertFalse(result);
    }
}
