/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests an evaluation.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class EvaluationTest {

    /**
     * The evaluation to be tested.
     */
    private Evaluation evaluation;

    @Before
    public void setUp() {
        List<Integer> answers = new ArrayList<>();
        answers.add(5);
        answers.add(3);
        answers.add(4);
        answers.add(1);
        answers.add(3);
        evaluation = new Evaluation(answers, new StaffMember());
    }

    /**
     * Test of validate method, of class Evaluation.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(evaluation.validate());
    }

    /**
     * Test of validate method with an invalida evaluation.
     */
    @Test
    public void testValidateWithInvalidValue() {
        System.out.println("validate");
        List<Integer> answers = new ArrayList<>();
        answers.add(5);
        answers.add(3);
        answers.add(4);
        answers.add(1);
        Evaluation invalidInstance = new Evaluation(answers, new StaffMember());
        assertFalse(invalidInstance.validate());
    }

}
