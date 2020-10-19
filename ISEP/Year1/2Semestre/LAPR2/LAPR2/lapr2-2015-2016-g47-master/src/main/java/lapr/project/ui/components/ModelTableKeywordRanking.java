/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.util.List;
import javafx.util.Pair;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.Keyword;

/**
 * Model of communication for a keyword ranking
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ModelTableKeywordRanking extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {
        "Keyword",
        "Frequency",};
    /**
     * Keyword ranking.
     */
    private final List<Pair<Keyword, Integer>> ranking;

    /**
     * Creates an instance of Keyword Ranking Model Table
     *
     * @param ranking keyword ranking
     */
    public ModelTableKeywordRanking(List<Pair<Keyword, Integer>> ranking) {
        this.ranking = ranking;
    }

    /**
     * Get the number of rows.
     *
     * @return the number of rows.
     */
    @Override
    public int getRowCount() {
        return ranking.size();
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

        Keyword keyword = ranking.get(rowIndex).getKey();
        int frequency = ranking.get(rowIndex).getValue();
        
        return columnIndex == 0 ? keyword.getDisplayInfo() : frequency;
    }

}
