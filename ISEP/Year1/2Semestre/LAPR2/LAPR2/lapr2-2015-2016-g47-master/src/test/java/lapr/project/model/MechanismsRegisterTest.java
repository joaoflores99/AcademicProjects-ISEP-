/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.mechanisms.attribution.EquitableLoadMechanism;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the mechanisms register class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class MechanismsRegisterTest {

    /**
     * The mechanisms register to be used on tests.
     */
    private MechanismsRegister mechanismsRegister;

    /**
     * The exibition center to be used on tests.
     */
    private ExhibitionCenter exhibitionCenter;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.mechanismsRegister = this.exhibitionCenter.getMechanismsRegister();
    }

    /**
     * Test of getAttributionMechanismList method, of class MechanismsRegister.
     */
    @Test
    public void testGetAttributionMechanismList() {
        System.out.println("getAttributionMechanismList");
        List<StaffAttributionMechanism> expResult = this.exhibitionCenter.getMechanismsRegister().getAttributionMechanismList();
        List<StaffAttributionMechanism> result = this.mechanismsRegister.getAttributionMechanismList();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class MechanismsRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = this.exhibitionCenter.getMechanismsRegister();
        assertTrue(this.mechanismsRegister.equals(otherObject));
    }

}
