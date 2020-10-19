/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.application.ApplicationInEvaluationState;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the staff attribution list class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class StaffAttributionsListTest {

    private StaffAttributionsList staffAttributionsList;
    
    @Before
    public void setUp() {
        
        StaffMember staffMember = new StaffMember(new User("Ivo Ferro", "ivoferro", "ivoferro@isep.pt", "123+qwe", new ArrayList<>(),""));
        ExhibitionApplication exhibitionApplication = new ExhibitionApplication();
        exhibitionApplication.setState(new ApplicationInEvaluationState(exhibitionApplication));
        
        List<StaffAttribution> staffAttributions = new ArrayList<>();
        StaffAttribution staffAttribution1 = new StaffAttribution(exhibitionApplication, staffMember);
        StaffAttribution staffAttribution2 = new StaffAttribution(exhibitionApplication, new StaffMember());
        StaffAttribution staffAttribution3 = new StaffAttribution(new ExhibitionApplication(), staffMember);
        staffAttributions.add(staffAttribution1);
        staffAttributions.add(staffAttribution2);
        staffAttributions.add(staffAttribution3);
        
        staffAttributionsList = new StaffAttributionsList(staffAttributions);
    }

    /**
     * Test of getStaffAtributionsApplicationsInEvaluationByStaff method, of
     * class StaffAttributionsList.
     */
    @Test
    public void testGetStaffAtributionsApplicationsInEvaluationByStaff() {
        System.out.println("getStaffAtributionsApplicationsInEvaluationByStaff");
        StaffMember staffMember = new StaffMember(new User("Ivo Ferro", "ivoferro", "ivoferro@isep.pt", "123+qwe", new ArrayList<>(),""));
        
        ExhibitionApplication exhibitionApplication = new ExhibitionApplication();
        exhibitionApplication.setState(new ApplicationInEvaluationState(exhibitionApplication));
        StaffAttribution staffAttribution1 = new StaffAttribution(exhibitionApplication, staffMember);
        
        List<StaffAttribution> expResult = new ArrayList<>();
        expResult.add(staffAttribution1);
        
        List<StaffAttribution> result = staffAttributionsList.getStaffAtributionsApplicationsInEvaluationByStaff(staffMember);
        
        assertEquals(expResult, result);
    }

}
