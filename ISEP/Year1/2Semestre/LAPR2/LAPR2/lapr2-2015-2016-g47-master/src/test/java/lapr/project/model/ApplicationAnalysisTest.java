/**
 * Package location for Apllication Controllers tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the classe application analysis.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationAnalysisTest {
    
    /**
     * The instance to be tested.
     */
    private ApplicationAnalysis applicationAnalysis;
    
    /**
     * The exhibition center with the data for tests.
     */
    private ExhibitionCenter exhibitionCenter;
    
    /**
     * The application used for tests.
     */
    private Application application;
    
    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.application = this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        this.applicationAnalysis = new ApplicationAnalysis(this.application);
    }

    /**
     * Test of getAnswersAverage method, of class ApplicationAnalysis.
     */
    @Test
    public void testGetAnswersAverage() {
        System.out.println("getAnswersAverage");
        List<Float> expResult = new ArrayList<>();
        expResult.add(2.50f);
        expResult.add(3.50f);
        expResult.add(4.50f);
        expResult.add(1.50f);
        expResult.add(2.50f);
        List<Float> result = this.applicationAnalysis.getAnswersAverage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalAverage method, of class ApplicationAnalysis.
     */
    @Test
    public void testGetTotalAverage() {
        System.out.println("getTotalAverage");
        float expResult = 2.90f;
        float result = this.applicationAnalysis.getTotalAverage();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getApplicationDisplayInfo method, of class ApplicationAnalysis.
     */
    @Test
    public void testGetApplicationDisplayInfo() {
        System.out.println("getApplicationDisplayInfo");
        String expResult = "Node JS";
        String result = this.applicationAnalysis.getApplicationDisplayInfo();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class ApplicationAnalysis.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new ApplicationAnalysis(this.application);
        this.applicationAnalysis.equals(otherObject);
    }
    
}
