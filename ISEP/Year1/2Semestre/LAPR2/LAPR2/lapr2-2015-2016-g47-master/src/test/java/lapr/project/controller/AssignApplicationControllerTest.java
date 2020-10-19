/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.StaffAttributionsList;
import lapr.project.model.Submittable;
import lapr.project.model.exhibition.ExhibitionChangedConflictsState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a assigned application controller class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class AssignApplicationControllerTest {

    AssignApplicationController controller;
    Organizer organizer;
    ExhibitionCenter exhibitionCenter;
    Exhibition eInNotCorrectState;
    Exhibition eInCorrectState;

    StaffAttributionsList staffAttributionList;

    @Before
    public void setUp() {

        exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        eInNotCorrectState = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        eInCorrectState = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        eInCorrectState.setState(new ExhibitionChangedConflictsState(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)));
        organizer = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0);
        controller = new AssignApplicationController(exhibitionCenter, organizer);
    }

    /**
     * Test of getSubmittablesInChangedConflictsByOrganizer method, of class
     * AssignApplicationController.
     */
    @Test
    public void getSubmittablesInChangedConflictsByOrganizer() {
        System.out.println("getSubmittablesInChangedConflictsByOrganizer");

        AssignApplicationController instance = controller;

        int expectedResult = 0;

        List<Submittable> resultList = instance.getSubmittablesInChangedConflictsByOrganizer(this.organizer);
        int result = 0;
        
        for (Submittable submitable: resultList) {
            Exhibition exhibition = (Exhibition)submitable;
            if (!exhibition.hasOrganizer(organizer) || !exhibition.getState().isChangedConflitcts()) {
                result++;
            }
        }
        
        assertEquals(expectedResult, result);
    }
}
