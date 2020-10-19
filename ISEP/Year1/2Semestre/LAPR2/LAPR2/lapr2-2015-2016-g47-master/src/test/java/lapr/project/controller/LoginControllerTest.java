 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.ExhibitionCenter;
import lapr.project.model.User;
import lapr.project.utils.DefaultInstantiator;
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
public class LoginControllerTest {
    LoginController controller;
       ExhibitionCenter ex ;
    public LoginControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ex = DefaultInstantiator.createExhibitionCenter();
        ex.getUsersRegister().getUser("danielgoncalves").setConfirmedStatus(true);
        controller = new LoginController(ex);
     
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of verifyLogin method, of class LoginController.
     */
//    @Test
//    public void testVerifyLogin() {
//        System.out.println("verifyLogin");
//        String userName = "danielgoncalves";
//        String password = "Qwe+123";
//        LoginController instance = controller;
//        
//        User result = instance.verifyLogin(userName, password);
//        assertTrue(result!=null);
//      
//    }

    /**
     * Test of verifyUserByStaffMember method, of class LoginController.
     */
    @Test
    public void testVerifyUserByStaffMember() {
        System.out.println("verifyUserByStaffMember");
        User user =  ex.getUsersRegister().getUser("danielgoncalves");
        LoginController instance = controller;

        boolean result = instance.verifyUserByStaffMember(user);
        assertFalse(result);
    }

    /**
     * Test of verifyUserByOrganizer method, of class LoginController.
     */
    @Test
    public void testVerifyUserByOrganizer() {
        System.out.println("verifyUserByOrganizer");
        User user =    ex.getUsersRegister().getUser("danielgoncalves");
        LoginController instance = controller;

        boolean result = instance.verifyUserByOrganizer(user);
        assertFalse(result);
       
    }

    /**
     * Test of verifyUserByExhibitorResponsible method, of class LoginController.
     */
    @Test
    public void testVerifyUserByExhibitorResponsible() {
        System.out.println("verifyUserByExhibitorResponsible");
        User user =  ex.getUsersRegister().getUser("danielgoncalves");
        LoginController instance = controller;

        boolean result = instance.verifyUserByExhibitorResponsible(user);
        assertFalse(result);
        
    }

    /**
     * Test of verifyUserByExhibitionsManager method, of class LoginController.
     */
    @Test
    public void testVerifyUserByExhibitionsManager() {
        System.out.println("verifyUserByExhibitionsManager");
        User user = ex.getUsersRegister().getUser("danielgoncalves");
        LoginController instance =controller;

        boolean result = instance.verifyUserByExhibitionsManager(user);
        assertTrue(result);
   
    }
    
}
