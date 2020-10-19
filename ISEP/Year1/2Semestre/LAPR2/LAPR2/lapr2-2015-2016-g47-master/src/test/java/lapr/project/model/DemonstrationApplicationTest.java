/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a demonstration application.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DemonstrationApplicationTest {

    /**
     * The demonstration application to be tested.
     */
    private DemonstrationApplication demonstrationApplication;
    
    /**
     * The exhibition center
     */
    private ExhibitionCenter exhibitionCenter;

    @Before
    public void setUp() {
        this.demonstrationApplication = new DemonstrationApplication();
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
    }

    /**
     * Test of equals method, of class ExhibitionApplication.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new DemonstrationApplication();
        assertTrue(demonstrationApplication.equals(otherObject));
    }

    /**
     * Test of newEvaluation method, of class DemonstrationApplication.
     */
    @Test
    public void testNewEvaluation() {
        System.out.println("newEvaluation");
        Evaluation expResult = new Evaluation();
        Evaluation result = demonstrationApplication.newEvaluation();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateEvaluation method, of class DemonstrationApplication.
     */
    @Test
    public void testValidateEvaluation() {
        System.out.println("validateEvaluation");
        List<Integer> answers = new ArrayList<>();
        answers.add(5);
        answers.add(3);
        answers.add(4);
        answers.add(1);
        answers.add(3);
        Evaluation evaluation = new Evaluation(answers, new StaffAttribution().getStaffMember());
        assertTrue(demonstrationApplication.validateEvaluation(evaluation));
    }

    /**
     * Test of registerEvaluation method, of class DemonstrationApplication.
     */
    @Test
    public void testRegisterEvaluation() {
        System.out.println("registerEvaluation");
        Evaluation evaluation = new Evaluation();
        assertTrue(demonstrationApplication.registerEvaluation(evaluation));
    }
    
    /**
     * Test of cloneToEditable method, of class DemonstrationApplication.
     */
    @Test
    public void testClone() {
        System.out.println("cloneToEditable");
        DemonstrationApplication demonstrationApplication = (DemonstrationApplication)this.exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getDemonstrationsList()
                .getDemonstrationsList().get(0).getApplicationsList()
                .getApplicationsList().get(0);
        Editable application = demonstrationApplication.cloneToEditable();
        assertEquals(application, demonstrationApplication);
    }

}
