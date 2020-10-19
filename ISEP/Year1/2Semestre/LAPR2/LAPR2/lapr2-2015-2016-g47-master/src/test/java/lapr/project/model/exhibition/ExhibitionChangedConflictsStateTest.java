/**
 * Package location for Model concept tests.
 */
package lapr.project.model.exhibition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.ApplicationsList;
import lapr.project.model.ConflictsList;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.Organizer;
import lapr.project.model.OrganizersList;
import lapr.project.model.Place;
import lapr.project.model.StaffAttributionsList;
import lapr.project.model.StaffList;
import lapr.project.model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests an exhibition at the exhibitionChangedConflictsState class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionChangedConflictsStateTest {

    private Exhibition exhibition;

    public ExhibitionChangedConflictsStateTest() {
    }

    @Before
    public void setUp() {
        List<Organizer> organizerslist = new ArrayList<>();
        organizerslist.add(new Organizer(new User("Daniel", "daniell", "email@dd2", "password", new ArrayList<>(), "")));
        organizerslist.add(new Organizer(new User("Daniel2", "daniell2", "email@dd2", "password", new ArrayList<>(), "")));

        this.exhibition = new Exhibition("title", "description", new Date(2016, 2, 1),
                new Date(2016, 3, 1), new Date(2016, 0, 10), new Date(2016, 0, 20),
                new Date(2016, 1, 10), new Date(2016, 1, 20),
                new Place(), new StaffList(), new OrganizersList(organizerslist),
                new ApplicationsList(), new DemonstrationsList(),
                new StaffAttributionsList(), new ConflictsList());
    }

    /**
     * Test of isInicial method, of class ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsInicial() {
        System.out.println("isInicial");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        boolean result = instance.isInicial();
        assertFalse(result);
    }

    /**
     * Test of setCreated method, of class ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetCreated() {
        System.out.println("setCreated");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
  
        boolean result = instance.setCreated();
        assertFalse(result);
    }

    /**
     * Test of isCreated method, of class ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsCreated() {
        System.out.println("isCreated");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
      
        boolean result = instance.isCreated();
       assertFalse(result);
       
    }

    /**
     * Test of setStaffDefined method, of class ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetStaffDefined() {
        System.out.println("setStaffDefined");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        
        boolean result = instance.setStaffDefined();
        assertFalse(result);
    }

    /**
     * Test of isStaffDefined method, of class ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsStaffDefined() {
        System.out.println("isStaffDefined");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.isStaffDefined();
        assertFalse(result);
    }

    /**
     * Test of setDemonstrationsDefined method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetDemonstrationsDefined() {
        System.out.println("setDemonstrationsDefined");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.setDemonstrationsDefined();
        assertFalse(result);
    }

    /**
     * Test of isDemonstrationsDefined method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsDemonstrationsDefined() {
        System.out.println("isDemonstrationsDefined");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        
        boolean result = instance.isDemonstrationsDefined();
        assertFalse(result);
    }

    /**
     * Test of setOpenApplication method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetOpenApplication() {
        System.out.println("setOpenApplication");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        
        boolean result = instance.setOpenApplication();
        assertFalse(result);
    }

    /**
     * Test of isOpenApplications method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsOpenApplications() {
        System.out.println("isOpenApplications");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.isOpenApplications();
        assertFalse(result);
    }

    /**
     * Test of setClosedApplications method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetClosedApplications() {
        System.out.println("setClosedApplications");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.setClosedApplications();
        assertFalse(result);
    }

    /**
     * Test of isClosedApplications method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsClosedApplications() {
        System.out.println("isClosedApplications");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.isClosedApplications();
        assertFalse(result);
    }

    /**
     * Test of setDetectedConflicts method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetDetectedConflicts() {
        System.out.println("setDetectedConflicts");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.setDetectedConflicts();
        assertFalse(result);
    }

    /**
     * Test of isDetectedConficts method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsDetectedConficts() {
        System.out.println("isDetectedConficts");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.isDetectedConficts();
        assertFalse(result);
    }

    /**
     * Test of setChangedConflicts method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetChangedConflicts() {
        System.out.println("setChangedConflicts");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.setChangedConflicts();
        assertFalse(result);
    }

    

    /**
     * Test of isApplicationsInEvaluation method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsApplicationsInEvaluation() {
        System.out.println("isApplicationsInEvaluation");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.isApplicationsInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of setApplicationsInDecision method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetApplicationsInDecision() {
        System.out.println("setApplicationsInDecision");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        
        boolean result = instance.setApplicationsInDecision();
        assertFalse(result);
    }

    /**
     * Test of isApplicationsInDecision method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsApplicationsInDecision() {
        System.out.println("isApplicationsInDecision");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        
        boolean result = instance.isApplicationsInDecision();
        assertFalse(result);
    }

    /**
     * Test of setApplicationsDecided method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetApplicationsDecided() {
        System.out.println("setApplicationsDecided");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
       
        boolean result = instance.setApplicationsDecided();
        assertFalse(result);
    }

    /**
     * Test of isApplicationsDecided method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsApplicationsDecided() {
        System.out.println("isApplicationsDecided");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        
        boolean result = instance.isApplicationsDecided();
        assertFalse(result);
    }
    
    /**
     * Test of isChangedConflitcts method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testIsChangedConflitcts() {
        System.out.println("isChangedConflitcts");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        boolean result = instance.isChangedConflitcts();
        assertTrue(result);
    }

    /**
     * Test of setApplicationsInEvaluation method, of class
     * ExhibitionChangedConflictsState.
     */
    @Test
    public void testSetApplicationsInEvaluation() {
        System.out.println("setApplicationsInEvaluation");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        boolean result = instance.setApplicationsInEvaluation();
        assertFalse(result);
    }

    /**
     * Test of validate method, of class ExhibitionChangedConflictsState.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        ExhibitionChangedConflictsState instance = new ExhibitionChangedConflictsState();
        boolean result = instance.validate();
        assertFalse(result);
    }

}
