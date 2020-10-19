/**
 * Package location for AssignStandsController concepts.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.ApplicationAnalysis;

/**
 * Represents a mode table for applications analysis.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ModelTableApplicationsAnalysis extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private final String[] COLUMNS_NAMES;

    /**
     * List of applications analysis.
     */
    private final List<ApplicationAnalysis> applicationsAnalysisList;

    /**
     * Creates an instance of model table applications analysis.
     *
     * @param applicationsAnalysisList applications analysis list.
     */
    public ModelTableApplicationsAnalysis(List<ApplicationAnalysis> applicationsAnalysisList) {
        this.applicationsAnalysisList = applicationsAnalysisList;
        this.COLUMNS_NAMES = this.applicationsAnalysisList.get(0).getDataColumnsName();
    }

    /**
     * Get the number of rows.
     *
     * @return the number of rows.
     */
    @Override
    public int getRowCount() {
        return this.applicationsAnalysisList.size();
    }

    /**
     * Get the number of columns.
     *
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return this.COLUMNS_NAMES.length;
    }

    /**
     * Returns the column name from its index.
     *
     * @param columnIndex index of the column
     * @return the column's name
     */
    @Override
    public String getColumnName(int columnIndex) {
        return this.COLUMNS_NAMES[columnIndex];
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
        String columnName = getColumnName(columnIndex);
        return columnName.equalsIgnoreCase(this.COLUMNS_NAMES[0])
                ? this.applicationsAnalysisList.get(rowIndex).getApplicationDisplayInfo()
                : columnName.equalsIgnoreCase(this.COLUMNS_NAMES[COLUMNS_NAMES.length - 1])
                ? this.applicationsAnalysisList.get(rowIndex).getTotalAverage()
                : this.applicationsAnalysisList.get(rowIndex).getAnswersAverage().get(columnIndex - 1);
    }

}
