/**
 * Package location for Model concept tests.
 */
package lapr.project.model.exhibition;

import lapr.project.model.Exhibition;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests an exhibition at the exhibitionClosedApplicationsState class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionCompleteStateTest {

    private Exhibition exhibition;

    public ExhibitionCompleteStateTest() {
    }

    @Before
    public void setUp() {
        exhibition = new Exhibition();
        exhibition.setState(new ExhibitionCompleteState(exhibition));
    }

    /**
     * Test of isInicial method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsInicial() {
        System.out.println("isInicial");
        boolean result = exhibition.getState().isInicial();
        assertFalse(result);
    }

    /**
     * Test of setCreated method, of class ExhibitionCompleteState.
     */
    @Test
    public void testSetCreated() {
        System.out.println("setCreated");
        boolean result = exhibition.getState().setCreated();
        assertFalse(result);
    }

    /**
     * Test of isCreated method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsCreated() {
        System.out.println("isCreated");
        boolean result = exhibition.getState().isCreated();
        assertFalse(result);
    }

    /**
     * Test of setStaffDefined method, of class ExhibitionCompleteState.
     */
    @Test
    public void testSetStaffDefined() {
        System.out.println("setStaffDefined");
        boolean result = exhibition.getState().setStaffDefined();
        assertFalse(result);
    }

    /**
     * Test of setDemonstrationsDefined method, of class
     * ExhibitionCompleteState.
     */
    @Test
    public void testSetDemonstrationsDefined() {
        System.out.println("setDemonstrationsDefined");
        boolean result = exhibition.getState().setDemonstrationsDefined();
        assertFalse(result);
    }

    /**
     * Test of isOpenApplications method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsOpenApplications() {
        System.out.println("isOpenApplications");
        boolean result = exhibition.getState().isOpenApplications();
        assertFalse(result);
    }

    /**
     * Test of setClosedApplications method, of class ExhibitionCompleteState.
     */
    @Test
    public void testSetClosedApplications() {
        System.out.println("setClosedApplications");
        boolean result = exhibition.getState().setClosedApplications();
        assertFalse(result);
    }

    /**
     * Test of isClosedApplications method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsClosedApplications() {
        System.out.println("isClosedApplications");
        boolean result = exhibition.getState().isClosedApplications();
        assertFalse(result);
    }

    /**
     * Test of setDetectedConflicts method, of class ExhibitionCompleteState.
     */
    @Test
    public void testSetDetectedConflicts() {
        System.out.println("setDetectedConflicts");
        boolean result = exhibition.getState().setDetectedConflicts();
        assertFalse(result);
    }

    /**
     * Test of isDetectedConficts method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsDetectedConficts() {
        System.out.println("isDetectedConficts");
        boolean result = exhibition.getState().isDetectedConficts();
        assertFalse(result);
    }

    /**
     * Test of setChangedConflicts method, of class ExhibitionCompleteState.
     */
    @Test
    public void testSetChangedConflicts() {
        System.out.println("setChangedConflicts");
        boolean result = exhibition.getState().setChangedConflicts();
        assertFalse(result);
    }

    /**
     * Test of isChangedConflitcts method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsChangedConflitcts() {
        System.out.println("isChangedConflitcts");
        boolean result = exhibition.getState().isChangedConflitcts();
        assertFalse(result);
    }

    /**
     * Test of setApplicationsInEvaluation method, of class
     * ExhibitionCompleteState.
     */
    @Test
    public void testSetApplicationsInEvaluation() {
        System.out.println("setApplicationsInEvaluation");
        boolean result = exhibition.getState().setApplicationsInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of isApplicationsInEvaluation method, of class
     * ExhibitionCompleteState.
     */
    @Test
    public void testIsApplicationsInEvaluation() {
        System.out.println("isApplicationsInEvaluation");
        boolean result = exhibition.getState().isApplicationsInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of setApplicationsInDecision method, of class
     * ExhibitionCompleteState.
     */
    @Test
    public void testSetApplicationsInDecision() {
        System.out.println("setApplicationsInDecision");
        boolean result = exhibition.getState().setApplicationsInDecision();
        assertFalse(result);
    }

    /**
     * Test of isApplicationsInDecision method, of class
     * ExhibitionCompleteState.
     */
    @Test
    public void testIsApplicationsInDecision() {
        System.out.println("isApplicationsInDecision");
        boolean result = exhibition.getState().isApplicationsInDecision();
        assertFalse(result);
    }

    /**
     * Test of setApplicationsDecided method, of class ExhibitionCompleteState.
     */
    @Test
    public void testSetApplicationsDecided() {
        System.out.println("setApplicationsDecided");
        boolean result = exhibition.getState().setApplicationsDecided();
        assertFalse(result);
    }

    /**
     * Test of isApplicationsDecided method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsApplicationsDecided() {
        System.out.println("isApplicationsDecided");
        boolean result = exhibition.getState().isApplicationsDecided();
        assertFalse(result);
    }

    /**
     * Test of isStaffDefined method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsStaffDefined() {
        System.out.println("isStaffDefined");
        boolean result = exhibition.getState().isStaffDefined();
        assertTrue(result);
    }

    /**
     * Test of isDemonstrationsDefined method, of class ExhibitionCompleteState.
     */
    @Test
    public void testIsDemonstrationsDefined() {
        System.out.println("isDemonstrationsDefined");
        boolean result = exhibition.getState().isDemonstrationsDefined();
        assertTrue(result);
    }

    /**
     * Test of setOpenApplication method, of class ExhibitionCompleteState.
     */
    @Test
    public void testSetOpenApplication() {
        System.out.println("setOpenApplication");
        boolean result = exhibition.getState().setOpenApplication();
        assertTrue(result);
    }

    /**
     * Test of validate method, of class ExhibitionCompleteState.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        boolean result = exhibition.validate();
        assertFalse(result);
    }

}
