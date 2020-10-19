/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.application;

import lapr.project.model.Application;
import lapr.project.model.ApplicationState;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author IvoFerro
 */
public class ApplicationRemovedStateTest {
    
    /**
     * The application state to be used in tests.
     */
    private ApplicationState applicationState;
    
    @Before
    public void setUp() {
        Application application = DefaultInstantiator.createExhibitionCenter().getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        applicationState = new ApplicationRemovedState(application);
    }

    /**
     * Test of isInitial method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsInitial() {
        System.out.println("isInitial");
        assertFalse(this.applicationState.isInitial());
    }

    /**
     * Test of setInSubmission method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetInSubmission() {
        System.out.println("setInSubmission");
        assertFalse(this.applicationState.setInSubmission());
    }

    /**
     * Test of isInSubmission method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsInSubmission() {
        System.out.println("isInSubmission");
        assertFalse(this.applicationState.isInSubmission());
    }

    /**
     * Test of setRemoved method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetRemoved() {
        System.out.println("setRemoved");
        assertFalse(this.applicationState.setRemoved());
    }

    /**
     * Test of isRemoved method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsRemoved() {
        System.out.println("isRemoved");
        assertTrue(this.applicationState.isRemoved());
    }

    /**
     * Test of setInAttribution method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetInAttribution() {
        System.out.println("setInAttribution");
        assertFalse(this.applicationState.setInAttribution());
    }

    /**
     * Test of isInAttribtion method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsInAttribtion() {
        System.out.println("isInAttribtion");
        assertFalse(this.applicationState.isInAttribtion());
    }

    /**
     * Test of setInEvaluation method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetInEvaluation() {
        System.out.println("setInEvaluation");
        assertFalse(this.applicationState.setInEvaluation());
    }

    /**
     * Test of isInEvaluation method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsInEvaluation() {
        System.out.println("isInEvaluation");
        assertFalse(this.applicationState.isInEvaluation());
    }

    /**
     * Test of setNotEvaluated method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetNotEvaluated() {
        System.out.println("setNotEvaluated");
        assertFalse(this.applicationState.setNotEvaluated());
    }

    /**
     * Test of isNotEvaluated method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsNotEvaluated() {
        System.out.println("isNotEvaluated");
        assertFalse(this.applicationState.isNotEvaluated());
    }

    /**
     * Test of setInDecision method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetInDecision() {
        System.out.println("setInDecision");
        assertFalse(this.applicationState.setInDecision());
    }

    /**
     * Test of isInDecision method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsInDecision() {
        System.out.println("isInDecision");
        assertFalse(this.applicationState.isInDecision());
    }

    /**
     * Test of setAccepted method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetAccepted() {
        System.out.println("setAccepted");
        assertFalse(this.applicationState.setAccepted());
    }

    /**
     * Test of isAccepted method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsAccepted() {
        System.out.println("isAccepted");
        assertFalse(this.applicationState.isAccepted());
    }

    /**
     * Test of setDeclined method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetDeclined() {
        System.out.println("setDeclined");
        assertFalse(this.applicationState.setDeclined());
    }

    /**
     * Test of isDeclined method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsDeclined() {
        System.out.println("isDeclined");
        assertFalse(this.applicationState.isDeclined());
    }

    /**
     * Test of setAssignedStand method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetAssignedStand() {
        System.out.println("setAssignedStand");
        assertFalse(this.applicationState.setAssignedStand());
    }

    /**
     * Test of isAssignedStand method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsAssignedStand() {
        System.out.println("isAssignedStand");
        assertFalse(this.applicationState.isAssignedStand());
    }

    /**
     * Test of setConfirmedStand method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetConfirmedStand() {
        System.out.println("setConfirmedStand");
        assertFalse(this.applicationState.setConfirmedStand());
    }

    /**
     * Test of isConfirmedStand method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsConfirmedStand() {
        System.out.println("isConfirmedStand");
        assertFalse(this.applicationState.isConfirmedStand());
    }

    /**
     * Test of setNotConfirmedStand method, of class ApplicationRemovedState.
     */
    @Test
    public void testSetNotConfirmedStand() {
        System.out.println("setNotConfirmedStand");
        assertFalse(this.applicationState.setNotConfirmedStand());
    }

    /**
     * Test of isNotConfirmedStand method, of class ApplicationRemovedState.
     */
    @Test
    public void testIsNotConfirmedStand() {
        System.out.println("isNotConfirmedStand");
        assertFalse(this.applicationState.isNotConfirmedStand());
    }

    /**
     * Test of validate method, of class ApplicationRemovedState.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertFalse(this.applicationState.validate());
    }
    
}
