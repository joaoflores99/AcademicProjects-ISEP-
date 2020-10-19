/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a product.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ProductTest {

    /**
     * The Products to be tested.
     */
    private Product product;

    @Before
    public void setUp() {
        this.product = new Product("Water");
    }

    /**
     * Test of getDesignation method, of class Product.
     */
    @Test
    public void testGetDesignation() {
        System.out.println("getDesignation");
        String expResult = "Water";
        String result = this.product.getDesignation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDesignation method, of class Product.
     */
    @Test
    public void testSetDesignation() {
        System.out.println("setDesignation");
        String designation = "Wine";
        this.product.setDesignation(designation);
        assertEquals(this.product.getDesignation(), designation);
    }

    /**
     * Test of equals method, of class Product.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Product("Water");
        assertTrue(this.product.equals(otherObject));
    }

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(this.product.validate());
    }

    /**
     * Test of getDisplayInfo method, of class Product.
     */
    @Test
    public void testGetDisplayInfo() {
        System.out.println("getDisplayInfo");
        Product instance = new Product("Water");
        String expResult = "Water";
        String result = instance.getDisplayInfo();
        assertEquals(expResult, result);
    }

}
