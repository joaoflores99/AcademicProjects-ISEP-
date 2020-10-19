/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lapr.project.utils.Calculator;

/**
 * Represents the recods of all application's evaluations.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class Record implements Serializable {

    /**
     * A list of staff that has already evaluated at least one application.
     */
    private final List<StaffMember> staffList;

    /**
     * List of all applications evaluated (for all exhibitions).
     */
    private final List<Application> applicationsList;

    /**
     * Matrix with evaluations from a Staff Member to Application (Each line is
     * a Staff Member each column is a Application) eg. | A1 | A2 | A3 S1 | 2,3
     * | 3,4 | N/A S2 | 3,6 | 4,2 | 3,2
     */
    private Float[][] evaluationsMatrix;

    /**
     * Average test value.
     */
    private static final float TEST_AVG = 1.0f;

    /**
     * Minimum number of applications evaluated to run hypothesis test
     */
    private static final int MIN_EVALUATED_APPS = 30;

    /**
     * Default constructor of a Record.
     */
    public Record() {

        this.staffList = new ArrayList<>();
        this.applicationsList = new ArrayList<>();
        this.evaluationsMatrix = new Float[0][0];
    }

    /**
     * Constructor of a Record class recieving its attributes by parameters.
     *
     * @param staffList the staff list
     * @param applicationsList applications list
     * @param evaluationsMatrix evaluations matrix
     */
    public Record(List<StaffMember> staffList, List<Application> applicationsList, Float[][] evaluationsMatrix) {

        this.staffList = new ArrayList<>(staffList);
        this.applicationsList = new ArrayList<>(applicationsList);
        this.evaluationsMatrix = evaluationsMatrix.clone();
    }

    /**
     * Copy Contructor of a Record.
     *
     * @param record the record to copy
     */
    public Record(Record record) {

        this.staffList = new ArrayList<>(record.getStaffList());
        this.applicationsList = new ArrayList<>(record.applicationsList);
        this.evaluationsMatrix = record.evaluationsMatrix.clone();
    }

    /**
     * Obtain Staff List.
     *
     * @return the staffList.
     */
    public List<StaffMember> getStaffList() {
        return staffList;
    }

    /**
     * Calculates the staff analytics analytics.
     *
     * @return the analytics output list.
     */
    public List<StaffMemberAnalytic> calculateStaffAnalytics() {

        List<StaffMemberAnalytic> analytics = new ArrayList<>();

        int numStaffMember = this.evaluationsMatrix.length;

        // Calculations
        // Xi
        Float[] rowAvg = new Float[numStaffMember];
        for (int i = 0; i < numStaffMember; i++) {
            rowAvg[i] = Calculator.calculateVectorAverage(evaluationsMatrix[i]);
        }
        // (XT)
        float globalAverage = Calculator.calculateVectorAverage(rowAvg);
        // (dij)
        Float[][] deviations = calculateDeviationsMatrix(evaluationsMatrix, globalAverage);
        // (di)
        Float[] rowDeviationsAvg = new Float[numStaffMember];
        for (int i = 0; i < numStaffMember; i++) {
            rowDeviationsAvg[i] = Calculator.calculateVectorAverage(deviations[i]);
        }
        // (D)
        float globalDeviationAvg = Calculator.calculateVectorAverage(rowDeviationsAvg);
        // (s2i)
        Float[] squaredVariances = calculateRowSquaredVariance(deviations, globalDeviationAvg);
        // (zi)
        Float[] zValues = new Float[numStaffMember];
        for (int i = 0; i < numStaffMember; i++) {
            zValues[i] = calculateCriticalZ(squaredVariances[i], globalDeviationAvg, countNotNullElements(deviations[i]));
        }

        for (int i = 0; i < numStaffMember; i++) {

            int numEvaluatedApps = countNotNullElements(deviations[i]);

            if (numEvaluatedApps <= MIN_EVALUATED_APPS) {
                analytics.add(null);
            } else {
                StaffMemberAnalytic analytic = new StaffMemberAnalytic();
                analytic.setStaffMember(getStaffList().get(i));
                analytic.setNumApplications(numEvaluatedApps);
                analytic.setEvaluationsAverage(rowAvg[i]);
                analytic.setDeviationsAverage(rowDeviationsAvg[i]);
                analytic.setHypothesisTestValue(zValues[i]);

                analytics.add(analytic);
            }
        }

        return analytics;
    }

    /**
     * Calculate the deviations of each element.
     *
     * @param matrix matrix with classification average values of each staff
     * member
     * @param globalAverage global average of the matrix
     * @return matrix with each deviation
     */
    private Float[][] calculateDeviationsMatrix(Float[][] matrix, float globalAverage) {

        int rows = matrix.length;
        int columns = matrix[0].length;

        Float[][] deviations = new Float[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {

                    deviations[i][j] = Calculator.calculateDeviation(matrix[i][j], globalAverage);
                }
            }
        }
        return deviations;
    }

    /**
     * Calculate the variance of each staff member's evaluations (row).
     *
     * @param deviations matrix with values to calculate deviation
     * @param globalDeviationAvg global devations average
     *
     * @return array with variances for each staff member
     */
    private Float[] calculateRowSquaredVariance(Float[][] deviations, float globalDeviationAvg) {

        int rows = deviations.length;

        Float[] variances = new Float[rows];

        for (int i = 0; i < deviations.length; i++) {

            float sum = 0.0f;
            int count = 0;

            for (int j = 0; j < deviations[i].length; j++) {
                if (deviations[i][j] != null) {

                    float result = deviations[i][j] - globalDeviationAvg;
                    sum += (float) Math.pow(result, 2);
                    count++;
                }
            }
            variances[i] = sum / (count - 1);
        }
        return variances;
    }

    /**
     * Calculate the critical z value of a staff member's evaluations.
     *
     * @param squaredVariance variances with of each staff member
     * @param globalDeviationAvg global devations average
     * @param numEvaluations number of evaluations of staff member.
     *
     * @return critical z value for each staff member
     */
    private float calculateCriticalZ(float squaredVariance, float globalDeviationAvg, int numEvaluations) {

        double zValue = (globalDeviationAvg - TEST_AVG) / Math.sqrt(squaredVariance / numEvaluations);

        return (float) zValue;
    }

    /**
     * Counts how many not elements existe in array.
     *
     * @param vector array to verify
     * @return count of not null elements
     */
    private int countNotNullElements(Float[] vector) {

        int count = 0;

        for (Float element : vector) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Return the textual representation of the record.
     *
     * @return the textual representation of the record
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Record{\n\t\t|");
        for (Application application : this.applicationsList) {
            s.append(String.format("%-5s\t|", application.getDisplayInfo()));
        }
        s.append("\n");
        for (int i = 0; i < evaluationsMatrix.length; i++) {
            s.append(String.format("%-10s\t|", getStaffList().get(i).getUser().getUsername()));
            for (int j = 0; j < evaluationsMatrix[i].length; j++) {
                s.append(String.format("%-10.2f\t|", evaluationsMatrix[i][j]));
            }
            s.append("\n");
        }
        s.append("\n}");
        return s.toString();
    }

    /**
     * Compares two record objects.
     *
     * @param otherObject Object to compare
     * @return true if the objects are equals.
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Record otherRecord = (Record) otherObject;

        return this.applicationsList.equals(otherRecord.applicationsList)
                && this.getStaffList().equals(otherRecord.getStaffList())
                && Arrays.equals(this.evaluationsMatrix, otherRecord.evaluationsMatrix);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.applicationsList);
        hash = 71 * hash + Arrays.deepHashCode(this.evaluationsMatrix);
        return hash;
    }

    /**
     * Add a given evaluation to the evaluations matrix.
     *
     * @param average average of the evaluation to be added
     * @param staffMember staff member which evaluate the application
     * @param application evaluated application
     */
    public void addEvaluation(float average, StaffMember staffMember, Application application) {
        int row; // Staff Member
        int column; // Application

        // gets the staff row
        if (this.getStaffList().contains(staffMember)) {
            row = this.getStaffList().indexOf(staffMember);
        } else {
            this.getStaffList().add(staffMember);
            row = this.getStaffList().indexOf(staffMember);
        }

        // gets the application row
        if (this.applicationsList.contains(application)) {
            column = this.applicationsList.indexOf(application);
        } else {
            this.applicationsList.add(application);
            column = this.applicationsList.indexOf(application);
        }

        updateEvaluationsMatrix(average, row, column);

    }

    /**
     * Updates the evaluation matrix.
     *
     * @param average average of the evaluation to be added
     * @param row staff member which evaluate the application
     * @param column evaluated application
     */
    private void updateEvaluationsMatrix(float average, int row, int column) {
        if (this.evaluationsMatrix.length < row + 1 || this.evaluationsMatrix[0].length < column + 1) { // update the matrix sixe
            Float[][] newEvaluationsMatrix = new Float[row + 2][column + 2];
            for (int i = 0; i < this.evaluationsMatrix.length; i++) {
                for (int j = 0; j < this.evaluationsMatrix[0].length; j++) {

                    newEvaluationsMatrix[i][j] = this.evaluationsMatrix[i][j];

                }
            }
            newEvaluationsMatrix[row][column] = average;
            this.evaluationsMatrix = newEvaluationsMatrix;
        } else {
            this.evaluationsMatrix[row][column] = average;
        }
    }

    public static void main(String[] args) {

        List<StaffMember> ls = new ArrayList<>(10);
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());
        ls.add(new StaffMember());

        List<Application> la = new ArrayList<>(10);
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());
        la.add(new ExhibitionApplication());

        Float[][] matrix = {
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f},
            {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f}};

        Record r = new Record(ls, la, matrix);
        System.out.println(r);
    }
}
