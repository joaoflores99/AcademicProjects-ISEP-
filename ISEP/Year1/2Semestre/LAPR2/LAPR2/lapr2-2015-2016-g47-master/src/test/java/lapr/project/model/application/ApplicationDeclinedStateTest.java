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
 * Represents the confirmed stand state for application.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationDeclinedStateTest {
    
    public ApplicationDeclinedStateTest() {
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
     * Test of isInitial method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsInitial() {
        System.out.println("isInitial");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isInitial();
        assertFalse(result);
    }

    /**
     * Test of setInSubmission method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetInSubmission() {
        System.out.println("setInSubmission");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setInSubmission();
        assertFalse(result);
    }

    /**
     * Test of isInSubmission method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsInSubmission() {
        System.out.println("isInSubmission");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isInSubmission();
        assertFalse(result);
    }

    /**
     * Test of setRemoved method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetRemoved() {
        System.out.println("setRemoved");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setRemoved();
        assertFalse(result);
    }

    /**
     * Test of isRemoved method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsRemoved() {
        System.out.println("isRemoved");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isRemoved();
        assertFalse(result);
    }

    /**
     * Test of setInAttribution method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetInAttribution() {
        System.out.println("setInAttribution");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setInAttribution();
        assertFalse(result);
    }

    /**
     * Test of isInAttribtion method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsInAttribtion() {
        System.out.println("isInAttribtion");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isInAttribtion();
        assertFalse(result);
    }

    /**
     * Test of setInEvaluation method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetInEvaluation() {
        System.out.println("setInEvaluation");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of isInEvaluation method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsInEvaluation() {
        System.out.println("isInEvaluation");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of setNotEvaluated method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetNotEvaluated() {
        System.out.println("setNotEvaluated");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setNotEvaluated();
        assertFalse(result);
    }

    /**
     * Test of isNotEvaluated method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsNotEvaluated() {
        System.out.println("isNotEvaluated");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isNotEvaluated();
        assertFalse(result);
    }

    /**
     * Test of setInDecision method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetInDecision() {
        System.out.println("setInDecision");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setInDecision();
        assertFalse(result);
    }

    /**
     * Test of isInDecision method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsInDecision() {
        System.out.println("isInDecision");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isInDecision();
        assertFalse(result);

    }

    /**
     * Test of setAccepted method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetAccepted() {
        System.out.println("setAccepted");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setAccepted();
        assertFalse(result);
    }

    /**
     * Test of isAccepted method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsAccepted() {
        System.out.println("isAccepted");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isAccepted();
        assertFalse(result);
    }

    /**
     * Test of setDeclined method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetDeclined() {
        System.out.println("setDeclined");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setDeclined();
        assertFalse(result);
    }

    /**
     * Test of isDeclined method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsDeclined() {
        System.out.println("isDeclined");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isDeclined();
        assertTrue(result);
    }

    /**
     * Test of setAssignedStand method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetAssignedStand() {
        System.out.println("setAssignedStand");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setAssignedStand();
        assertFalse(result);
    }

    /**
     * Test of isAssignedStand method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsAssignedStand() {
        System.out.println("isAssignedStand");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isAssignedStand();
        assertFalse(result);
    }

    /**
     * Test of setConfirmedStand method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetConfirmedStand() {
        System.out.println("setConfirmedStand");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of isConfirmedStand method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsConfirmedStand() {
        System.out.println("isConfirmedStand");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of setNotConfirmedStand method, of class ApplicationDeclinedState.
     */
    @Test
    public void testSetNotConfirmedStand() {
        System.out.println("setNotConfirmedStand");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.setNotConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of isNotConfirmedStand method, of class ApplicationDeclinedState.
     */
    @Test
    public void testIsNotConfirmedStand() {
        System.out.println("isNotConfirmedStand");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.isNotConfirmedStand();
        assertFalse(result);
    }

    /**
     * Test of validate method, of class ApplicationDeclinedState.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        ApplicationDeclinedState instance = new ApplicationDeclinedState(new ExhibitionApplication());
        boolean result = instance.validate();
        assertFalse(result);
    }
    
}
