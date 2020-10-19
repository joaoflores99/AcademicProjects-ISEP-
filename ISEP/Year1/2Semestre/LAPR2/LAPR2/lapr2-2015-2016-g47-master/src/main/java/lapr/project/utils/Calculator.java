/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.util.List;

/**
 * Contains useful methods for calculations.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class Calculator {

    /**
     * Calculate the vector average.
     *
     * @param vector vector to be calculated
     * @return average of the vector
     */
    public static float calculateVectorAverage(Float[] vector) {
        float sum = 0;
        int count = 0;

        for (Float value : vector) {
            if (value != null) {
                sum += value;
                count++;
            }
        }

        return count < 1 ? 0 : sum / count;
    }

    /**
     * Calculates the matrix average.
     *
     * @param matrix matrix to be calculated
     * @return average of the matrix
     */
    public static float calculateMatrixAverage(Float[][] matrix) {
        float sum = 0;
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] != null) {
                for (int j = 0; j < matrix[i].length; j++) {

                    if (matrix[i][j] != null) {
                        sum += matrix[i][j];
                        count++;
                    }
                }
            }
        }

        return count < 1 ? 0 : sum / count;
    }

    /**
     * Calculates the deviation.
     *
     * @param value value
     * @param average average
     * @return deviation
     */
    public static float calculateDeviation(float value, float average) {
        return Math.abs(value - average);
    }

    /**
     * Calculate the average of a given list.
     *
     * @param valuesList values list
     * @return average of the values in the list
     */
    public static float calculateListAverage(List<Integer> valuesList) {
        int sum = 0;
        int count = 0;

        for (Integer value : valuesList) {
            if (value != null) {
                sum += value;
                count++;
            }
        }

        return count < 1 ? 0f : (float) sum / count;
    }
}
