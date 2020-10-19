/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.demonstration.DemonstrationCreatedState;
import lapr.project.model.demonstration.DemonstrationDecidedState;
import lapr.project.utils.DefaultInstantiator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests a DefineEffectiveDemosController class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineEfectiveDemosControllerTest {

    private DefineEffectiveDemosController controller;

    @Before
    public void setUp() {

        controller = new DefineEffectiveDemosController(DefaultInstantiator.createExhibitionCenter());

        Demonstration demonstration = new Demonstration("demo1");
        demonstration.setCurrentState(new DemonstrationCreatedState(demonstration));
        demonstration.setStartDate(new Date(2016, 02, 01));
        demonstration.setEndDate(new Date(2016, 02, 30));

        controller.setEfectiveDemonstration(demonstration);
    }

    /**
     * Test of updateDemonstration method, of class
     * DefineEffectiveDemosController, returns true.
     */
    @Test
    public void testUpdateDemonstrationTrue() {
        System.out.println("updateDemonstration");

        Demonstration demonstration1 = new Demonstration("demo1");
        demonstration1.setCurrentState(new DemonstrationCreatedState(demonstration1));
        List<Demonstration> demonsList1 = new ArrayList<>();
        demonsList1.add(demonstration1);

        Exhibition exhibition1 = new Exhibition();
        exhibition1.setDemonstrationsList(new DemonstrationsList(demonsList1));
        controller.setExhibitionAndDemonstrationsList(exhibition1);

        boolean result = controller.updateDemonstration();
        assertTrue(result);
    }

    /**
     * Test of updateDemonstration method, of class
     * DefineEffectiveDemosController.
     */
    @Test
    public void testUpdateDemonstration() {
        System.out.println("updateDemonstration");

        Demonstration demonstration1 = new Demonstration("demo1");
        demonstration1.setCurrentState(new DemonstrationCreatedState(demonstration1));
        List<Demonstration> demonsList1 = new ArrayList<>();
        demonsList1.add(demonstration1);

        Exhibition exhibition1 = new Exhibition();
        exhibition1.setDemonstrationsList(new DemonstrationsList(demonsList1));
        controller.setExhibitionAndDemonstrationsList(exhibition1);

        controller.updateDemonstration();

        demonstration1.setCurrentState(new DemonstrationDecidedState(demonstration1));
        List<Demonstration> expResult = demonsList1;

        List<Demonstration> result = controller.getDemonstrationsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateDemosntrationList method, of class
     * DefineEffectiveDemosController, returns true.
     */
    @Test
    public void testUpdateDemosntrationListTrue() {
        System.out.println("updateDemosntrationList");

        Demonstration demonstration1 = new Demonstration("demo1");
        demonstration1.setCurrentState(new DemonstrationCreatedState(demonstration1));

        Demonstration demonstration2 = new Demonstration("demo2");
        demonstration2.setCurrentState(new DemonstrationDecidedState(demonstration2));

        List<Demonstration> demonsList1 = new ArrayList<>();
        demonsList1.add(demonstration1);
        demonsList1.add(demonstration2);

        Exhibition selectedExhibition = new Exhibition();
        selectedExhibition.setDemonstrationsList(new DemonstrationsList(demonsList1));
        controller.setExhibitionAndDemonstrationsList(selectedExhibition);

        boolean result = controller.updateDemosntrationList();
        assertTrue(result);
    }

    /**
     * Test of updateDemosntrationList method, of class
     * DefineEffectiveDemosController.
     */
    @Test
    public void testUpdateDemosntrationList() {
        System.out.println("updateDemosntrationList");

        Demonstration demonstration1 = new Demonstration("demo1");
        demonstration1.setCurrentState(new DemonstrationCreatedState(demonstration1));

        Demonstration demonstration2 = new Demonstration("demo2");
        demonstration2.setCurrentState(new DemonstrationDecidedState(demonstration2));

        List<Demonstration> demonsList1 = new ArrayList<>();
        demonsList1.add(demonstration1);
        demonsList1.add(demonstration2);

        Exhibition selectedExhibition = new Exhibition();
        selectedExhibition.setDemonstrationsList(new DemonstrationsList(demonsList1));
        controller.setExhibitionAndDemonstrationsList(selectedExhibition);

        controller.updateDemosntrationList();

        Demonstration demonstration3 = new Demonstration("demo1");
        demonstration3.setCurrentState(new DemonstrationCreatedState(demonstration3));

        Demonstration demonstration4 = new Demonstration("demo2");
        demonstration4.setCurrentState(new DemonstrationDecidedState(demonstration4));
        List<Demonstration> demonsList2 = new ArrayList<>();
        demonsList2.add(demonstration3);
        demonsList2.add(demonstration4);

        Exhibition expResult = new Exhibition();
        expResult.setDemonstrationsList(new DemonstrationsList(demonsList2));
        Exhibition result = selectedExhibition;

        assertEquals(expResult, result);
    }

}
