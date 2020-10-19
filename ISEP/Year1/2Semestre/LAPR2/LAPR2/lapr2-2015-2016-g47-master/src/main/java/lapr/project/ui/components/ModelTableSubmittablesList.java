/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.Submittable;

/**
 * Represents a model table for Submittables
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ModelTableSubmittablesList extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] columnNames = {"Title", "Start date", "End date"};
    /**
     * The exhibitions list.
     */
    private final List<Submittable> submittablesList;

    /**
     * Constructs a instance of this class.
     *
     * @param submittablesList the list of submittables
     */
    public ModelTableSubmittablesList(List<Submittable> submittablesList) {
        this.submittablesList = submittablesList;
    }

    /**
     * Returns the number of rows.
     *
     * @return number of columns
     */
    @Override
    public int getRowCount() {
        return this.submittablesList.size();
    }

    /**
     * Returns the number of columns.
     *
     * @return number of columns
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the column name in the selected index
     *
     * @param column the column index
     * @return the column name
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns the selected element's description.
     *
     * @param rowIndex row index
     * @param columnIndex column index
     * @return element's description
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String nomeColuna = getColumnName(columnIndex);
        String[] info = this.submittablesList.get(rowIndex).getInfo();
        return nomeColuna.equalsIgnoreCase(columnNames[0])
                ? info[0]
                : nomeColuna.equalsIgnoreCase(columnNames[1])
                        ? info[1]
                        : info[2];
    }

}
