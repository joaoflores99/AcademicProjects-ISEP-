package lapr.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * Class that allows converting Tables to CSV and write to files.
 */
public class CSVParser {

    private static final String CELL_SEPARATOR = ";";
    private static final String LINE_SEPARATOR = String.format("%n");

    /**
     * Writes to a file a table in csv format.
     *
     * @param table table to parse.
     * @param file file to write
     */
    public static void writeToCSV(JTable table, File file) {

        try {
            FileWriter writer = new FileWriter(file);
            try {

                TableModel model = table.getModel();

                int columns = model.getColumnCount();
                int rows = model.getRowCount();
                // Print Header
                for (int i = 0; i < columns; i++) {
                    writer.append(model.getColumnName(i));
                    if (i != columns - 1) {
                        writer.append(CELL_SEPARATOR);
                    }
                }
                writer.append(LINE_SEPARATOR);
                // Print table
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        Object cell = model.getValueAt(i, j);
                        if (cell == null) {
                            writer.append(" ");
                        } else {
                            
                            String export = cell.toString().trim().replace(',', ';');
                            writer.append(export);
                        }
                        
                        if (j != columns - 1) {
                            writer.append(CELL_SEPARATOR);
                        }
                    }
                    writer.append(LINE_SEPARATOR);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Writing to file failed! Try again.",
                        "Writing failed",
                        JOptionPane.WARNING_MESSAGE);
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Something went wrong! Try again.",
                            "Error 404",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "File Path incorrect! Try again.",
                    "File incorrect",
                    JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong! Try again.",
                    "Error 404",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
