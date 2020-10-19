/**
 * Package location for Pure Fabrication util classes tests.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests the classe Calculator.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CalculatorTest {

    /**
     * Test of calculateVectorAverage method, of class Calculator.
     */
    @Test
    public void testCalculateVectorAverage() {
        System.out.println("calculateVectorAverage");
        Float[] vector = {2.50f, 2.20f, null, 3.30f};
        float expResult = 2.67f;
        float result = Calculator.calculateVectorAverage(vector);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateVectorAverage method with a 0 value, of class
     * Calculator.
     */
    @Test
    public void testCalculateVectorAverageWithZero() {
        System.out.println("calculateVectorAverage - with 0 value");
        Float[] vector = {0.00f, 2.50f, 2.20f, 0.00f, null, 3.30f, 0.00f};
        float expResult = 1.33f;
        float result = Calculator.calculateVectorAverage(vector);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateMatrixAverage method, of class Calculator.
     */
    @Test
    public void testCalculateMatrixAverage() {
        System.out.println("calculateMatrixAverage");
        Float[][] matrix = {{2.50f, 2.20f, null, 3.30f},
        {3.50f, 2.20f, null, 5.30f},
        {2.50f, 3.20f, null, 3.30f}
        };
        float expResult = 3.11f;
        float result = Calculator.calculateMatrixAverage(matrix);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateMatrixAverage method with a null vector, of class
     * Calculator.
     */
    @Test
    public void testCalculateMatrixAverageWithNullVector() {
        System.out.println("calculateMatrixAverage - with null vector");
        Float[][] matrix = {{2.50f, 2.20f, null, 3.30f},
        null,
        {2.50f, 3.20f, null, 3.30f}
        };
        float expResult = 2.83f;
        float result = Calculator.calculateMatrixAverage(matrix);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateMatrixAverage method with a null vector and zeros , of
     * class Calculator.
     */
    @Test
    public void testCalculateMatrixAverageWithNullVectorAndZero() {
        System.out.println("calculateMatrixAverage - with null vector and zero");
        Float[][] matrix = {{2.50f, 2.20f, null, 3.30f, 0.00f},
        null,
        {0.00f, 2.50f, 3.20f, null, 3.30f}
        };
        float expResult = 2.13f;
        float result = Calculator.calculateMatrixAverage(matrix);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateDeviation method, of class Calculator.
     */
    @Test
    public void testCalculateDeviationAverageBiggerThanValue() {
        System.out.println("calculateDeviation - average bigger than value");
        float value = 30.80f;
        float average = 35.40f;
        float expResult = 4.60f;
        float result = Calculator.calculateDeviation(value, average);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateDeviation method, of class Calculator.
     */
    @Test
    public void testCalculateDeviationValueBiggerThanAverage() {
        System.out.println("calculateDeviation - value bigger than average");
        float value = 35.40f;
        float average = 30.80f;
        float expResult = 4.60f;
        float result = Calculator.calculateDeviation(value, average);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateListAverage method, of class Calculator.
     */
    @Test
    public void testCalculateListAverage() {
        System.out.println("calculateListAverage");
        List<Integer> valuesList = new ArrayList<>();
        valuesList.add(3);
        valuesList.add(4);
        valuesList.add(null);
        valuesList.add(4);
        float expResult = 3.67f;
        float result = Calculator.calculateListAverage(valuesList);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateListAverage method with negative dividend, of class
     * Calculator.
     */
    @Test
    public void testCalculateListAverageNegativeDividend() {
        System.out.println("calculateListAverage with a negative dividend");
        List<Integer> valuesList = new ArrayList<>();
        valuesList.add(-3);
        valuesList.add(-4);
        valuesList.add(null);
        valuesList.add(2);
        float expResult = -1.67f;
        float result = Calculator.calculateListAverage(valuesList);
        assertEquals(expResult, result, 0.01);
    }
}
