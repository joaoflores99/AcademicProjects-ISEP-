/**
 * Package location for Model concept tests.
 */
package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.ConflictDetectionMechanism;
import lapr.project.model.ConflictType;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.StaffMember;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a define type conflicts controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineTypeConflictsControllerTest {

    /**
     * The defineTypeConflictsController to be tested.
     */
    private DefineTypeConflictsController defineTypeConflictsController;

    /**
     * The conflict type expected result.
     */
    private ConflictType expResult;

    /**
     * The conflict detetion mechanism.
     */
    private ConflictDetectionMechanism conflictDetectionMechanism;

    @Before
    public void setUp() {
        ExhibitionCenter exhibitionCenter = new ExhibitionCenter();
        ExhibitionsManager exhibitionsManager = new ExhibitionsManager();
        this.defineTypeConflictsController = new DefineTypeConflictsController(exhibitionCenter, exhibitionsManager);

        this.conflictDetectionMechanism = new ConflictDetectionMechanism() {

            @Override
            public boolean detectConflict(StaffMember staffMember, Application application) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getDescription() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        //this.expResult = new ConflictType(conflictDetectionMechanism, "TipoConflitoFamiliar");
    }

    /**
     * Test of newConflictType method, of class DefineTypeConflictsController.
     */
    @Test
    public void testNewConflictType() {
        System.out.println("newConflictType");
        boolean resultNew = this.defineTypeConflictsController.newConflictType();
        assertTrue(resultNew);
    }
    

    /**
     * Test of newConflictType method, of class DefineTypeConflictsController.
     */
    @Test
    public void testConflictTypeSetData() {
        System.out.println("newConflictType");
        this.defineTypeConflictsController.newConflictType();
        boolean resultData = this.defineTypeConflictsController.setDataConflictType("cenas");
        assertTrue(resultData);
    }
    

    /**
     * Test of newConflictType method, of class DefineTypeConflictsController.
     */
    @Test
    public void testNewConflictTypeAddToRegister() {
        System.out.println("newConflictType");
        this.defineTypeConflictsController.newConflictType();
        this.defineTypeConflictsController.setDataConflictType("cenas");
        boolean resultAdd = this.defineTypeConflictsController.registerTypeConflict();
        assertTrue(resultAdd);
    }

}
