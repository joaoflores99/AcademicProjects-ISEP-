/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
import lapr.project.model.exhibition.ExhibitionInicialState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a exhibition class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionTest {

    /**
     * Instance of Exhibition.
     */
    private Exhibition exhibition;

    /**
     * Exhibition center used for tests
     */
    private ExhibitionCenter exhibitionCenter;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.exhibition = this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
    }

    /**
     * Test of validate method, of class Exhibition, returns false.
     */
    @Test
    public void testValidateFalse() {
        System.out.println("validate");
        Exhibition instance = new Exhibition();
        boolean result = instance.validate();
        assertFalse(result);
    }

    /**
     * Test of validate method, of class Exhibition, returns true.
     */
    @Test
    public void testValidateTrue() {
        System.out.println("validate");
        assertTrue(this.exhibition.validate());
    }

    /**
     * Test of isApplicationsDecided method, of class Exhibition.
     */
    @Test
    public void testIsApplicationsDecided() {
        System.out.println("isApplicationsDecided");
        this.exhibition.setState(new ExhibitionDecidedApplicationsState(this.exhibition));
        assertTrue(this.exhibition.isApplicationsDecided());
    }

    @Test
    public void testGetAcceptanceRate() {
        System.out.println("getAcceptanceRate");

        List<Application> applications = new ArrayList<>();

        ExhibitionApplication application1 = new ExhibitionApplication();
        application1.setDecision(new Decision("Out of context.", false));
        applications.add(application1);

        ExhibitionApplication application2 = new ExhibitionApplication();
        application2.setDecision(new Decision("Interested.", true));
        applications.add(application2);

        ExhibitionApplication application3 = new ExhibitionApplication();
        application3.setDecision(new Decision("Out of context.", false));
        applications.add(application3);

        ExhibitionApplication application4 = new ExhibitionApplication();
        application4.setDecision(new Decision("Interested.", true));
        applications.add(application4);

        this.exhibition.setApplicationsList(new ApplicationsList(applications));

        float expResult = 0.50f;
        float result = this.exhibition.getAcceptanceRate();

        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testGetApplicationsAnalysis() {
        System.out.println("getApplicationsAnalysis");
        List<ApplicationAnalysis> expResult = new ArrayList<>();
        expResult.add(new ApplicationAnalysis(this.exhibition.getApplicationsList().getApplicationsList().get(0)));
        List<ApplicationAnalysis> result = this.exhibition.getApplicationsAnalysis();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class Exhibition.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Exhibition instance = new Exhibition();
        instance.setTitle("Test");
        String expResult = "Test";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Exhibition.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "Test";
        Exhibition instance = new Exhibition();
        instance.setTitle(title);
        assertEquals(title, instance.getTitle());
    }

    /**
     * Test of getDescription method, of class Exhibition.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Exhibition instance = new Exhibition();
        instance.setDescription("Test");
        String expResult = "Test";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Exhibition.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Test";
        Exhibition instance = new Exhibition();
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of getStartDate method, of class Exhibition.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Exhibition instance = new Exhibition();
        instance.setStartDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartDate method, of class Exhibition.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Date startDate = new Date(2016, 1, 1);
        Exhibition instance = new Exhibition();
        instance.setStartDate(startDate);
        assertEquals(instance.getStartDate(), startDate);
    }

    /**
     * Test of getEndDate method, of class Exhibition.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Exhibition instance = new Exhibition();
        instance.setEndDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class Exhibition.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Date endDate = new Date(2016, 1, 1);
        Exhibition instance = new Exhibition();
        instance.setEndDate(endDate);
        assertEquals(endDate, instance.getEndDate());
    }

    /**
     * Test of getSubStartDate method, of class Exhibition.
     */
    @Test
    public void testGetSubStartDate() {
        System.out.println("getSubStartDate");
        Exhibition instance = new Exhibition();
        instance.setSubStartDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getSubStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSubStartDate method, of class Exhibition.
     */
    @Test
    public void testSetSubStartDate() {
        System.out.println("setSubStartDate");
        Date subStartDate = new Date(2016, 1, 1);
        Exhibition instance = new Exhibition();
        instance.setSubStartDate(subStartDate);
        assertEquals(instance.getSubStartDate(), subStartDate);
    }

    /**
     * Test of getSubEndDate method, of class Exhibition.
     */
    @Test
    public void testGetSubEndDate() {
        System.out.println("getSubEndDate");
        Exhibition instance = new Exhibition();
        instance.setSubEndDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getSubEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSubEndDate method, of class Exhibition.
     */
    @Test
    public void testSetSubEndDate() {
        System.out.println("setSubEndDate");
        Date subEndDate = new Date(2016, 1, 1);
        Exhibition instance = new Exhibition();
        instance.setSubEndDate(subEndDate);
        assertEquals(instance.getSubEndDate(), subEndDate);
    }

    /**
     * Test of getConflictLimitDate method, of class Exhibition.
     */
    @Test
    public void testGetConflictLimitDate() {
        System.out.println("getConflictLimitDate");
        Exhibition instance = new Exhibition();
        instance.setConflictLimitDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getConflictLimitDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConflictLimitDate method, of class Exhibition.
     */
    @Test
    public void testSetConflictLimitDate() {
        System.out.println("setConflictLimitDate");
        Date conflictLimitDate = new Date(2016, 1, 1);
        Exhibition instance = new Exhibition();
        instance.setConflictLimitDate(conflictLimitDate);
        assertEquals(instance.getConflictLimitDate(), conflictLimitDate);
    }

    /**
     * Test of getEvaluationLimitDate method, of class Exhibition.
     */
    @Test
    public void testGetEvaluationLimitDate() {
        System.out.println("getEvaluationLimitDate");
        Exhibition instance = new Exhibition();
        instance.setEvaluationLimitDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getEvaluationLimitDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEvaluationLimitDate method, of class Exhibition.
     */
    @Test
    public void testSetEvaluationLimitDate() {
        System.out.println("setEvaluationLimitDate");
        Date evaluationLimitDate = new Date(2016, 1, 1);
        Exhibition instance = new Exhibition();
        instance.setEvaluationLimitDate(evaluationLimitDate);
        assertEquals(instance.getEvaluationLimitDate(), evaluationLimitDate);
    }

    /**
     * Test of getPlace method, of class Exhibition.
     */
    @Test
    public void testGetPlace() {
        System.out.println("getPlace");
        Exhibition instance = new Exhibition();
        Place expResult = new Place();
        Place result = instance.getPlace();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlace method, of class Exhibition.
     */
    @Test
    public void testSetPlace() {
        System.out.println("setPlace");
        Place place = new Place();
        Exhibition instance = new Exhibition();
        instance.setPlace(place);
        assertEquals(place, instance.getPlace());
    }

    /**
     * Test of getStaffList method, of class Exhibition.
     */
    @Test
    public void testGetStaffList() {
        System.out.println("getStaffList");
        Exhibition instance = new Exhibition();
        StaffList expResult = new StaffList();
        StaffList result = instance.getStaffList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStaffList method, of class Exhibition.
     */
    @Test
    public void testSetStaffList() {
        System.out.println("setStaffList");
        StaffList staffList = new StaffList();
        Exhibition instance = new Exhibition();
        instance.setStaffList(staffList);
        assertEquals(instance.getStaffList(), staffList);
    }

    /**
     * Test of getOrganizersList method, of class Exhibition.
     */
    @Test
    public void testGetOrganizersList() {
        System.out.println("getOrganizersList");
        Exhibition instance = new Exhibition();
        instance.setOrganizersList(new OrganizersList());
        OrganizersList expResult = new OrganizersList();
        OrganizersList result = instance.getOrganizersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrganizersList method, of class Exhibition.
     */
    @Test
    public void testSetOrganizersList() {
        System.out.println("setOrganizersList");
        OrganizersList organizersList = new OrganizersList();
        Exhibition instance = new Exhibition();
        instance.setOrganizersList(organizersList);
        assertEquals(instance.getOrganizersList(), organizersList);
    }

    /**
     * Test of getApplicationsList method, of class Exhibition.
     */
    @Test
    public void testGetApplicationsList() {
        System.out.println("getApplicationsList");
        Exhibition instance = new Exhibition();
        instance.setApplicationsList(new ApplicationsList());
        ApplicationsList expResult = new ApplicationsList();
        ApplicationsList result = instance.getApplicationsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setApplicationsList method, of class Exhibition.
     */
    @Test
    public void testSetApplicationsList() {
        System.out.println("setApplicationsList");
        ApplicationsList applicationsList = new ApplicationsList();
        Exhibition instance = new Exhibition();
        instance.setApplicationsList(applicationsList);
        assertEquals(instance.getApplicationsList(), applicationsList);
    }

    /**
     * Test of getDemonstrationsList method, of class Exhibition.
     */
    @Test
    public void testGetDemonstrationsList() {
        System.out.println("getDemonstrationsList");
        Exhibition instance = new Exhibition();
        instance.setDemonstrationsList(new DemonstrationsList());
        DemonstrationsList expResult = new DemonstrationsList();
        DemonstrationsList result = instance.getDemonstrationsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDemonstrationsList method, of class Exhibition.
     */
    @Test
    public void testSetDemonstrationsList() {
        System.out.println("setDemonstrationsList");
        DemonstrationsList demonstrationsList = new DemonstrationsList();
        Exhibition instance = new Exhibition();
        instance.setDemonstrationsList(demonstrationsList);
        assertEquals(instance.getDemonstrationsList(), demonstrationsList);
    }

    /**
     * Test of getStaffAttributionsList method, of class Exhibition.
     */
    @Test
    public void testGetStaffAttributionsList() {
        System.out.println("getStaffAttributionsList");
        Exhibition instance = new Exhibition();
        instance.setStaffAttributionsList(new StaffAttributionsList());
        StaffAttributionsList expResult = new StaffAttributionsList();
        StaffAttributionsList result = instance.getStaffAttributionsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStaffAttributionsList method, of class Exhibition.
     */
    @Test
    public void testSetStaffAttributionsList() {
        System.out.println("setStaffAttributionsList");
        StaffAttributionsList staffAttributionsList = new StaffAttributionsList();
        Exhibition instance = new Exhibition();
        instance.setStaffAttributionsList(staffAttributionsList);
        assertEquals(instance.getStaffAttributionsList(), staffAttributionsList);
    }

    /**
     * Test of getConflictsList method, of class Exhibition.
     */
    @Test
    public void testGetConflictsList() {
        System.out.println("getConflictsList");
        Exhibition instance = new Exhibition();
        instance.setConflictsList(new ConflictsList());
        ConflictsList expResult = new ConflictsList();
        ConflictsList result = instance.getConflictsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConflictsList method, of class Exhibition.
     */
    @Test
    public void testSetConflictsList() {
        System.out.println("setConflictsList");
        ConflictsList conflictsList = new ConflictsList();
        Exhibition instance = new Exhibition();
        instance.setConflictsList(conflictsList);
        assertEquals(instance.getConflictsList(), conflictsList);
    }

    /**
     * Test of getConflictListByStaffMember method, of class Exhibition.
     */
    @Test
    public void testGetConflictListByStaffMember() {
        System.out.println("getConflictListByStaffMember");
        StaffMember staffMember = new StaffMember();
        Exhibition instance = new Exhibition();
        List<Conflict> expResult = new ArrayList<>();
        List<Conflict> result = instance.getConflictListByStaffMember(staffMember);
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class Exhibition.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        Exhibition instance = new Exhibition();
        ExhibitionState currentState = new ExhibitionInicialState(instance);
        instance.setState(currentState);
        assertEquals(instance.getState(), currentState);
    }

    /**
     * Test of hasOrganizer method, of class Exhibition.
     */
    @Test
    public void testHasOrganizer() {
        System.out.println("hasOrganizer");
        Organizer organizer = new Organizer();
        Exhibition instance = new Exhibition();
        List<Organizer> lo = new ArrayList<>();
        lo.add(organizer);
        instance.setOrganizersList(new OrganizersList(lo));

        boolean result = instance.hasOrganizer(organizer);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class Exhibition.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Exhibition();
        Exhibition instance = new Exhibition();
        boolean result = instance.equals(otherObject);
        assertTrue(result);
    }

    /**
     * Test of getName method, of class Exhibition.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Exhibition instance = new Exhibition();
        instance.setTitle("Test");
        String expResult = "Test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}