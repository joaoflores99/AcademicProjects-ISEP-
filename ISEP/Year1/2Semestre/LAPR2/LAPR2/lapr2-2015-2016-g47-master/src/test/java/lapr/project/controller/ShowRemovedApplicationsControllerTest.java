/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.application.ApplicationRemovedState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a ShowRemovedApplicationsController class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ShowRemovedApplicationsControllerTest {
    
    ShowRemovedApplicationsController controller;
    Organizer organizer;
    ExhibitionCenter exhibitionCenter;
    Application aInNotCorrectState;
    Application aInCorrectState;
    List<Application> applications = new ArrayList();
    List<Application> expectedResult = new ArrayList();
    
    @Before
    public void setUp() {
        
        exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        aInNotCorrectState = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        aInCorrectState = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        aInCorrectState.setState(new ApplicationRemovedState(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)));
        organizer = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0);
        
        exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList()
                .getApplicationsList().get(0).setState(new ApplicationRemovedState(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList()
                                .getApplicationsList().get(0)));
        
        controller = new ShowRemovedApplicationsController(exhibitionCenter, organizer);
        
        expectedResult.add(aInCorrectState);
    }

    /**
     * Test of getSubmittableListByStaffMember method, of class
     * UpdateDetectedConflictsController.
     */
    @Test
    public void getSubmittableListByStaffMember() {
        System.out.println("getSubmittableListByStaffMember");
        
        ShowRemovedApplicationsController instance = controller;        
        
        List<Application> result = instance.getRemovedApplicationsListByOrganizer(organizer);
        
        assertEquals(expectedResult, result);
    }
    
}
