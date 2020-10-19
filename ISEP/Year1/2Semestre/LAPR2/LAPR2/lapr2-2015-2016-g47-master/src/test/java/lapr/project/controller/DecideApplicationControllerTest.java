/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationsList;
import lapr.project.model.Decisable;
import lapr.project.model.Decision;
import lapr.project.model.Demonstration;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.application.ApplicationInDecisionState;
import lapr.project.model.application.ApplicationInEvaluationState;
import lapr.project.utils.DefaultInstantiator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class DecideApplicationControllerTest {
    
      DecideApplicationController controller ;
      Organizer organizer;
      ExhibitionCenter exhibitionCenter;
    public DecideApplicationControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        organizer = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0);
        
        controller = new DecideApplicationController(organizer, exhibitionCenter);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDecisablesByOrganizer method, of class DecideApplicationController.
     */
    @Test
    public void testGetDecisablesByOrganizer() {
        System.out.println("getDecisablesByOrganizer");
        DecideApplicationController instance = controller;
        ApplicationsList appList = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList();
        Application application =appList.getApplicationsList().get(0);
         application.setState(new ApplicationInEvaluationState(application)) ;
             application.setState(new ApplicationInDecisionState(application)) ;
        List<Decisable> expResult = new ArrayList();
        expResult.add((Decisable)application);
        List<Decisable> result = instance.getDecisablesByOrganizer();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setDecisable method, of class DecideApplicationController.
     */
//    @Test
//    public void testSetDecisable() {
//        System.out.println("setDecisable");
//        Decisable decisable = null;
//        DecideApplicationController instance = null;
//        instance.setDecisable(decisable);
//         TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getEvaluationsList method, of class DecideApplicationController.
     */
//    @Test
//    public void testGetEvaluationsList() {
//        System.out.println("getEvaluationsList");
//        DecideApplicationController instance = null;
//        List<Evaluation> expResult = null;
//        List<Evaluation> result = instance.getEvaluationsList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of createDecision method, of class DecideApplicationController.
     */
    @Test
    public void testCreateDecision() {
        System.out.println("createDecision");
        DecideApplicationController instance = controller;
        
        Decisable decisable = new ExhibitionApplication();
        instance.setDecisable(decisable);
        instance.createDecision();
        Decision decision = new Decision();
        assertEquals(decision, ((ExhibitionApplication)instance.getDecisable()).getDecision());
    }

    /**
     * Test of setDecision method, of class DecideApplicationController.
     */
//    @Test
//    public void testSetDecision() {
//        System.out.println("setDecision");
//        boolean decision = false;
//        String justificativeText = "";
//        DecideApplicationController instance = null;
//        instance.setDecision(decision, justificativeText);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of validateDecision method, of class DecideApplicationController.
     */
    @Test
    public void testValidateDecision() {
        System.out.println("validateDecision");
        DecideApplicationController instance = controller;
          ApplicationsList appList = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList();
        Application application =appList.getApplicationsList().get(0);
        ((ExhibitionApplication)application).getDemonstrationsList().add(new Demonstration());
         Decisable decisable = (Decisable)application;
        instance.setDecisable(decisable);
        instance.createDecision();
        instance.setDecision(true, "Test");
        
        assertTrue(instance.validateDecision());
    
    }

    /**
     * Test of registerDecision method, of class DecideApplicationController.
     */
//    @Test
//    public void testRegisterDecision() {
//        System.out.println("registerDecision");
//        DecideApplicationController instance = controller;
//        instance.registerDecision();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
