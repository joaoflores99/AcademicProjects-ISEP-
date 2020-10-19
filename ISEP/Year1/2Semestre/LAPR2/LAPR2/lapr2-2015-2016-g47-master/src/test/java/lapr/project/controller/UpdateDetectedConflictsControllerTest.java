/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Conflict;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.StaffMember;
import lapr.project.model.Submittable;
import lapr.project.model.exhibition.ExhibitionDetectedConflictsState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests an updateDetectedConflictsController controller class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UpdateDetectedConflictsControllerTest {

    UpdateDetectedConflictsController controller;
    StaffMember staffMember;
    ExhibitionCenter exhibitionCenter;
    Exhibition eInNotCorrectState;
    Exhibition eInCorrectState;
    List<Conflict> conflicts = new ArrayList();
    List<Conflict> expectedResult = new ArrayList();
    
    @Before
    public void setUp() {

        exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        eInNotCorrectState = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        eInCorrectState = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        eInCorrectState.setState(new ExhibitionDetectedConflictsState(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)));
        staffMember = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getStaffList().getStaffList().get(0);
        controller = new UpdateDetectedConflictsController(exhibitionCenter, staffMember);
        
        conflicts.addAll(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getConflictsList().getConflictsList());
        for (Conflict conflict : expectedResult) {
            if (conflict.isStaffMember(staffMember)) {
                expectedResult.add(conflict);
            }
        }
    }

    /**
     * Test of getSubmittableListByStaffMember method, of class
     * UpdateDetectedConflictsController.
     */
    @Test
    public void getSubmittableListByStaffMember() {
        System.out.println("getSubmittableListByStaffMember");

        UpdateDetectedConflictsController instance = controller;

        List<Submittable> expectedResult = new ArrayList();
        expectedResult.add(eInCorrectState);

        List<Submittable> result = instance.getSubmittableListByStaffMember();
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of getConflictsListByStaffMember method, of class 
     * UpdateDetectedConflictsController.
     */
    public void getConflictsListByStaffMember() {
        System.out.println("getSubmittableListByStaffMember");
        
        List<Conflict> result = new ArrayList();
        result.addAll(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getConflictsList().getConflictsListByStaffMember(staffMember));
        
        assertEquals(expectedResult, result);
        
    }
}
