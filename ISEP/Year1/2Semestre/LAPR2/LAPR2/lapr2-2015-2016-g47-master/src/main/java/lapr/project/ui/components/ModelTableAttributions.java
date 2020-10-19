/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.StaffAttribution;

/**
 * Model of communication for attributions list
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ModelTableAttributions extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {"Exhibitor Name", "Staff Member"};
    /**
     * List of  staff attributions
     */
    private final List<StaffAttribution> staffAttributionList;

    /**
     * Creates an instance of ModelTableAttributions
     *
     * @param staffAttributionList list of staff attributions
     */
    public ModelTableAttributions(List<StaffAttribution> staffAttributionList) {
        this.staffAttributionList = staffAttributionList;
    }

    /**
     * Get the number of rows.
     *
     * @return the number of rows.
     */
    @Override
    public int getRowCount() {
        return staffAttributionList.size();
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

        return columnIndex == 0 ? staffAttributionList.get(rowIndex).getApplication().getTitle()
                : staffAttributionList.get(rowIndex).getStaffMember().getUser().getName();
    }

}
