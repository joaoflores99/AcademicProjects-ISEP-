/**
 * Package location for Apllication Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Demonstration;
import lapr.project.model.Editable;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests an edit application controller class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class EditApplicationControllerTest {

    /**
     * The exhibition center to be used in tests.
     */
    private ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitor responsible to be used in tests.
     */
    private ExhibitorResponsible exhibitorResponsible;

    /**
     * The controller being tested.
     */
    private EditApplicationController editApplicationController;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        this.exhibitorResponsible = this.exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getApplicationsList().getApplicationsList()
                .get(0).getExhibitor().getExhibitorResponsible();
        this.editApplicationController = new EditApplicationController(this.exhibitionCenter, this.exhibitorResponsible);
    }

    /**
     * Test of cloneEditable method, of class EditApplicationController.
     */
    @Test
    public void testCloneEditable() {
        System.out.println("cloneEditable");

        this.editApplicationController.setSubmittable(this.exhibitionCenter
                .getExhibitionsRegister().getExhibitionsList().get(0));

        Editable expResult = (Editable) this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        Editable result = this.editApplicationController.cloneEditable();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class EditApplicationController.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        this.editApplicationController.setSubmittable(this.exhibitionCenter
                .getExhibitionsRegister().getExhibitionsList().get(0));
        this.editApplicationController.cloneEditable();

        Editable newEditable = (Editable) this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        newEditable.setTitle("ABC");
        newEditable.setNumberInvitations(20);

        assertTrue(this.editApplicationController.validate(newEditable));
    }

    /**
     * Test of modifyEditable method, of class EditApplicationController.
     */
    @Test
    public void testModifyEditable() {
        System.out.println("modifyEditable");

        this.editApplicationController.setSubmittable(this.exhibitionCenter
                .getExhibitionsRegister().getExhibitionsList().get(0));
        this.editApplicationController.cloneEditable();

        Editable newEditable = (Editable) this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        newEditable.setTitle("ABC");
        newEditable.setNumberInvitations(20);
        this.editApplicationController.validate(newEditable);

        assertTrue(this.editApplicationController.modifyEditable());
    }

    /**
     * Test of getAvailableDemonstrationsInExhibition mehod, of class
     * EditApplicationController.
     */
    @Test
    public void testGetAvailableDemonstrationsInExhibition() {
        System.out.println("getAvailableDemonstrationsInExhibition");

        this.editApplicationController.setSubmittable(this.exhibitionCenter
                .getExhibitionsRegister().getExhibitionsList().get(0));

        List<Demonstration> expResult = new ArrayList<>();
        expResult.add(this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList()
                .get(0).getDemonstrationsList().getDemonstrationsList().get(0));
        List<Demonstration> result = this.editApplicationController.getAvailableDemonstrationsInExhibition();

        assertEquals(expResult, result);
    }
}
