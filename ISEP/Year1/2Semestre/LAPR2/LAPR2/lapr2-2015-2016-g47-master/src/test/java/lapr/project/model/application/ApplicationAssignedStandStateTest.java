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
 * Tests an exhibition at the ApplicationAssignStand class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationAssignedStandStateTest {
    
    public ApplicationAssignedStandStateTest() {
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
     * Test of isInitial method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsInitial() {
        System.out.println("isInitial");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isInitial();
        assertFalse(result);
    }

    /**
     * Test of setInSubmission method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetInSubmission() {
        System.out.println("setInSubmission");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setInSubmission();
        assertFalse(result);
    }

    /**
     * Test of isInSubmission method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsInSubmission() {
        System.out.println("isInSubmission");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isInSubmission();
        assertFalse(result);
    }

    /**
     * Test of setRemoved method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetRemoved() {
        System.out.println("setRemoved");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setRemoved();
        assertFalse(result);
    }

    /**
     * Test of isRemoved method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsRemoved() {
        System.out.println("isRemoved");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isRemoved();
        assertFalse(result);
    }

    /**
     * Test of setInAttribution method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetInAttribution() {
        System.out.println("setInAttribution");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setInAttribution();
        assertFalse(result);
    }

    /**
     * Test of isInAttribtion method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsInAttribtion() {
        System.out.println("isInAttribtion");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isInAttribtion();
        assertFalse(result);
    }

    /**
     * Test of setInEvaluation method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetInEvaluation() {
        System.out.println("setInEvaluation");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of isInEvaluation method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsInEvaluation() {
        System.out.println("isInEvaluation");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of setNotEvaluated method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetNotEvaluated() {
        System.out.println("setNotEvaluated");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setNotEvaluated();
        assertFalse(result);
    }

    /**
     * Test of isNotEvaluated method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsNotEvaluated() {
        System.out.println("isNotEvaluated");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isNotEvaluated();
        assertFalse(result);
    }

    /**
     * Test of setInDecision method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetInDecision() {
        System.out.println("setInDecision");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setInDecision();
        assertFalse(result);
    }

    /**
     * Test of isInDecision method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsInDecision() {
        System.out.println("isInDecision");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isInDecision();
        assertFalse(result);
    }

    /**
     * Test of setDeclined method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetDeclined() {
        System.out.println("setDeclined");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setDeclined();
        assertFalse(result);
    }

    /**
     * Test of isDeclined method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsDeclined() {
        System.out.println("isDeclined");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isDeclined();
        assertFalse(result);
    }

    /**
     * Test of setAccepted method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetAccepted() {
        System.out.println("setAccepted");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setAccepted();
        assertFalse(result);
    }

    /**
     * Test of isAccepted method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsAccepted() {
        System.out.println("isAccepted");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isAccepted();
        assertFalse(result);
    }

    /**
     * Test of setAssignedStand method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetAssignedStand() {
        System.out.println("setAssignedStand");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setAssignedStand();
        assertFalse(result);
    }

    /**
     * Test of isAssignedStand method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsAssignedStand() {
        System.out.println("isAssignedStand");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isAssignedStand();
        assertTrue(result);
    }

    /**
     * Test of setConfirmedStand method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetConfirmedStand() {
        System.out.println("setConfirmedStand");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setConfirmedStand();
        assertTrue(result);
    }

    /**
     * Test of isConfirmedStand method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsConfirmedStand() {
        System.out.println("isConfirmedStand");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of setNotConfirmedStand method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testSetNotConfirmedStand() {
        System.out.println("setNotConfirmedStand");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.setNotConfirmedStand();
        assertTrue(result);
    }

    /**
     * Test of isNotConfirmedStand method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testIsNotConfirmedStand() {
        System.out.println("isNotConfirmedStand");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.isNotConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of validate method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.validate();
        assertTrue(result);
    }

    /**
     * Test of validateNotConfirmedStand method, of class ApplicationAssignedStandState.
     */
    @Test
    public void testValidateNotConfirmedStand() {
        System.out.println("validateNotConfirmedStand");
        ApplicationAssignedStandState instance = new ApplicationAssignedStandState(new ExhibitionApplication());
        boolean result = instance.validateNotConfirmedStand();
        assertTrue(result);
    }
    
}
