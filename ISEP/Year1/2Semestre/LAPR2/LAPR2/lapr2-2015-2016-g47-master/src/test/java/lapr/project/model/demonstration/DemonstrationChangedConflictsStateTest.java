/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.demonstration;

import lapr.project.model.Demonstration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class DemonstrationChangedConflictsStateTest {
    Demonstration demonstration;
    
    public DemonstrationChangedConflictsStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
     
   @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        demonstration= new Demonstration();
        demonstration.setCurrentState(new DemonstrationChangedConflictsState(demonstration));
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of isInicial method, of class DemonstrationChangedConflictsState.
     */
    /**
     * Test of isInicial method, of class DemonstrationApplicationsInEvaluationState.
     */
         @Test
    public void testIsInicial() {
        System.out.println("isInicial");

        boolean expResult = false;
        boolean result = demonstration.getCurrentState().isInicial();
        assertFalse(result);

    }

    /**
     * Test of isCreated method, of class DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsCreated() {
        System.out.println("isCreated");

        DemonstrationApplicationsDecidedState instance = null;

        boolean result = demonstration.getCurrentState().isCreated();
        assertFalse(result);

    }

    /**
     * Test of setCreated method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetCreated() {
        System.out.println("setCreated");

        DemonstrationApplicationsDecidedState instance = null;

        boolean result = demonstration.getCurrentState().setCreated();
        assertFalse(result);

    }

    /**
     * Test of isDiscontinued method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsDiscontinued() {
        System.out.println("isDiscontinued");

        boolean result = demonstration.getCurrentState().isDiscontinued();
        assertFalse(result);

    }

    /**
     * Test of setDiscontinued method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetDiscontinued() {
        System.out.println("setDiscontinued");

        boolean expResult = false;
        boolean result = demonstration.getCurrentState().setDiscontinued();
        assertFalse(result);

    }

    /**
     * Test of isDecided method, of class DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsDecided() {
        System.out.println("isDecided");

        boolean result = demonstration.getCurrentState().isDecided();
        assertFalse(result);

    }

    /**
     * Test of setDecided method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetDecided() {
        System.out.println("setDecided");

        boolean result = demonstration.getCurrentState().setDecided();
        assertFalse(result);

    }

    /**
     * Test of isOpenedApplications method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsOpenedApplications() {
        System.out.println("isOpenedApplications");

        boolean result = demonstration.getCurrentState().isOpenedApplications();
        assertFalse(result);

    }

    /**
     * Test of setOpenApplications method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetOpenApplications() {
        System.out.println("setOpenApplications");

        boolean result = demonstration.getCurrentState().setOpenApplications();
        assertFalse(result);

    }

    /**
     * Test of isClosedApplications method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsClosedApplications() {
        System.out.println("isClosedApplications");

        boolean result = demonstration.getCurrentState().isClosedApplications();
        assertFalse(result);

    }

    /**
     * Test of setClosedApplications method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetClosedApplications() {
        System.out.println("setClosedApplications");

        boolean result = demonstration.getCurrentState().setClosedApplications();
        assertFalse(result);

    }

    /**
     * Test of isDetectedConflicts method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsDetectedConflicts() {
        System.out.println("isDetectedConflicts");
         boolean result = demonstration.getCurrentState().isDetectedConflicts();
        assertFalse(result);

    }

    /**
     * Test of setDetectedConflicts method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetDetectedConflicts() {
        System.out.println("setDetectedConflicts");
         boolean result = demonstration.getCurrentState().setDetectedConflicts();
        assertFalse(result);
     
    }

    /**
     * Test of isChangedConflicts method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsChangedConflicts() {
        System.out.println("isChangedConflicts");
         boolean result = demonstration.getCurrentState().isChangedConflicts();
        assertTrue(result);
      
    }

    /**
     * Test of setChangedConflicts method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetChangedConflicts() {
        System.out.println("setChangedConflicts");
          boolean result = demonstration.getCurrentState().setChangedConflicts();
        assertFalse(result);
      
    }

    /**
     * Test of isApplicationsInEvaluation method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsApplicationsInEvaluation() {
        System.out.println("isApplicationsInEvaluation");
         boolean result = demonstration.getCurrentState().isApplicationsInEvaluation();
        assertFalse(result);
     
    }

    /**
     * Test of setApplicationsInEvaluation method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetApplicationsInEvaluation() {
        System.out.println("setApplicationsInEvaluation");
         boolean result = demonstration.getCurrentState().setApplicationsInEvaluation();
        assertFalse( result);
       
    }

    /**
     * Test of isApplicationsInDecision method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsApplicationsInDecision() {
        System.out.println("isApplicationsInDecision");
         boolean result = demonstration.getCurrentState().isApplicationsInDecision();
        assertFalse(result);
  
    }

    /**
     * Test of setApplicationsInDecision method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetApplicationsInDecision() {
        System.out.println("setApplicationsInDecision");
        boolean result = demonstration.getCurrentState().setApplicationsInDecision();
        assertFalse(result);
     
    }

    /**
     * Test of isApplicationsDecided method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testIsApplicationsDecided() {
        System.out.println("isApplicationsDecided");
           boolean result = demonstration.getCurrentState().isApplicationsDecided();
        assertFalse(result);
   
    }

    /**
     * Test of setApplicationsDecided method, of class
     * DemonstrationApplicationsDecidedState.
     */
    @Test
    public void testSetApplicationsDecided() {
        System.out.println("setApplicationsDecided");
         boolean result = demonstration.getCurrentState().setApplicationsDecided();
        assertFalse(result);
    
    }
}
