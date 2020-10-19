/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import lapr.project.model.application.ApplicationAcceptedState;
import lapr.project.model.application.ApplicationAssignedStandState;
import lapr.project.model.application.ApplicationDeclinedState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests an application list.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationsListTest {

    /**
     * Exhibition center used int tests.
     */
    private ExhibitionCenter exhibitionCenter;

    /**
     * The application list.
     */
    private ApplicationsList applicationsList;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.applicationsList = this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList();
    }

    /**
     * Test of newExhibitionApplication method, of class ApplicationsList.
     */
    @Test
    public void testNewExhibitionApplication() {
        System.out.println("newExhibitionApplication");
        Application expResult = new ExhibitionApplication();
        Application result = this.applicationsList.newExhibitionApplication();
        assertEquals(expResult, result);
    }

    /**
     * Test of newDemonstrationApplication method, of class ApplicationsList.
     */
    @Test
    public void testNewDemonstrationApplication() {
        System.out.println("newDemonstrationApplication");
        Application expResult = new DemonstrationApplication();
        Application result = this.applicationsList.newDemonstrationApplication();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateDemonstrationApplication method, of class
     * ApplicationsList.
     */
    @Test
    public void testValidateDemonstrationApplication() {
        System.out.println("validateDemonstrationApplication");
        Application application = new DemonstrationApplication();
        assertTrue(this.applicationsList.validateDemonstrationApplication(application));
    }

//    /**
//     * Test of registerDemonstrationApplication method, of class
//     * ApplicationsList.
//     */
//    @Test
//    public void testRegisterDemonstrationApplication() {
//        System.out.println("registerDemonstrationApplication");
//        Application application = new ExhibitionApplication();
//        ApplicationsList instance = new ApplicationsList();
//        instance.registerDemonstrationApplication(application);
//        assertTrue(instance.getApplicationsList().contains(application));
//    }
    /**
     * Test of getApplicationByExhibitorResponsible method, of class
     * ApplicationsList.
     */
    @Test
    public void testGetApplicationByExhibitorResponsible() {
        System.out.println("getApplicationByExhibitorResponsible");
        ExhibitorResponsible exhibitorResponsible = new ExhibitorResponsible(new User("Fábio Silva", "fabiosilva", "fabiosilva@blip.pt", "Ewq+321", new ArrayList<>(),""));
        Application expResult = this.applicationsList.getApplicationsList().get(0);
        Application result = this.applicationsList.getApplicationByExhibitorResponsible(exhibitorResponsible);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEditableByExhibitorResponsible method, of class
     * ApplicationsList.
     */
    @Test
    public void testGetEditableByExhibitorResponsible() {
        System.out.println("getEditableByExhibitorResponsible");
        ExhibitorResponsible exhibitorResponsible = new ExhibitorResponsible(new User("Fábio Silva", "fabiosilva", "fabiosilva@blip.pt", "Ewq+321", new ArrayList<>(),""));
        Editable expResult = (Editable) this.applicationsList.getApplicationsList().get(0);
        Editable result = this.applicationsList.getEditableByExhibitorResponsible(exhibitorResponsible);
        assertEquals(expResult, result);

    }

    /**
     * Test of cloneEditable method, of class ApplicationsList.
     */
    @Test
    public void testCloneEditable() {
        System.out.println("cloneEditable");
        Editable editable = (Editable) this.applicationsList.getApplicationsList().get(0);
        Editable expResult = (Editable) this.applicationsList.getApplicationsList().get(0);
        Editable result = this.applicationsList.cloneEditable(editable);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testValidateNewEditable() {
//        System.out.println("validateNewEditable");
//        Editable newEditable = (Editable)this.applicationsList.getApplicationsList().get(0);
//        Editable oldEditable = new ExhibitionApplication();
//        assertTrue(this.applicationsList.validateEditable(newEditable, oldEditable));
//    }
    /**
     * Test of modifyEditable method, of class ApplicationsList.
     */
    @Test
    public void testModifyEditable() {
        System.out.println("modifyEditable");
        Editable newEditable = (Editable) new ExhibitionApplication();
        Editable oldEditable = (Editable) this.applicationsList.getApplicationsList().get(0);
        assertTrue(this.applicationsList.modifyEditable(newEditable, oldEditable));
    }

    /**
     * Test of modifyEditable method, of class ApplicationsList.
     */
    @Test
    public void testIsExhibitorResponsibleApplicaitonOnAssignedStand() {
        System.out.println("isExhibitorResponsibleApplicaitonOnAssignedStand");
        ExhibitorResponsible exhibitorResponsible = ((ExhibitionApplication) this.exhibitionCenter.getExhibitionsRegister().
                getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)).getExhibitor().getExhibitorResponsible();
        ((ExhibitionApplication) this.exhibitionCenter.getExhibitionsRegister().
                getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0))
                .setState(new ApplicationAssignedStandState(this.exhibitionCenter.getExhibitionsRegister().
                                getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)));
        assertTrue(this.applicationsList.isExhibitorResponsibleApplicaitonOnAssignedStand(exhibitorResponsible));
    }

    /**
     * Test of modifyEditable method, of class ApplicationsList.
     */
    @Test
    public void testGetExhibitorResponsibleApplication() {
        System.out.println("getExhibitorResponsibleApplication");
        ExhibitorResponsible exhibitorResponsible = ((ExhibitionApplication) this.exhibitionCenter.getExhibitionsRegister().
                getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)).getExhibitor().getExhibitorResponsible();
        ((ExhibitionApplication) this.exhibitionCenter.getExhibitionsRegister().
                getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0))
                .setState(new ApplicationAssignedStandState(this.exhibitionCenter.getExhibitionsRegister().
                                getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)));
        Application expResult = this.exhibitionCenter.getExhibitionsRegister().
                getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        assertEquals(expResult, this.applicationsList.getExhibitorResponsibleApplication(exhibitorResponsible));
    }

    /**
     * Test of CalculateAcceptedAppsKeywordsRankings method, of class ApplicationsList.
     */
    @Test
    public void testCalculateAcceptedAppsKeywordsRankings() {

        System.out.println("CalculateAcceptedAppsKeywordsRankings");

        Keyword keyword1 = new Keyword("Keyword1");
        Keyword keyword2 = new Keyword("Keyword2");
        Keyword keyword3 = new Keyword("Keyword3");

        List<Keyword> keywordsList1 = new ArrayList<>();
        keywordsList1.add(keyword1);
        keywordsList1.add(keyword2);
        ExhibitionApplication application1 = new ExhibitionApplication();
        application1.setState(new ApplicationAcceptedState(application1));
        application1.setDecision(true, "test");
        application1.setKeywordsList(keywordsList1);

        List<Keyword> keywordsList2 = new ArrayList<>();
        keywordsList2.add(keyword1);
        keywordsList2.add(keyword2);
        keywordsList2.add(keyword3);
        ExhibitionApplication application2 = new ExhibitionApplication();
        application2.setState(new ApplicationAssignedStandState(application2));
        application2.setDecision(true, "test");
        application2.setKeywordsList(keywordsList2);

        ExhibitionApplication application3 = new ExhibitionApplication();
        application3.setState(new ApplicationDeclinedState(application3));
        application3.setKeywordsList(keywordsList2);

        List<Application> applications = new ArrayList<>();
        applications.add(application1);
        applications.add(application2);
        applications.add(application3);
        ApplicationsList instance = new ApplicationsList(applications);

        Pair<Keyword, Integer> pair1 = new Pair(keyword1, 2);
        Pair<Keyword, Integer> pair2 = new Pair(keyword2, 2);
        Pair<Keyword, Integer> pair3 = new Pair(keyword3, 1);
        List<Pair<Keyword, Integer>> expResult = new ArrayList<>();
        expResult.add(pair1);
        expResult.add(pair2);
        expResult.add(pair3);

        List<Pair<Keyword, Integer>> result = instance.calculateAcceptedAppsKeywordsRankings();

        assertEquals(expResult, result);
    }

    /**
     * Test of CalculateDeclinedAppsKeywordsRankings method, of class ApplicationsList.
     */
    @Test
    public void testCalculateDeclinedAppsKeywordsRankings() {

        System.out.println("CalculateDeclinedAppsKeywordsRankings");

        Keyword keyword1 = new Keyword("Keyword1");
        Keyword keyword2 = new Keyword("Keyword2");
        Keyword keyword3 = new Keyword("Keyword3");

        List<Keyword> keywordsList1 = new ArrayList<>();
        keywordsList1.add(keyword1);
        keywordsList1.add(keyword2);
        ExhibitionApplication application1 = new ExhibitionApplication();
        application1.setState(new ApplicationDeclinedState(application1));
        application1.setDecision(false, "test");
        application1.setKeywordsList(keywordsList1);

        List<Keyword> keywordsList2 = new ArrayList<>();
        keywordsList2.add(keyword1);
        keywordsList2.add(keyword2);
        keywordsList2.add(keyword3);
        ExhibitionApplication application2 = new ExhibitionApplication();
        application2.setState(new ApplicationDeclinedState(application2));
        application2.setKeywordsList(keywordsList2);

        ExhibitionApplication application3 = new ExhibitionApplication();
        application3.setState(new ApplicationAcceptedState(application3));
        application3.setDecision(true, "test");
        application3.setKeywordsList(keywordsList2);

        List<Application> applications = new ArrayList<>();
        applications.add(application1);
        applications.add(application2);
        applications.add(application3);
        ApplicationsList instance = new ApplicationsList(applications);

        Pair<Keyword, Integer> pair1 = new Pair(keyword1, 2);
        Pair<Keyword, Integer> pair2 = new Pair(keyword2, 2);
        Pair<Keyword, Integer> pair3 = new Pair(keyword3, 1);
        List<Pair<Keyword, Integer>> expResult = new ArrayList<>();
        expResult.add(pair1);
        expResult.add(pair2);
        expResult.add(pair3);

        List<Pair<Keyword, Integer>> result = instance.calculateDeclinedAppsKeywordsRankings();

        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ApplicationsList.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList();
        assertTrue(this.applicationsList.equals(otherObject));
    }

}
