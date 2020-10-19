/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a OrganizersList class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class OrganizersListTest {

    /**
     * Instance of OrganizersList.
     */
    private OrganizersList organizersList;

    /**
     * Instance of Organizer.
     */
    private Organizer organizer;

    /**
     * Instance of User.
     */
    private User user;

    @Before
    public void setUp() {

        this.organizersList = new OrganizersList();
        this.user = new User("John Doe", "johndoe", "jdoe@email.com", "password", new ArrayList<>(),"");
        this.organizer = new Organizer(user);
    }

    /**
     * Test of newOrganizer method, of class OrganizersList.
     */
    @Test
    public void testNewOrganizer() {
        System.out.println("newOrganizer");
        Organizer expResult = new Organizer(this.user);
        Organizer result = this.organizersList.newOrganizer(this.user);
        assertEquals(expResult, result);
    }

    /**
     * Test if addAndValidateOrganizer method, of class OrganizersList, adds the
     * organizer to the list.
     */
    @Test
    public void testAddAndValidateOrganizer() {
        System.out.println("addAndValidateOrganizer");
        List<Organizer> testList = new ArrayList<>();
        testList.add(this.organizer);

        OrganizersList expResult = new OrganizersList(testList);
        this.organizersList.addAndValidateOrganizer(organizer);
        assertEquals(expResult, this.organizersList);
    }

    /**
     * Test if addAndValidateOrganizer method, of class OrganizersList, returns
     * true when added to list.
     */
    @Test
    public void testAddAndValidateOrganizerReturnsTrue() {
        System.out.println("addAndValidateOrganizer");

        boolean result = this.organizersList.addAndValidateOrganizer(organizer);
        assertTrue(result);
    }

    /**
     * Test if addAndValidateOrganizer method, of class OrganizersList, returns
     * false when added to list.
     */
    @Test
    public void testAddAndValidateOrganizerReturnsFalse() {
        System.out.println("addAndValidateOrganizer");

        Organizer invalidOrganizer = new Organizer(new User("John Doe", "jd", "jd@", "password", new ArrayList<>(),""));

        boolean result = this.organizersList.addAndValidateOrganizer(invalidOrganizer);
        assertFalse(result);
    }
}
