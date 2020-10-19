/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.StaffMemberAnalytic;

/**
 * Model of communication for staff member analytics list
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ModelTableStaffMemeberAnalytics extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {
        "Staff Member",
        "Applications Evaluated",
        "Grades Average",
        "Deviations Average",
        "Hypothesis Test Value",
        "Warning"};
    /**
     * List of staff members analytics
     */
    private final List<StaffMemberAnalytic> staffMemberAnalytics;

    /**
     * Creates an instance of Staff Analytics Model Table
     *
     * @param staffMemberAnalytics list of staff member analytics
     */
    public ModelTableStaffMemeberAnalytics(List<StaffMemberAnalytic> staffMemberAnalytics) {
        this.staffMemberAnalytics = staffMemberAnalytics;
    }

    /**
     * Get the number of rows.
     *
     * @return the number of rows.
     */
    @Override
    public int getRowCount() {
        return staffMemberAnalytics.size();
    }

    /**
     * Get the number of columns.
     *
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return COLUMNS_NAMES.length;
    }

    /**
     * Returns the column name from its index.
     *
     * @param column index of the column
     * @return the column's name
     */
    @Override
    public String getColumnName(int column) {
        return COLUMNS_NAMES[column];
    }

    /**
     * Returns the description of the specified element.
     *
     * @param rowIndex row's index
     * @param columnIndex column's index
     * @return the description of the element
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (staffMemberAnalytics.get(rowIndex) == null) {
            return "N/A";
        }

        switch (columnIndex) {
            case 0:
                return staffMemberAnalytics.get(rowIndex).getStaffMember().getDisplayInfo();
            case 1:
                return staffMemberAnalytics.get(rowIndex).getNumApplications();
            case 2:
                return String.format("%.2f", staffMemberAnalytics.get(rowIndex).getEvaluationsAverage());
            case 3:
                return String.format("%.2f", staffMemberAnalytics.get(rowIndex).getDeviationsAverage());
            case 4:
                return String.format("%.2f", staffMemberAnalytics.get(rowIndex).getHypothesisTestValue());
            case 5:
                return staffMemberAnalytics.get(rowIndex).isWarning() ? "Affirmative" : "Negative";
        }
        return "";
    }

}
