/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.ApplicationsList;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationApplication;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
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
public class CreateDemonstrationApplicationControllerTest {

    ExhibitionCenter exhibitionCenter;
    CreateDemonstrationApplicationController controller;
    Exhibition exhibition;

    public CreateDemonstrationApplicationControllerTest() {
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
        ExhibitionApplication application = ((ExhibitionApplication) exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0));
        ExhibitorResponsible exhibitorResponsible = application.getExhibitorResponsible();
        exhibition = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        exhibition.setState(new ExhibitionDecidedApplicationsState(exhibition));
        controller = new CreateDemonstrationApplicationController(exhibitorResponsible, exhibitionCenter);
    }

    @After
    public void tearDown() {
        
    }

    /**
     * Test of getExhibitionListWithApplicationsInSubmission method, of class
     * CreateDemonstrationApplicationController.
     */
    @Test
    public void testGetExhibitionListWithApplicationsInSubmission() {
        System.out.println("getExhibitionListWithApplicationsInSubmission");
        CreateDemonstrationApplicationController instance = controller;
        List<Exhibition> exhibitionList = new ArrayList();
        exhibitionList.add(exhibition);
        List<Exhibition> expResult = exhibitionList;
        List<Exhibition> result = instance.getExhibitionListWithApplicationsInSubmission();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDemonstrationsList method, of class
     * CreateDemonstrationApplicationController.
     */
//    @Test
//    public void testGetDemonstrationsList() {
//        System.out.println("getDemonstrationsList");
//        Exhibition exhibition = null;
//        CreateDemonstrationApplicationController instance = null;
//        List<Demonstration> expResult = null;
//        List<Demonstration> result = instance.getDemonstrationsList(exhibition);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of newDemonstrationApplication method, of class
     * CreateDemonstrationApplicationController.
     */
    @Test
    public void testNewDemonstrationApplication() {
        System.out.println("newDemonstrationApplication");
        Demonstration demonstration = exhibition.getDemonstrationsList().getDemonstrationsList().get(0);
        ApplicationsList applicationsList = demonstration.getApplicationsList();
        DemonstrationApplication demonstrationApplication = new DemonstrationApplication();

        CreateDemonstrationApplicationController instance = controller;
        instance.newDemonstrationApplication(demonstration);
        DemonstrationApplication result = instance.getDemonstrationApplication();
        assertEquals(result, demonstrationApplication);

    }

    /**
     * Test of setData method, of class
     * CreateDemonstrationApplicationController.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        String title = "";
        int numberInvitations = 0;
        CreateDemonstrationApplicationController instance = controller;
        Demonstration demonstration = new Demonstration();
         instance.newDemonstrationApplication(demonstration);
        DemonstrationApplication result = instance.getDemonstrationApplication();
        instance.setData(title, numberInvitations);
        DemonstrationApplication demonstrationApplication = new DemonstrationApplication();
        demonstrationApplication.setTitle(title);
        demonstrationApplication.setNumberInvitations(numberInvitations);
        assertEquals(result, instance.getDemonstrationApplication());
    }

    /**
     * Test of setProductsList method, of class
     * CreateDemonstrationApplicationController.
     */
//    @Test
//    public void testSetProductsList() {
//        System.out.println("setProductsList");
//        List<Product> productsList = null;
//        CreateDemonstrationApplicationController instance = null;
//        instance.setProductsList(productsList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getProductsList method, of class
     * CreateDemonstrationApplicationController.
     */
//    @Test
//    public void testGetProductsList() {
//        System.out.println("getProductsList");
//        CreateDemonstrationApplicationController instance = null;
//        List<Product> expResult = null;
//        List<Product> result = instance.getProductsList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getKeywordsList method, of class
     * CreateDemonstrationApplicationController.
     */
//    @Test
//    public void testGetKeywordsList() {
//        System.out.println("getKeywordsList");
//        CreateDemonstrationApplicationController instance = null;
//        List<Keyword> expResult = null;
//        List<Keyword> result = instance.getKeywordsList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of registerDemonstrationApplication method, of class
     * CreateDemonstrationApplicationController.
     */
    @Test
    public void testRegisterDemonstrationApplication() {
        System.out.println("registerDemonstrationApplication");
         CreateDemonstrationApplicationController instance = controller;
        Demonstration demonstration = new Demonstration();
         instance.newDemonstrationApplication(demonstration);
        
        instance.setData("TestTitle", 10);
      
        boolean result = instance.registerDemonstrationApplication();
        assertTrue(result);
      
    }

}
