/**
 * Package location for Apllication Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Evaluable;
import lapr.project.model.Evaluation;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.StaffAttribution;
import lapr.project.model.StaffMember;
import lapr.project.model.Submittable;
import lapr.project.model.application.ApplicationInEvaluationState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a evaluate application controller class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class EvaluateApplicationsControllerTest {

    /**
     * The exhibition center to be used in tests.
     */
    private ExhibitionCenter exhibitionCenter;

    /**
     * The evaluate applications controller to be tested.
     */
    private EvaluateApplicationsController evaluateApplicationsController;

    /**
     * The staff member used for tests.
     */
    private StaffMember staffMember;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();

        this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList()
                .getApplicationsList().get(0).setState(new ApplicationInEvaluationState(this.exhibitionCenter
                .getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)));

        this.staffMember = this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)
                .getStaffList().getStaffList().get(0);

        this.evaluateApplicationsController = new EvaluateApplicationsController(this.exhibitionCenter);
    }

    /**
     * Test of getSubmittablesByStaff method, of class
     * EvaluateApplicationsController.
     */
    @Test
    public void testGetSubmittablesByStaff() {
        System.out.println("getSubmittablesByStaff");

        List<Submittable> expResult = new ArrayList<>();
        expResult.add(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0));
        expResult.add(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getDemonstrationsList().getDemonstrationsList().get(0));

        List<Submittable> result = evaluateApplicationsController.getSubmittablesByStaff(this.staffMember);

        assertEquals(expResult, result);
    }

    /**
     * Test of getAttributionsByStaff method, of class
     * EvaluateApplicationsController.
     */
    @Test
    public void testGetAttributionsByStaff() {
        System.out.println("getAttributionsByStaff");

        this.evaluateApplicationsController.setSubmittable(this.exhibitionCenter.
                getExhibitionsRegister().getExhibitionsList().get(0));

        List<StaffAttribution> expResult = new ArrayList<>();
        expResult.add(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)
                .getStaffAttributionsList().getStaffAttributionsList().get(0));

        List<StaffAttribution> result = this.evaluateApplicationsController.getAttributionsByStaff(staffMember);

        assertEquals(expResult, result);
    }

    /**
     * Test of getEvaluableByAttribution method, of class
     * EvaluateApplicationsController.
     */
    @Test
    public void testGetEvaluableByAttribution() {
        System.out.println("getEvaluableByAttribution");

        StaffAttribution staffAttribution = this.exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getStaffAttributionsList().getStaffAttributionsList().get(0);

        this.evaluateApplicationsController.setSubmittable(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0));

        Evaluable expResult = (Evaluable) this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        Evaluable result = this.evaluateApplicationsController.getEvaluableByAttribution(staffAttribution);

        assertEquals(expResult, result);
    }

    /**
     * Test of newEvaluation method, of class EvaluateApplicationsController.
     */
    @Test
    public void testNewEvaluation() {
        System.out.println("newEvaluation");
        
        this.evaluateApplicationsController.setSubmittable(this.exhibitionCenter.
                getExhibitionsRegister().getExhibitionsList().get(0));
        StaffAttribution staffAttribution = this.exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getStaffAttributionsList().getStaffAttributionsList().get(0);
        this.evaluateApplicationsController.getEvaluableByAttribution(staffAttribution);
        
        List<String> expResult = new Evaluation().getQuestionsList();
        List<String> result = this.evaluateApplicationsController.newEvaluation();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setEvaluation method, of class EvaluateApplicationsController.
     */
    @Test
    public void testSetEvaluation() {
        System.out.println("setEvaluation");
        
        this.evaluateApplicationsController.setSubmittable(this.exhibitionCenter.
                getExhibitionsRegister().getExhibitionsList().get(0));
        StaffAttribution staffAttribution = this.exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getStaffAttributionsList().getStaffAttributionsList().get(0);
        this.evaluateApplicationsController.getEvaluableByAttribution(staffAttribution);
        this.evaluateApplicationsController.newEvaluation();
        
        List<Integer> answersList = new ArrayList<>();
        answersList.add(1);
        answersList.add(2);
        answersList.add(1);
        answersList.add(4);
        answersList.add(3);
        
        assertTrue(this.evaluateApplicationsController.setEvaluation(answersList));
    }

    /**
     * Test of registerEvaluation method, of class
     * EvaluateApplicationsController.
     */
    @Test
    public void testRegisterEvaluation() {
        System.out.println("registerEvaluation");
        
        this.evaluateApplicationsController.setSubmittable(this.exhibitionCenter.
                getExhibitionsRegister().getExhibitionsList().get(0));
        StaffAttribution staffAttribution = this.exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getStaffAttributionsList().getStaffAttributionsList().get(0);
        this.evaluateApplicationsController.getEvaluableByAttribution(staffAttribution);
        this.evaluateApplicationsController.newEvaluation();
        List<Integer> answersList = new ArrayList<>();
        answersList.add(1);
        answersList.add(2);
        answersList.add(1);
        answersList.add(4);
        answersList.add(3);
        this.evaluateApplicationsController.setEvaluation(answersList);
        
        assertTrue(this.evaluateApplicationsController.registerEvaluation());
    }

    /**
     * Test of removeStaffAttributions method, of class
     * EvaluateApplicationsController.
     */
    @Test
    public void testRemoveStaffAttributions() {
        System.out.println("removeStaffAttributions");
        
        this.evaluateApplicationsController.setSubmittable(this.exhibitionCenter.
                getExhibitionsRegister().getExhibitionsList().get(0));
        StaffAttribution staffAttribution = this.exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getStaffAttributionsList().getStaffAttributionsList().get(0);
        this.evaluateApplicationsController.getEvaluableByAttribution(staffAttribution);
        this.evaluateApplicationsController.newEvaluation();
        List<Integer> answersList = new ArrayList<>();
        answersList.add(1);
        answersList.add(2);
        answersList.add(1);
        answersList.add(4);
        answersList.add(3);
        this.evaluateApplicationsController.setEvaluation(answersList);
        this.evaluateApplicationsController.addToRecord();
        
        assertTrue(this.evaluateApplicationsController.removeStaffAttributions());
    }

}
