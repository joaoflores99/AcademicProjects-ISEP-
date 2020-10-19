/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
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
public class ImportExhibitionControllerTest {

    ImportExhibitionController controller;
    ExhibitionCenter exhibitionCenter;
      File xmlFile ;

    public ImportExhibitionControllerTest() {
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
        ExhibitionsManager manager = new ExhibitionsManager();
        controller = new ImportExhibitionController(manager, exhibitionCenter);
         Exhibition exhibitionTest = new Exhibition();//exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0);
        
        try{
        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(Exhibition.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
         xmlFile = new File("exhibitionTest.xml");

        m.marshal(exhibitionTest, System.out);

        m.marshal(exhibitionTest, xmlFile);

        JAXBContext jaxbContext = JAXBContext.newInstance(Exhibition.class);
        }
        catch(JAXBException ex){
            
        }

    }

    @After
    public void tearDown() {
        xmlFile.delete();
    }

    /**
     * Test of getExhibitionsRegister method, of class
     * ImportExhibitionController.
     */
//    @Test
//    public void testGetExhibitionsRegister() {
//        System.out.println("getExhibitionsRegister");
//        ImportExhibitionController instance = controller;
//      
//       ExhibitionCenter exhibitionCenterTest = DefaultInstantiator.createExhibitionCenter();
//        assertEquals(instance.getExhibitionsRegister(), exhibitionCenterTest.getExhibitionsRegister());
//       
//    }
    /**
     * Test of readExhibitionFromFile method, of class
     * ImportExhibitionController.
     */
    @Test
    public void testReadExhibitionFromFile() {
        System.out.println("readExhibitionFromFile");
        String filePath = "exhibitionTest.xml";
        ImportExhibitionController instance = controller;

        instance.readExhibitionFromFile(filePath);
        Exhibition exhibition = instance.getExhibition();
        Exhibition expectedExhibition = new Exhibition();
        assertEquals(exhibition, expectedExhibition);

    }

    /**
     * Test of registerExhibition method, of class ImportExhibitionController.
     */
//    @Test
//    public void testRegisterExhibition() {
//        System.out.println("registerExhibition");
//        Exhibition exhibition = null;
//        ImportExhibitionController instance = null;
//        instance.registerExhibition(exhibition);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
