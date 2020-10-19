/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.demonstration.DemonstrationApplicationsDecidedState;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a demonstration class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DemonstrationTest {

    @Before
    public void setUp() {
    }

    /**
     * Test of isApplicationsDecided method, of class Demonstration.
     */
    @Test
    public void testIsApplicationsDecided() {
        System.out.println("isApplicationsDecided");
        Demonstration instance = new Demonstration();
        instance.setCurrentState(new DemonstrationApplicationsDecidedState(instance));
        boolean result = instance.isApplicationsDecided();
        assertTrue(result);
    }

    /**
     * Test of getTitle method, of class Demonstration.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Demonstration instance = new Demonstration();
        instance.setTitle("Test");
        String expResult = "Test";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Demonstration.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "Test";
        Demonstration instance = new Demonstration();
        instance.setTitle(title);
        assertEquals(title, instance.getTitle());
    }

    /**
     * Test of getDescription method, of class Demonstration.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Demonstration instance = new Demonstration();
        instance.setDescription("Test");
        String expResult = "Test";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Demonstration.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Test";
        Demonstration instance = new Demonstration();
        instance.setDescription(description);
        assertEquals(instance.getDescription(), description);
    }

    /**
     * Test of getStartDate method, of class Demonstration.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Demonstration instance = new Demonstration();
        instance.setStartDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartDate method, of class Demonstration.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Date startDate = new Date(2016, 1, 1);
        Demonstration instance = new Demonstration();
        instance.setStartDate(startDate);
        assertEquals(instance.getStartDate(), startDate);
    }

    /**
     * Test of getEndDate method, of class Demonstration.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Demonstration instance = new Demonstration();
        instance.setEndDate(new Date(2016, 1, 1));
        Date expResult = new Date(2016, 1, 1);
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class Demonstration.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Date endDate = new Date(2016, 1, 1);
        Demonstration instance = new Demonstration();
        instance.setEndDate(endDate);
        assertEquals(endDate, instance.getEndDate());
    }

    /**
     * Test of getPlace method, of class Demonstration.
     */
    @Test
    public void testGetPlace() {
        System.out.println("getPlace");
        Demonstration instance = new Demonstration();
        instance.setPlace(new Place());
        Place expResult = new Place();
        Place result = instance.getPlace();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlace method, of class Demonstration.
     */
    @Test
    public void testSetPlace() {
        System.out.println("setPlace");
        Place place = new Place();
        Demonstration instance = new Demonstration();
        instance.setPlace(place);
        assertEquals(place, instance.getPlace());
    }

    /**
     * Test of getOrganizersList method, of class Demonstration.
     */
    @Test
    public void testGetOrganizersList() {
        System.out.println("getOrganizersList");
        Demonstration instance = new Demonstration();
        instance.setOrganizersList(new OrganizersList());
        OrganizersList expResult = new OrganizersList();
        OrganizersList result = instance.getOrganizersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrganizersList method, of class Demonstration.
     */
    @Test
    public void testSetOrganizersList() {
        System.out.println("setOrganizersList");
        OrganizersList organizersList = new OrganizersList();
        Demonstration instance = new Demonstration();
        instance.setOrganizersList(organizersList);
        assertEquals(instance.getOrganizersList(), organizersList);
    }

    /**
     * Test of getResourcesList method, of class Demonstration.
     */
    @Test
    public void testGetResourcesList() {
        System.out.println("getResourcesList");
        Demonstration instance = new Demonstration();
        List<Resource> expResult = new ArrayList<>();
        List<Resource> result = instance.getResourcesList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setResourcesList method, of class Demonstration.
     */
    @Test
    public void testSetResourcesList() {
        System.out.println("setResourcesList");
        List<Resource> resourcesList = new ArrayList<>();
        Demonstration instance = new Demonstration();
        instance.setResourcesList(resourcesList);
        assertEquals(instance.getResourcesList(), resourcesList);
    }

    /**
     * Test of getStaffList method, of class Demonstration.
     */
    @Test
    public void testGetStaffList() {
        System.out.println("getStaffList");
        Demonstration instance = new Demonstration();
        StaffList expResult = new StaffList();
        StaffList result = instance.getStaffList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStaffList method, of class Demonstration.
     */
    @Test
    public void testSetStaffList() {
        System.out.println("setStaffList");
        StaffList staffList = new StaffList();
        Demonstration instance = new Demonstration();
        instance.setStaffList(staffList);
        assertEquals(instance.getStaffList(), staffList);
    }

    /**
     * Test of getConflictsList method, of class Demonstration.
     */
    @Test
    public void testGetConflictsList() {
        System.out.println("getConflictsList");
        Demonstration instance = new Demonstration();
        ConflictsList expResult = new ConflictsList();
        ConflictsList result = instance.getConflictsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConflictsList method, of class Demonstration.
     */
    @Test
    public void testSetConflictsList() {
        System.out.println("setConflictsList");
        ConflictsList conflictsList = new ConflictsList();
        Demonstration instance = new Demonstration();
        instance.setConflictsList(conflictsList);
        assertEquals(instance.getConflictsList(), conflictsList);
    }

    /**
     * Test of addResource method, of class Demonstration.
     */
    @Test
    public void testAddResource() {
        System.out.println("addResource");
        Resource resource = new Resource();
        Demonstration instance = new Demonstration();
        boolean result = instance.addResource(resource);
        assertTrue(result);
    }

    /**
     * Test of removeResource method, of class Demonstration.
     */
    @Test
    public void testRemoveResource() {
        System.out.println("removeResource");
        int index = 0;
        Demonstration instance = new Demonstration();
        List<Resource> lr = new ArrayList<>();
        lr.add(new Resource());
        instance.setResourcesList(lr);
        
        boolean result = instance.removeResource(index);
        assertTrue(result);
    }

    /**
     * Test of getStaffAttributionsList method, of class Demonstration.
     */
    @Test
    public void testGetStaffAttributionsList() {
        System.out.println("getStaffAttributionsList");
        Demonstration instance = new Demonstration();
        StaffAttributionsList expResult = new StaffAttributionsList();
        StaffAttributionsList result = instance.getStaffAttributionsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getApplicationsList method, of class Demonstration.
     */
    @Test
    public void testGetApplicationsList() {
        System.out.println("getApplicationsList");
        Demonstration instance = new Demonstration();
        ApplicationsList expResult = new ApplicationsList();
        ApplicationsList result = instance.getApplicationsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setApplicationsList method, of class Demonstration.
     */
    @Test
    public void testSetApplicationsList() {
        System.out.println("setApplicationsList");
        ApplicationsList applicationsList = new ApplicationsList();
        Demonstration instance = new Demonstration();
        instance.setApplicationsList(applicationsList);
        assertEquals(instance.getApplicationsList(), applicationsList);
    }

    /**
     * Test of setStaffAttributionsList method, of class Demonstration.
     */
    @Test
    public void testSetStaffAttributionsList() {
        System.out.println("setStaffAttributionsList");
        StaffAttributionsList staffAttributionsList = new StaffAttributionsList();
        Demonstration instance = new Demonstration();
        instance.setStaffAttributionsList(staffAttributionsList);
        assertEquals(instance.getStaffAttributionsList(), staffAttributionsList);
    }

    /**
     * Test of getName method, of class Demonstration.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Demonstration instance = new Demonstration();
        instance.setTitle("Title");
        String expResult = "Title";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

}
