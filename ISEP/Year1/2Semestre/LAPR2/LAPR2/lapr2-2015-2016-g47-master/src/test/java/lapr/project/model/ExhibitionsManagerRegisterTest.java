/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the exhibitions manager register class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionsManagerRegisterTest {

    /**
     * The exhibitions manager register to be tested.
     */
    private ExhibitionsManagerRegister exhibitionsManagerRegister;

    /**
     * The exhibition center to be used on tests.
     */
    private ExhibitionCenter exhibitionCenter;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.exhibitionsManagerRegister = this.exhibitionCenter.getExhibitionsManagerRegister();
    }

    /**
     * Test of getExhibitionsManagerList method, of class
     * ExhibitionsManagerRegister.
     */
    @Test
    public void testGetExhibitionsManagerList() {
        System.out.println("getExhibitionsManagerList");
        List<ExhibitionsManager> expResult = new ArrayList<>();
        expResult.add(new ExhibitionsManager(new User("Daniel Gonçalves", "danielgoncalves", "1151452@isep.ipp.pt", "Qwe+123", new ArrayList<>(),"")));
        List<ExhibitionsManager> result = this.exhibitionsManagerRegister.getExhibitionsManagerList();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasAnyExhibitionManager method, of class
     * ExhibitionsManagerRegister.
     */
    @Test
    public void testHasAnyExhibitionManager() {
        System.out.println("hasAnyExhibitionManager");
        assertTrue(this.exhibitionsManagerRegister.hasAnyExhibitionManager());
    }

    /**
     * Test of equals method, of class ExhibitionsManagerRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = this.exhibitionCenter.getExhibitionsManagerRegister();
        assertTrue(this.exhibitionsManagerRegister.equals(otherObject));
    }

}
