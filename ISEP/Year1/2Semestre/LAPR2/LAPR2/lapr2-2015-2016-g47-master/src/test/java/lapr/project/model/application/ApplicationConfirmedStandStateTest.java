/**
 * Package location for Model concept tests.
 */
package lapr.project.model.application;

import lapr.project.model.ExhibitionApplication;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests an exhibition at the applicationsInDecisionState class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationConfirmedStandStateTest {
    
    public ApplicationConfirmedStandStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isInitial method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsInitial() {
        System.out.println("isInitial");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isInitial();
        assertFalse(result);
    }

    /**
     * Test of setInSubmission method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetInSubmission() {
        System.out.println("setInSubmission");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setInSubmission();
        assertFalse(result);
    }

    /**
     * Test of isInSubmission method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsInSubmission() {
        System.out.println("isInSubmission");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isInSubmission();
        assertFalse(result);
    }

    /**
     * Test of setRemoved method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetRemoved() {
        System.out.println("setRemoved");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setRemoved();
        assertFalse(result);
    }

    /**
     * Test of isRemoved method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsRemoved() {
        System.out.println("isRemoved");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isRemoved();
        assertFalse(result);
    }

    /**
     * Test of setInAttribution method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetInAttribution() {
        System.out.println("setInAttribution");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setInAttribution();
        assertFalse(result);
    }

    /**
     * Test of isInAttribtion method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsInAttribtion() {
        System.out.println("isInAttribtion");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isInAttribtion();
        assertFalse(result);
    }

    /**
     * Test of setInEvaluation method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetInEvaluation() {
        System.out.println("setInEvaluation");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of isInEvaluation method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsInEvaluation() {
        System.out.println("isInEvaluation");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of setNotEvaluated method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetNotEvaluated() {
        System.out.println("setNotEvaluated");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setNotEvaluated();
        assertFalse(result);
    }

    /**
     * Test of isNotEvaluated method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsNotEvaluated() {
        System.out.println("isNotEvaluated");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isNotEvaluated();
        assertFalse(result);
    }

    /**
     * Test of setInDecision method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetInDecision() {
        System.out.println("setInDecision");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setInDecision();
        assertFalse(result);
    }

    /**
     * Test of isInDecision method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsInDecision() {
        System.out.println("isInDecision");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isInDecision();
        assertFalse(result);
    }

    /**
     * Test of setDeclined method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetDeclined() {
        System.out.println("setDeclined");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setDeclined();
        assertFalse(result);
    }

    /**
     * Test of isDeclined method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsDeclined() {
        System.out.println("isDeclined");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isDeclined();
        assertFalse(result);
    }

    /**
     * Test of setAccepted method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetAccepted() {
        System.out.println("setAccepted");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setAccepted();
        assertFalse(result);
    }

    /**
     * Test of isAccepted method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsAccepted() {
        System.out.println("isAccepted");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isAccepted();
        assertFalse(result);
    }

    /**
     * Test of setAssignedStand method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetAssignedStand() {
        System.out.println("setAssignedStand");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setAssignedStand();
        assertFalse(result);
    }

    /**
     * Test of isAssignedStand method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsAssignedStand() {
        System.out.println("isAssignedStand");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isAssignedStand();
        assertFalse(result);
    }

    /**
     * Test of setConfirmedStand method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetConfirmedStand() {
        System.out.println("setConfirmedStand");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of isConfirmedStand method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsConfirmedStand() {
        System.out.println("isConfirmedStand");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isConfirmedStand();
        assertTrue(result);
    }

    /**
     * Test of setNotConfirmedStand method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testSetNotConfirmedStand() {
        System.out.println("setNotConfirmedStand");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.setNotConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of isNotConfirmedStand method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testIsNotConfirmedStand() {
        System.out.println("isNotConfirmedStand");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.isNotConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of validate method, of class ApplicationConfirmedStandState.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        ApplicationConfirmedStandState instance = new ApplicationConfirmedStandState(new ExhibitionApplication());
        boolean result = instance.validate();
        assertFalse(result);
    }
}
