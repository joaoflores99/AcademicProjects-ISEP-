/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a decision.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DecisionTest {

    /**
     * The decision to be tested.
     */
    private Decision decision;

    @Before
    public void setUp() {
        this.decision = new Decision("top", true);
    }

    /**
     * Test of getJustificativeText method, of class Decision.
     */
    @Test
    public void testGetJustificativeText() {
        System.out.println("getJustificativeText");
        String expResult = "top";
        String result = this.decision.getJustificativeText();
        assertEquals(expResult, result);
    }

    /**
     * Test of isDecisionTrue method, of class Decision.
     */
    @Test
    public void testIsDecisionTrue() {
        System.out.println("isDecisionTrue");
        assertTrue(this.decision.isDecisionTrue());
    }

    /**
     * Test of setJustificativeText method, of class Decision.
     */
    @Test
    public void testSetJustificativeText() {
        System.out.println("setJustificativeText");
        String justificativeText = "good";
        this.decision.setJustificativeText(justificativeText);
        assertEquals(this.decision.getJustificativeText(), justificativeText);
    }

    /**
     * Test of setDecision method, of class Decision.
     */
    @Test
    public void testSetDecision() {
        System.out.println("setDecision");
        boolean decision = false;
        this.decision.setDecision(decision);
        assertFalse(this.decision.isDecisionTrue());
    }

    /**
     * Test of validate method, of class Decision.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(this.decision.validate());
    }

    /**
     * Test of equals method, of class Decision.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Decision("top", true);
        assertTrue(this.decision.equals(otherObject));
    }

}
