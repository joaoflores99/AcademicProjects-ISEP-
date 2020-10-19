/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.application.ApplicationAcceptedState;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
import lapr.project.utils.DefaultInstantiator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class AssignStandsControllerTest {

    AssignStandsController controller;
    Organizer organizer;
    List<Exhibition> expectedResult;
    List<ExhibitionApplication>expectedResultApplication;
    ExhibitionCenter exhibitionCenter;

    public AssignStandsControllerTest() {
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
        expectedResult = new ArrayList();
        expectedResultApplication = new ArrayList();
        Application application = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        application.setState(new ApplicationAcceptedState(application));
        expectedResultApplication.add((ExhibitionApplication)application);
        organizer = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0);
        exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).setState(new ExhibitionDecidedApplicationsState(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)));
        expectedResult.add(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0));
        controller = new AssignStandsController(organizer, exhibitionCenter);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getExhibitionsListByOrganizerInApplicationsDecidedState method,
     * of class AssignStandsController.
     */
    @Test
    public void testGetExhibitionsListByOrganizerInApplicationsDecidedState() {
        System.out.println("getExhibitionsListByOrganizerInApplicationsDecidedState");

        AssignStandsController instance = controller;
        List<Exhibition> expResult = expectedResult;
        List<Exhibition> result = instance.getExhibitionsListByOrganizerInApplicationsDecidedState(this.organizer);
        assertEquals(expResult, result);

    }

    /**
     * Test of getApplicationsList method, of class AssignStandsController.
     */
    @Test
    public void testGetApplicationsList() {
        System.out.println("getApplicationsList");
        Exhibition exhibition = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        AssignStandsController instance = controller;
        List<ExhibitionApplication> expResult = expectedResultApplication;
        List<ExhibitionApplication> result = instance.getApplicationsList(exhibition);
        assertEquals(expResult, result);
       
    }

}
