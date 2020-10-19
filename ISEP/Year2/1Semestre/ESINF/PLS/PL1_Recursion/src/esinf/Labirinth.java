package esinf;

/**
 *
 * @author DEI-ESINF
 */
public class Labirinth {

    /**
     *
     * @param actual the labirinth in its actual (marked) form
     * @param y coordinate y in the labirinth
     * @param x coordinate x in the labirinth
     * @return the marked labirinth or null if there is no way
     */
    public static int[][] check(int[][] actual, int y, int x) {

        if (x == actual.length - 1 && y == actual[0].length - 1) {
            actual[x][y] = 9;
            return actual;
        } else {
            if (x > 0 && actual[x - 1][y] == 1) {
                if (check(actual, x - 1, y) != null) {
                    return actual;
                }
            }
            if (x < actual.length - 1 && actual[x + 1][y] == 1) {
                if (check(actual, x + 1, y) != null) {
                    return actual;
                }
            }

            if (actual[0].length - 1 > y && actual[x][y + 1] == 1) {
                if (check(actual, x, y + 1) != null) {
                    return actual;
                }
            }

            if (x > 0 && actual[x][y - 1] == 1) {
                if (check(actual, x, y - 1) != null) {
                    return actual;
                }
            }

        }
        actual[x][y] = 2;
        return null;
    }

}
