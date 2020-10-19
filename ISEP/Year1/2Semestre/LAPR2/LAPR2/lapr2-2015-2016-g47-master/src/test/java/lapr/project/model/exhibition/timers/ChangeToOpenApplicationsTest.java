/**
 * Package location for Model concept tests.
 */
package lapr.project.model.exhibition.timers;

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
import lapr.project.model.exhibition.ExhibitionCompleteState;
import lapr.project.model.timers.ChangeToOpenApplications;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests change to open applications timer task.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ChangeToOpenApplicationsTest {

    /**
     * exhibition to test.
     */
    private Exhibition exhibition;

    @Before
    public void setUp() {

        List<Organizer> organizerslist = new ArrayList<>();
        organizerslist.add(new Organizer());
        organizerslist.add(new Organizer());

        this.exhibition = new Exhibition("title", "description", new Date(2016, 0, 1),
                new Date(2016, 3, 1), new Date(2016, 0, 10), new Date(2016, 1, 1),
                new Date(2016, 1, 10), new Date(2016, 2, 1),
                new Place(), new StaffList(), new OrganizersList(organizerslist),
                new ApplicationsList(), new DemonstrationsList(),
                new StaffAttributionsList(), new ConflictsList());
    }

    /**
     * Test of run method, of class ChangeToOpenApplications.
     */
    @Test
    public void testRun() {
        System.out.println("run");

        this.exhibition.setState(new ExhibitionCompleteState(exhibition));

        ChangeToOpenApplications instance = new ChangeToOpenApplications(exhibition);
        instance.run();

        boolean result = exhibition.getState().isOpenApplications();
        assertTrue(result);
    }

}
