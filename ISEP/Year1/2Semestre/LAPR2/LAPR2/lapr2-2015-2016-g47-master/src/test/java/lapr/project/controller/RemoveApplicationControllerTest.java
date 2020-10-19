/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.Demonstration;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Removable;
import lapr.project.model.application.ApplicationInSubmissionState;
import lapr.project.model.exhibition.ExhibitionOpenApplicationsState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests an RemoveApplicationController controller class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class RemoveApplicationControllerTest {
    
    RemoveApplicationController controller;
    ExhibitorResponsible exhibitorResponsible;
    ExhibitionCenter exhibitionCenter;
    Exhibition exhibition;
    List<Application> applicationsList = new ArrayList();
    
    @Before
    public void setUp() {

        exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        applicationsList = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList();
        exhibitorResponsible = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0).getExhibitor().getExhibitorResponsible();
        controller = new RemoveApplicationController(exhibitionCenter, exhibitorResponsible);
        
    }

    /**
     * Test of getSubmittableListByStaffMember method, of class
     * UpdateDetectedConflictsController.
     */
    @Test
    public void remove() {
        List<Removable> removables;
        Exhibition exhibition = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        Application application = exhibition.getApplicationsList().getApplicationsList().get(0);
        exhibition.setState(new ExhibitionOpenApplicationsState(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)));
        application.setState(new ApplicationInSubmissionState(application));
        
        List<Demonstration> demonstrationsList = new ArrayList();
        demonstrationsList = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getDemonstrationsList().getDemonstrationsList();
        
        removables = this.controller.getRemovables(exhibitorResponsible);
        Removable toRemove = removables.get(0);
        ((ExhibitionApplication) toRemove).setDemonstrationsList(demonstrationsList);
     
        this.controller.remove(toRemove);
        List<Application> applications = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList();
        boolean result = false;
        for (Application app : applications) {
            if (app.equals((Application) toRemove)) {
                result = ((Removable) app).isRemoved();
            }
        }
        
        assertTrue(result);
        
    }
    
   
}
