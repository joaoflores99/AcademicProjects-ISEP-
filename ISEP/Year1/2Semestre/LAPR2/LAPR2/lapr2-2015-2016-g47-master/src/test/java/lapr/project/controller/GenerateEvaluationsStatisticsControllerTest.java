/**
 * Package location for Apllication Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Actor;
import lapr.project.model.ApplicationAnalysis;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the classe generate evaluations statistics.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class GenerateEvaluationsStatisticsControllerTest {

    /**
     * The generate evaluations statistics controller to be tested
     */
    private GenerateEvaluationsStatisticsController generateEvaluationsStatisticsController;

    /**
     * The exhibition center to be used in tests.
     */
    private ExhibitionCenter exhibitionCenter;

    /**
     * The actor logged in.
     */
    private Actor actor;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)
                .setState(new ExhibitionDecidedApplicationsState(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)));
        this.actor = new ExhibitionsManager();
        this.generateEvaluationsStatisticsController = new GenerateEvaluationsStatisticsController(this.exhibitionCenter, this.actor);
    }

    /**
     * Test of getExhibitionsList method, of class
     * GenerateEvaluationsStatisticsController.
     */
    @Test
    public void testGetExhibitionsList() {
        System.out.println("getExhibitionsList");
        List<Exhibition> expResult = this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList();
        List<Exhibition> result = this.generateEvaluationsStatisticsController.getExhibitionsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAcceptanceRate method, of class
     * GenerateEvaluationsStatisticsController.
     */
    @Test
    public void testGetAcceptanceRate() {
        System.out.println("getAcceptanceRate");
        float expResult = 0.00f;
        this.generateEvaluationsStatisticsController.selectExhibition(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0));
        float result = this.generateEvaluationsStatisticsController.getAcceptanceRate();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getApplicationsAnalysis method, of class
     * GenerateEvaluationsStatisticsController.
     */
    @Test
    public void testGetApplicationsAnalysis() {
        System.out.println("getApplicationsAnalysis");
        this.generateEvaluationsStatisticsController.selectExhibition(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0));
        List<ApplicationAnalysis> expResult = new ArrayList<>();
        expResult.add(new ApplicationAnalysis(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)));
        List<ApplicationAnalysis> result = this.generateEvaluationsStatisticsController.getApplicationsAnalysis();
        assertEquals(expResult, result);
    }
}
