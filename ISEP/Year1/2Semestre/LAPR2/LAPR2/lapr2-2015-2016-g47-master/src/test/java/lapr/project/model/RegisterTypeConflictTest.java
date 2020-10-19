/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.DefineTypeConflictsController;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the conflict types register class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class RegisterTypeConflictTest {

    /**
     * The defineTypeConflictsController.
     */
    private DefineTypeConflictsController defineTypeConflictsController;

    /**
     * A valid conflict type.
     */
    private ConflictType validConflictType;

    /**
     * An existing conflict type.
     */
    private ConflictType existingConflictType;

    /**
     * The conflict types register.
     */
    private ConflictTypesRegister conflictTypesRegister;

    /**
     * The conflict detection mechanism.
     */
    private ConflictDetectionMechanism conflictDetetionMechanism;

    /**
     * The conflict types list.
     */
    private List<ConflictType> conflictTypesList = new ArrayList();

    /**
     * The conflict type1.
     */
    private ConflictType conflictType1;

    /**
     * The conflict type2.
     */
    private ConflictType conflictType2;

    @Before
    public void setUp() {

        this.conflictDetetionMechanism = new ConflictDetectionMechanism() {

            @Override
            public boolean detectConflict(StaffMember staffMember, Application application) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getDescription() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        this.validConflictType = new ConflictType(this.conflictDetetionMechanism, "Mista");

        this.conflictType1 = new ConflictType(this.conflictDetetionMechanism, "1");
        this.conflictType2 = new ConflictType(this.conflictDetetionMechanism, "2");

        ExhibitionCenter exhibitionCenter = new ExhibitionCenter();
        ExhibitionsManager exhibitionsManager = new ExhibitionsManager();
        this.defineTypeConflictsController = new DefineTypeConflictsController(exhibitionCenter, exhibitionsManager);

        this.conflictTypesList.add(conflictType1);
        this.conflictTypesList.add(conflictType2);
        this.conflictTypesRegister = new ConflictTypesRegister(conflictTypesList);

        this.existingConflictType = this.conflictTypesRegister.getConflictTypesList().get(0);

    }

    /**
     * Test of validateTypeConflict method, of class ConflictTypesRegister with
     * a existing TypeConflict.
     */
    @Test
    public void testValidateTypeConflictWithExistingTypeConflict() {
        System.out.println("validateTypeConflict with existing typeConflict");
        assertFalse(conflictTypesRegister.validateTypeConflict(this.existingConflictType));
    }

    /**
     * Test of registerTypeConflict method, of class ConflictTypesRegister.
     */
    @Test
    public void testRegisterTypeConflict() {
        System.out.println("registerTypeConflict");
        assertTrue(this.conflictTypesRegister.registerTypeConflict(this.validConflictType));
    }
//    //TODO verify the exception and try to solve this: Exception occurred in target VM
//    /**
//     * Test of registerTypeConflict method, of class ConflictTypesRegister with an
//     * existing TypeConflict.
//     */
//    @Test
//    public void testRegisterTypeConflictWithExistingTypeConflict() {
//        System.out.println("registerTypeConflict with existing TypeConflict");
//        assertFalse(this.conflictTypesRegister.registerTypeConflict(this.existingConflictType));
//    }
}
