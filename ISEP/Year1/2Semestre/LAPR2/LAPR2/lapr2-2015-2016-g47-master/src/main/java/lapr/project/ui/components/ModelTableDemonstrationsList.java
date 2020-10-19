/**
 * Package location for UI concepts.
 */
package lapr.project.ui.components;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.Demonstration;

/**
 * Represents a model for a a demonstrations list table
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ModelTableDemonstrationsList extends AbstractTableModel {

    /**
     * Names of the table columns.
     */
    private static final String[] columnNames = {"", "Designação"};
    /**
     * Demonstrations list.
     */
    private final List<Demonstration> demonstrationsList;

    /**
     * Creates a new instance of this class.
     *
     * @param demonstrationsList the list of demonstrations
     */
    public ModelTableDemonstrationsList(List<Demonstration>  demonstrationsList) {
        this. demonstrationsList =  demonstrationsList;
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return number of rows
     */
    @Override
    public int getRowCount() {
        return demonstrationsList.size();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return number of columns
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the column name in the index
     *
     * @param column index of the column
     * @return column name
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns the selected element description
     *
     * @param rowIndex row index
     * @param columnIndex  column index
     * @return  the element designation
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String nomeColuna = getColumnName(columnIndex);
        return nomeColuna.equalsIgnoreCase(columnNames[0])
                ? ""
                : demonstrationsList.get(rowIndex).getDescription();
    }

}
